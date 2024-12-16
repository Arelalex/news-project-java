package db.dao.impl;

import db.dao.StatusDao;
import db.dto.StatusDto;
import db.entity.StatusesEntity;
import db.enums.Statuses;
import db.exception.*;
import db.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class StatusDaoImpl implements StatusDao<Integer, StatusesEntity> {

    private static final String STATUS_ID = "status_id";
    private static final String STATUS_STATUS = "status";

    private static final String DELETE_SQL = """
            DELETE FROM status
            WHERE status_id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO status (status) 
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE status
            SET status = ?
            WHERE status_id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT status_id,
                status
            FROM status
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE status.status_id = ?
            """;

    private static final String FIND_BY_NAME_SQL = """
            SELECT status_id,
                status
            FROM status WHERE status = ?
            """;

    private static StatusDaoImpl instance;

    private StatusDaoImpl() {
    }

    public static synchronized StatusDaoImpl getInstance() {
        if (instance == null) {
            instance = new StatusDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoExceptionDelete("Error deleting values from table", throwables);
        }
    }

    @Override
    public StatusesEntity save(StatusesEntity statuses) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, statuses.getStatus().name());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                statuses.setStatusId(generatedKeys.getInt("id"));
            }
            return statuses;
        } catch (SQLException throwables) {
            throw new DaoExceptionInsert("Error inserting values into table", throwables);
        }
    }

    @Override
    public void update(StatusesEntity statuses) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, statuses.getStatus().name());

            preparedStatement.setLong(2, statuses.getStatusId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoExceptionUpdate("Error updating values in table", throwables);
        }
    }

    @Override
    public Optional<StatusesEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    @Override
    public Optional<StatusesEntity> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            StatusesEntity statuses = null;
            if (resultSet.next()) {
                statuses = buildStatus(resultSet);
            }
            return Optional.ofNullable(statuses);
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    public Optional<StatusesEntity> findByName(String status) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_NAME_SQL)) {
            preparedStatement.setString(1, status);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new StatusesEntity(
                            resultSet.getInt("status_id"),
                            Statuses.valueOf(resultSet.getString(STATUS_STATUS))
                    ));
                }
            }
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching status by status ID", throwables);
        }
        return Optional.empty();
    }

    @Override
    public List<StatusesEntity> findAllByFilter(StatusDto filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.getStatus() != null) {
            whereSql.add("status LIKE ?");
            parameters.add("%" + filter.getStatus() + "%");
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
            List<StatusesEntity> statusEntities = new ArrayList<>();
            while (resultSet.next()) {
                statusEntities.add(buildStatus(resultSet));
            }
            return statusEntities;
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    @Override
    public List<StatusesEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<StatusesEntity> statusEntities = new ArrayList<>();
            while (resultSet.next()) {
                statusEntities.add(buildStatus(resultSet));
            }
            return statusEntities;
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    private StatusesEntity buildStatus(ResultSet resultSet) throws SQLException {
        return new StatusesEntity(
                resultSet.getInt(STATUS_ID),
                Statuses.valueOf(resultSet.getString(STATUS_STATUS))
        );
    }
}
