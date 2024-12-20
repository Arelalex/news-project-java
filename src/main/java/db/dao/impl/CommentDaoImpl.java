package db.dao.impl;

import db.dao.CommentDao;
import db.dto.CommentDto;
import db.entity.CommentEntity;
import db.exception.*;
import db.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class CommentDaoImpl implements CommentDao<Long, CommentEntity> {

    private static final String FOREIGN_USER_ID = "user_id";
    private static final String FOREIGN_STATUS_ID = "status_id";
    private static final String FOREIGN_NEWS_ID = "news_id";
    private static final String COMMENT_ID = "comment_id";
    private static final String COMMENT_CONTENT = "content";
    private static final String COMMENT_CREATED_AT = "created_at";
    private static final String COMMENT_UPDATED_AT = "updated_at";
    private static final String COMMENT_ATTACHMENT = "attachment";
    private static final String DELETE_SQL = """
            DELETE FROM comment
            WHERE comment_id = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO comment (content, created_at, updated_at, attachment, news_id, user_id, status_id) 
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String UPDATE_SQL = """
                    UPDATE comment
                    SET content = ?,
                        created_at = ?,
                        updated_at = ?,
                        attachment = ?,
                        news_id = ?,
                        user_id = ?,
                        status_id = ?
                    WHERE comment_id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT c.comment_id,
                   c.content,
                   c.created_at,
                   c.updated_at,
                   c.attachment,
                n.news_id,
                u.user_id,
                s.status_id
                     FROM comment c
                     LEFT JOIN news n ON c.news_id = n.news_id
                     LEFT JOIN portal_user u ON c.user_id = u.user_id
                     LEFT JOIN status s ON c.status_id = s.status_id
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE comment.comment_id = ?
            """;
    private static CommentDaoImpl instance;
    private final NewsDaoImpl newsDaoImpl = NewsDaoImpl.getInstance();
    private final PortalUserDaoImpl portalUserDaoImpl = PortalUserDaoImpl.getInstance();
    private final StatusDaoImpl statusDaoImpl = StatusDaoImpl.getInstance();

    private CommentDaoImpl() {
    }

    public static synchronized CommentDaoImpl getInstance() {
        if (instance == null) {
            instance = new CommentDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoExceptionDelete("Error deleting values from table", throwables);
        }
    }

    @Override
    public CommentEntity save(CommentEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getContent());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getCreatedAt()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getUpdatedAt()));
            preparedStatement.setString(4, entity.getAttachment());
            preparedStatement.setLong(5, entity.getNews().getNewsId());
            preparedStatement.setInt(6, entity.getUser().getUserId());
            preparedStatement.setInt(7, entity.getStatus().getStatusId());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setCommentId(generatedKeys.getLong("comment_id"));
            }
            return entity;
        } catch (SQLException throwables) {
            throw new DaoExceptionInsert("Error inserting values into table", throwables);
        }
    }

    @Override
    public void update(CommentEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getContent());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getCreatedAt()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getUpdatedAt()));
            preparedStatement.setString(4, entity.getAttachment());
            preparedStatement.setLong(5, entity.getNews().getNewsId());
            preparedStatement.setInt(6, entity.getUser().getUserId());
            preparedStatement.setInt(7, entity.getStatus().getStatusId());

            preparedStatement.setLong(8, entity.getCommentId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoExceptionUpdate("Error updating values in table", throwables);
        }
    }

    @Override
    public Optional<CommentEntity> findById(Long id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    @Override
    public Optional<CommentEntity> findById(Long id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            CommentEntity commentEntity = null;
            if (resultSet.next()) {
                commentEntity = buildComment(resultSet);
            }
            return Optional.ofNullable(commentEntity);
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    @Override
    public List<CommentEntity> findAllByFilter(CommentDto filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.getContent() != null) {
            whereSql.add("c.content LIKE ?");
            parameters.add("%" + filter.getContent() + "%");
        }
        if (filter.getCreatedAt() != null) {
            whereSql.add("c.created_at = ?");
            parameters.add("%" + filter.getCreatedAt() + "%");
        }
        if (filter.getUpdatedAt() != null) {
            whereSql.add("c.updated_at = ?");
            parameters.add("%" + filter.getUpdatedAt() + "%");
        }

        if (filter.getNewsId() != null) {
            whereSql.add("c.news_id = ?");
            parameters.add(filter.getNewsId());
        }
        if (filter.getUser() != null) {
            whereSql.add("c.user_id = ?");
            parameters.add(filter.getUser());
        }
        if (filter.getStatus() != null) {
            whereSql.add("c.status_id = ?");
            parameters.add(filter.getStatus());
        }

        parameters.add(filter.getLimit());
        parameters.add(filter.getOffset());
        var where = whereSql.stream()
                .collect(joining(" AND ", " WHERE ", " LIMIT ? OFFSET ? "));

        var sql = FIND_ALL_SQL + where;

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(preparedStatement);
            var resultSet = preparedStatement.executeQuery();
            List<CommentEntity> commentEntities = new ArrayList<>();
            while (resultSet.next()) {
                commentEntities.add(buildComment(resultSet));
            }
            return commentEntities;
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching for values in table", throwables);
        }
    }

    @Override
    public List<CommentEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<CommentEntity> commentEntities = new ArrayList<>();
            while (resultSet.next()) {
                commentEntities.add(buildComment(resultSet));
            }
            return commentEntities;
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching for values in table", throwables);
        }
    }

    private CommentEntity buildComment(ResultSet resultSet) throws SQLException {
        return new CommentEntity(
                resultSet.getLong(COMMENT_ID),
                resultSet.getString(COMMENT_CONTENT),
                resultSet.getTimestamp(COMMENT_CREATED_AT).toLocalDateTime(),
                resultSet.getTimestamp(COMMENT_UPDATED_AT).toLocalDateTime(),
                resultSet.getString(COMMENT_ATTACHMENT),
                newsDaoImpl.findById(resultSet.getLong(FOREIGN_NEWS_ID),
                        resultSet.getStatement().getConnection()).orElse(null),
                portalUserDaoImpl.findById(resultSet.getInt(FOREIGN_USER_ID),
                        resultSet.getStatement().getConnection()).orElse(null),
                statusDaoImpl.findById(resultSet.getInt(FOREIGN_STATUS_ID),
                        resultSet.getStatement().getConnection()).orElse(null)
        );
    }
}
