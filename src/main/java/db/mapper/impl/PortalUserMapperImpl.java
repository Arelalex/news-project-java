package db.mapper.impl;

import db.dto.PortalUserDto;
import db.entity.PortalUserEntity;
import db.mapper.PortalUserMapper;

public class PortalUserMapperImpl implements PortalUserMapper {

    private static PortalUserMapperImpl instance;

    private PortalUserMapperImpl() {
    }

    public static PortalUserMapperImpl getInstance() {
        if (instance == null) {
            instance = new PortalUserMapperImpl();
        }
        return instance;
    }

    @Override
    public PortalUserEntity toEntity(PortalUserDto dto) {
        return PortalUserEntity
                .builder()
                .userId(dto.getUserId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();
    }

    @Override
    public PortalUserDto toDto(PortalUserEntity portalUser) {
        return PortalUserDto
                .builder()
                .userId(portalUser.getUserId())
                .firstName(portalUser.getFirstName())
                .lastName(portalUser.getLastName())
                .nickname(portalUser.getNickname())
                .email(portalUser.getEmail())
                .role(portalUser.getRole())
                .build();
    }

}
