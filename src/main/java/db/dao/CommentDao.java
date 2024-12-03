package db.dao;

import db.dto.CommentFilter;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface CommentDao<K, E> extends Dao<K, E> {

    Optional<E> findById(K id, Connection connection);

    List<E> findAllByFilter(CommentFilter filter);
}
