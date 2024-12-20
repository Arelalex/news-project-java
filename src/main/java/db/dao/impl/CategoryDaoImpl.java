package db.dao.impl;

import db.dao.CategoryDao;
import db.dto.CategoryDto;
import db.entity.CategoryEntity;
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

public class CategoryDaoImpl implements CategoryDao<Integer, CategoryEntity> {

    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_CATEGORY = "category";
    private static final String DELETE_SQL = """
            DELETE FROM category
            WHERE category_id = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO category (category) 
            VALUES (?);
            """;
    private static final String UPDATE_SQL = """
            UPDATE category
            SET category = ?
            WHERE category_id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT category_id,
                category
            FROM category 
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE category.category_id = ?
            """;

    private static final String FIND_BY_NAME_SQL = """
            SELECT category_id,
                category
            FROM category WHERE category = ?
            """;

    private static CategoryDaoImpl instance;

    private CategoryDaoImpl() {
    }

    public static synchronized CategoryDaoImpl getInstance() {
        if (instance == null) {
            instance = new CategoryDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoExceptionDelete("Error deleting values from table", throwables);
        }
    }

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, categoryEntity.getCategory());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                categoryEntity.setCategoryId(generatedKeys.getInt("id"));
            }
            return categoryEntity;
        } catch (SQLException throwables) {
            throw new DaoExceptionInsert("Error inserting values into table", throwables);
        }
    }

    @Override
    public void update(CategoryEntity categoryEntity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, categoryEntity.getCategory());
            preparedStatement.setLong(2, categoryEntity.getCategoryId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoExceptionUpdate("Error updating values in table", throwables);
        }
    }

    @Override
    public Optional<CategoryEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    @Override
    public Optional<CategoryEntity> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            CategoryEntity categoryEntity = null;
            if (resultSet.next()) {
                categoryEntity = buildCategory(resultSet);
            }

            return Optional.ofNullable(categoryEntity);
        } catch (SQLException throwables) {
            throw new DaoExceptionFindById("Error searching values by ID in table", throwables);
        }
    }

    @Override
    public List<CategoryEntity> findAllByFilter(CategoryDto filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.getCategory() != null) {
            whereSql.add("category LIKE ?");
            parameters.add("%" + filter.getCategory() + "%");
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
            List<CategoryEntity> categoryEntities = new ArrayList<>();
            while (resultSet.next()) {
                categoryEntities.add(buildCategory(resultSet));
            }
            return categoryEntities;
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching for values in table", throwables);
        }
    }

    @Override
    public List<CategoryEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<CategoryEntity> categoryEntities = new ArrayList<>();
            while (resultSet.next()) {
                categoryEntities.add(buildCategory(resultSet));
            }
            return categoryEntities;
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching for values in table", throwables);
        }
    }

    public Optional<CategoryEntity> findByName(String category) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_NAME_SQL)) {
            preparedStatement.setString(1, category);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new CategoryEntity(
                            resultSet.getInt("category_id"),
                            resultSet.getString("category")
                    ));
                }
            }
        } catch (SQLException throwables) {
            throw new DaoExceptionFindAll("Error searching category by category ID", throwables);
        }
        return Optional.empty();
    }

    private CategoryEntity buildCategory(ResultSet resultSet) throws SQLException {
        return new CategoryEntity(
                resultSet.getInt(CATEGORY_ID),
                resultSet.getString(CATEGORY_CATEGORY)
        );
    }

}
