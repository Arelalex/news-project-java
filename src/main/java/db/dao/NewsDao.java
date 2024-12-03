package db.dao;

import db.dto.NewsFilter;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface NewsDao<K, E> extends Dao<K, E> {

    Optional<E> findById(K id, Connection connection);

    List<E> findAllByFilter(NewsFilter filter);

    List<E> findByCategoryId(Integer categoryId);
}
