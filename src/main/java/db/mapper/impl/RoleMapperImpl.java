package db.mapper.impl;

import db.dto.RoleDto;
import db.entity.RoleEntity;
import db.enums.Roles;
import db.mapper.RoleMapper;

public class RoleMapperImpl implements RoleMapper {

    private static RoleMapperImpl instance;

    private RoleMapperImpl() {
    }

    public static RoleMapperImpl getInstance() {
        if (instance == null) {
            instance = new RoleMapperImpl();
        }
        return instance;
    }

    @Override
    public RoleEntity toEntity(RoleDto dto) {
        return RoleEntity
                .builder()
                .role(Roles.valueOf(dto.getRole()))
                .build();
    }

    @Override
    public RoleDto toDto(RoleEntity entity) {
        return RoleDto
                .builder()
                .role(String.valueOf(entity.getRole()))
                .build();
    }
}
