package db.mapper;

import db.dto.CreatePortalUserDto;
import db.entity.PortalUserEntity;

public interface BaseMapper<E, F> {

    E toEntity(F dto);

    F toDto(E entity);

}
