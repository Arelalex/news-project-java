package db.dao;

import db.dto.StatusFilter;
import db.entity.StatusEntity;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface StatusDao<K, E> extends Dao<K, StatusEntity> {

    Optional<E> findById(K id, Connection connection);

    List<E> findAllByFilter(StatusFilter filter);
}
