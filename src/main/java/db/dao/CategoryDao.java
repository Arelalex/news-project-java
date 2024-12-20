package db.dao;

import db.dto.CategoryDto;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface CategoryDao<K, E> extends Dao<K, E> {

    Optional<E> findById(K id, Connection connection);

    List<E> findAllByFilter(CategoryDto filter);
}
