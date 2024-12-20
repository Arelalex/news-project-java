package db.dao;

import db.dto.PortalUserDto;
import db.entity.PortalUserEntity;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface PortalUserDao<K, E> extends Dao<K, E> {

    Optional<PortalUserEntity> findById(K id, Connection connection);

    List<PortalUserEntity> findAllByFilter(PortalUserDto filter);

    Optional<PortalUserEntity> findByEmailAndPassword(String email, String password);
}
