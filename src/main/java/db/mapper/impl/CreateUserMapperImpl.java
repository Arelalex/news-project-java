package db.mapper.impl;

import db.dto.CreatePortalUserDto;
import db.entity.PortalUserEntity;
import db.enums.Roles;
import db.mapper.CreateUserMapper;

public class CreateUserMapperImpl implements CreateUserMapper {

    private static CreateUserMapperImpl instance;

    private CreateUserMapperImpl() {
    }

    public static CreateUserMapperImpl getInstance() {
        if (instance == null) {
            instance = new CreateUserMapperImpl();
        }
        return instance;
    }

    @Override
    public PortalUserEntity toEntity(CreatePortalUserDto dto) {
        return PortalUserEntity
                .builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .image(null) //исправить когда буду реализовывать картинки
                .role(dto.getRole())
                .build();
    }

    @Override
    public CreatePortalUserDto toDto(PortalUserEntity entity) {
        return null;
    }
}
