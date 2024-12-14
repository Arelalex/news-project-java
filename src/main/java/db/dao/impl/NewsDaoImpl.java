package db.dao.impl;

import db.dao.NewsDao;
import db.dto.NewsDto;
import db.entity.NewsEntity;
import db.exception.*;
import db.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class NewsDaoImpl implements NewsDao<Long, NewsEntity> {

    private static final String NEWS_ID = "news_id";
    private static final String NEWS_TITLE = "title";
    private static final String NEWS_DESCRIPTION = "description";
    private static final String NEWS_CONTENT = "content";
    private static final String NEWS_CREATED_AT = "created_at";
    private static final String NEWS_UPDATE_AT = "updated_at";
    private static final String NEWS_IMAGE = "image";
    private static final String FOREIGN_STATUS_ID = "status_id";
    private static final String FOREIGN_PORTAL_ID = "user_id";
    private static final String FOREIGN_CATEGORY_ID = "category_id";
    private static final String NEWS_REASON_REJ = "reason_rej";

    private static final String DELETE_SQL = """
            DELETE FROM news
            WHERE news_id = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO news (title, description, content, created_at, updated_at, image, user_id, category_id, status_id, reason_rej) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String UPDATE_SQL = """
                    UPDATE news
                    SET title = ?,
                        description = ?,
                        content = ?,
                        updated_at = ?,
                        image = ?,
            status_id = ?
                    WHERE news_id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT n.news_id,
                            n.title,
                            n.description,
                            n.content,
                            n.created_at,
                            n.updated_at,
                            n.image,
                            u.user_id,
                            c.category_id,
                            s.status_id,
                            n.reason_rej
                     FROM news n
                              LEFT JOIN portal_user u ON n.user_id = u.user_id
                              LEFT JOIN category c ON n.category_id = c.category_id
                              LEFT JOIN status s ON n.status_id = s.status_id
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE n.news_id = ?
            """;

    private static final String FIND_BY_CATEGORY_ID = """
                SELECT 
                    n.news_id,
                    n.title, 
                    n.description, 
                    n.content, 
                    n.created_at, 
                    n.updated_at, 
                    n.image, 
                    n.user_id, 
                    n.category_id, 
                    n.status_id,
                    n.reason_rej
                FROM news n
                LEFT JOIN portal_user u ON n.user_id = u.user_id
                LEFT JOIN category c ON n.category_id = c.category_id
                LEFT JOIN status s ON n.status_id = s.status_id
                WHERE n.category_id = ?
            """;

    private static final String FIND_BY_STATUS_ID = """
            SELECT
                    n.news_id,
                    n.title, 
                    n.description, 
                    n.content, 
                    n.created_at, 
                    n.updated_at, 
                    n.image, 
                    n.user_id, 
                    n.category_id, 
                    n.status_id,
                    n.reason_rej
                FROM news n
                LEFT JOIN portal_user u ON n.user_id = u.user_id
                LEFT JOIN category c ON n.category_id = c.category_id
                LEFT JOIN status s ON n.status_id = s.status_id
            WHERE n.status_id = ?
            """;

    private static final String UPDATE_STATUS_SQL = """
            UPDATE news SET status_id = ?, updated_at = ?, reason_rej = ? WHERE news_id = ?
            """;

    private static NewsDaoImpl instance = new NewsDaoImpl();
    private final PortalUserDaoImpl userDaoImpl = PortalUserDaoImpl.getInstance();
    private final CategoryDaoImpl categoryDaoImpl = CategoryDaoImpl.getInstance();
    private final StatusDaoImpl statusDaoImpl = StatusDaoImpl.getInstance();

    public static synchronized NewsDaoImpl getInstance() {
        if (instance == null) {
            instance = new NewsDaoImpl();
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
    public NewsEntity save(NewsEntity newsEntity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newsEntity.getTitle());
            preparedStatement.setString(2, newsEntity.getDescription());
            preparedStatement.setString(3, newsEntity.getContent());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(newsEntity.getCreatedAt()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(newsEntity.getUpdatedAt()));
            preparedStatement.setString(6, newsEntity.getImage());
            preparedStatement.setInt(7, newsEntity.getUser().getUserId());
            preparedStatement.setInt(8, newsEntity.getCategory().getCategoryId());
            preparedStatement.setInt(9, newsEntity.getStatus().getId());
            preparedStatement.setString(10, newsEntity.getReasonRej());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newsEntity.setNewsId(generatedKeys.getLong("news_id"));
            }
            System.out.println(preparedStatement);
            return newsEntity;
        } catch (SQLException throwables) {
            throw new DaoExceptionInsert("Error inserting values into table", throwables);
        }
    }

    @Override
    public void update(NewsEntity newsEntity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, newsEntity.getTitle());
            preparedStatement.setString(2, newsEntity.getDescription());
            preparedStatement.setString(3, newsEntity.getContent());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(5, newsEntity.getImage());
            preparedStatement.setInt(6, newsEntity.getStatus().getId());

            preparedStatement.setLong(7, newsEntity.getNewsId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoExceptionUpdate("Error updating values in table", throwables);
        }
    }

    public boolean updateStatus(Integer newsId, Integer statusId, String reason_rej) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_STATUS_SQL)) {
            preparedStatement.setInt(1, statusId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(3, reason_rej);
            preparedStatement.setInt(4, newsId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoExceptionUpdate("Error updating status in table", throwables);
        }
    }

    @Override
    public Optional<NewsEntity> findById(Long id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    @Override
    public Optional<NewsEntity> findById(Long id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            NewsEntity newsEntity = null;
            if (resultSet.next()) {
                newsEntity = buildNews(resultSet);
            }
            return Optional.ofNullable(newsEntity);
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    @Override
    public List<NewsEntity> findAllByFilter(NewsDto filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.getTitle() != null) {
            whereSql.add("n.title LIKE ?");
            parameters.add("%" + filter.getTitle() + "%");
        }
        if (filter.getDescription() != null) {
            whereSql.add("n.description LIKE ?");
            parameters.add("%" + filter.getDescription() + "%");
        }
        if (filter.getContent() != null) {
            whereSql.add("n.content LIKE ?");
            parameters.add("%" + filter.getContent() + "%");
        }
        if (filter.getCreatedAt() != null) {
            whereSql.add("n.created_at LIKE ?");
            parameters.add("%" + filter.getCreatedAt() + "%");
        }
        if (filter.getUpdatedAt() != null) {
            whereSql.add("n.updated_at LIKE ?");
            parameters.add("%" + filter.getUpdatedAt() + "%");
        }
        if (filter.getUserId() != null) {
            whereSql.add("n.user_id = ?");
            parameters.add(filter.getUserId());
        }
        if (filter.getCategoryId() != null) {
            whereSql.add("n.category_id = ?");
            parameters.add(filter.getCategoryId());
        }
        if (filter.getStatusId() != null) {
            whereSql.add("n.status_id = ?");
            parameters.add(filter.getStatusId());
        }
        if (filter.getReasonRej() != null) {
            whereSql.add("n.reason_rej = ?");
            parameters.add(filter.getReasonRej());
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
            List<NewsEntity> newsEntities = new ArrayList<>();
            while (resultSet.next()) {
                newsEntities.add(buildNews(resultSet));
            }
            return newsEntities;
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching for values in table", throwables);
        }
    }

    @Override
    public List<NewsEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<NewsEntity> news = new ArrayList<>();
            while (resultSet.next()) {
                news.add(buildNews(resultSet));
            }
            return news;
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching for values in table", throwables);
        }
    }

    @Override
    public List<NewsEntity> findByCategoryId(Integer categoryId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_CATEGORY_ID)) {
            preparedStatement.setInt(1, categoryId);

            try (var resultSet = preparedStatement.executeQuery()) {
                List<NewsEntity> news = new ArrayList<>();
                while (resultSet.next()) {
                    news.add(buildNews(resultSet));
                }
                return news;
            }
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching news by category ID", throwables);
        }
    }

    @Override
    public List<NewsEntity> findByStatusId(Integer statusId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_STATUS_ID)) {
            preparedStatement.setInt(1, statusId);

            try (var resultSet = preparedStatement.executeQuery()) {
                List<NewsEntity> news = new ArrayList<>();
                while (resultSet.next()) {
                    news.add(buildNews(resultSet));
                }
                return news;
            }
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching news by status ID", throwables);
        }
    }

    private NewsEntity buildNews(ResultSet resultSet) throws SQLException {
        return new NewsEntity(
                resultSet.getLong(NEWS_ID),
                resultSet.getString(NEWS_TITLE),
                resultSet.getString(NEWS_DESCRIPTION),
                resultSet.getString(NEWS_CONTENT),
                resultSet.getTimestamp(NEWS_CREATED_AT).toLocalDateTime(),
                resultSet.getTimestamp(NEWS_UPDATE_AT).toLocalDateTime(),
                resultSet.getString(NEWS_IMAGE),
                userDaoImpl.findById(resultSet.getInt(FOREIGN_PORTAL_ID),
                        resultSet.getStatement().getConnection()).orElse(null),
                categoryDaoImpl.findById(resultSet.getInt(FOREIGN_CATEGORY_ID),
                        resultSet.getStatement().getConnection()).orElse(null),
                statusDaoImpl.findById(resultSet.getInt(FOREIGN_STATUS_ID),
                        resultSet.getStatement().getConnection()).orElseThrow().getStatus(),
                resultSet.getString(NEWS_REASON_REJ)
        );
    }
}
