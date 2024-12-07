package db.mapper;

import db.dto.CreatePortalUserDto;
import db.dto.PortalUserDto;
import db.entity.PortalUserEntity;

public interface CreateUserMapper extends BaseMapper<PortalUserEntity, CreatePortalUserDto>{

    PortalUserEntity toEntity(CreatePortalUserDto dto);
}
