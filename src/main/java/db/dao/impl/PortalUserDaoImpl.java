package db.dao.impl;

import db.dao.PortalUserDao;
import db.dto.PortalUserDto;
import db.entity.PortalUserEntity;
import db.exception.*;
import db.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class PortalUserDaoImpl implements PortalUserDao<Integer, PortalUserEntity> {

    private static final String PORTAL_USER_ID = "user_id";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String NICKNAME = "nickname";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String IMAGE = "image";
    private static final String FOREIGN_ROLE_ID = "role_id";

    private static final String DELETE_SQL = """
            DELETE FROM portal_user
            WHERE user_id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO portal_user (first_name, last_name, nickname, email, password, image, role_id) 
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE portal_user
            SET first_name = ?,
                last_name = ?,
                nickname = ?,
                email = ?,
                password = ?,
                image = ?,
                role_id = ?
            WHERE user_id = ?
            """;

    private static final String FIND_ALL_SQL = """
                        SELECT u.user_id,
                               u.first_name,
                               u.last_name,
                               u.nickname,
                               u.email,
                            u.password,
                            u.image,
                            u.role_id,            
                            r.role
                        FROM portal_user u
                        LEFT JOIN role r
                        ON u.role_id = r.role_id
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE u.user_id = ?
            """;

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL = """
            SELECT user_id,
                first_name,
                last_name,
                nickname,
                email,
                password,
                image,
                role_id
                FROM portal_user
                WHERE email = ? AND password = ?
            """;

    private static PortalUserDaoImpl instance;
    private final RoleDaoImpl roleDaoImpl = RoleDaoImpl.getInstance();
    ;

    private PortalUserDaoImpl() {
    }

    public static synchronized PortalUserDaoImpl getInstance() {
        if (instance == null) {
            instance = new PortalUserDaoImpl();
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
    public PortalUserEntity save(PortalUserEntity portalUser) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, portalUser.getFirstName());
            preparedStatement.setString(2, portalUser.getLastName());
            preparedStatement.setString(3, portalUser.getNickname());
            preparedStatement.setString(4, portalUser.getEmail());
            preparedStatement.setString(5, portalUser.getPassword());
            preparedStatement.setString(6, portalUser.getImage());
            preparedStatement.setObject(7, portalUser.getRole().getId());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                portalUser.setUserId(generatedKeys.getInt("user_id"));
            }
            return portalUser;
        } catch (SQLException throwables) {
            throw new DaoExceptionInsert("Error inserting values into table", throwables);
        }
    }

    @Override
    public void update(PortalUserEntity portalUser) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, portalUser.getFirstName());
            preparedStatement.setString(2, portalUser.getLastName());
            preparedStatement.setString(3, portalUser.getNickname());
            preparedStatement.setString(4, portalUser.getEmail());
            preparedStatement.setString(5, portalUser.getPassword());
            preparedStatement.setString(6, portalUser.getImage());
            preparedStatement.setObject(7, portalUser.getRole().getId());

            preparedStatement.setInt(8, portalUser.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoExceptionUpdate("Error updating values in table", throwables);
        }
    }

    @Override
    public Optional<PortalUserEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    @Override
    public Optional<PortalUserEntity> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            PortalUserEntity portalUser = null;
            if (resultSet.next()) {
                portalUser = buildUser(resultSet);

            }
            return Optional.ofNullable(portalUser);
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    @Override
    public List<PortalUserEntity> findAllByFilter(PortalUserDto filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.getFirstName() != null) {
            whereSql.add("first_name LIKE ?");
            parameters.add("%" + filter.getFirstName() + "%");
        }
        if (filter.getLastName() != null) {
            whereSql.add("last_name = ?");
            parameters.add("%" + filter.getLastName() + "%");
        }
        if (filter.getNickname() != null) {
            whereSql.add("nickname = ?");
            parameters.add("%" + filter.getNickname() + "%");
        }
        if (filter.getEmail() != null) {
            whereSql.add("email = ?");
            parameters.add("%" + filter.getEmail() + "%");
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
            List<PortalUserEntity> portalUsers = new ArrayList<>();
            while (resultSet.next()) {
                portalUsers.add(buildUser(resultSet));
            }
            return portalUsers;
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching for values in table", throwables);
        }
    }

    @Override
    public Optional<PortalUserEntity> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            var resultSet = preparedStatement.executeQuery();
            PortalUserEntity portalUser = null;
            if (resultSet.next()) {
                portalUser = buildUser(resultSet);
            }
            return Optional.ofNullable(portalUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PortalUserEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<PortalUserEntity> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching for values in table", throwables);
        }
    }

    private PortalUserEntity buildUser(ResultSet resultSet) throws SQLException {
        return new PortalUserEntity(
                resultSet.getInt(PORTAL_USER_ID),
                resultSet.getString(USER_FIRST_NAME),
                resultSet.getString(USER_LAST_NAME),
                resultSet.getString(NICKNAME),
                resultSet.getString(USER_EMAIL),
                resultSet.getString(USER_PASSWORD),
                resultSet.getString(IMAGE),
                roleDaoImpl.findById(resultSet.getInt(FOREIGN_ROLE_ID),
                        resultSet.getStatement().getConnection()).orElseThrow().getRole()
        );
    }
}
