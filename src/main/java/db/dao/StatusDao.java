package db.dao;

import db.dto.StatusDto;
import db.entity.StatusesEntity;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface StatusDao<K, E> extends Dao<K, StatusesEntity> {

    Optional<E> findById(K id, Connection connection);

    List<E> findAllByFilter(StatusDto filter);
}
