package db.dao;

import db.dto.RoleDto;
import db.enums.Roles;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface RoleDao<K, E> extends Dao<K, E> {

    Optional<E> findById(K id, Connection connection);

    List<E> findAllByFilter(RoleDto filter);

    Optional<E> findByRole(Roles role, Connection connection);
}
