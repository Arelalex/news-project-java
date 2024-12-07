package db.dao;

import db.dto.NewsDto;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface NewsDao<K, E> extends Dao<K, E> {

    Optional<E> findById(K id, Connection connection);

    List<E> findAllByFilter(NewsDto filter);

    List<E> findByCategoryId(Integer categoryId);
}
