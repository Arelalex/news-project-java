package db.mapper.impl;

import db.dto.CreatePortalUserDto;
import db.entity.PortalUserEntity;
import db.mapper.CreateUserMapper;

public class CreateUserMapperImpl implements CreateUserMapper {

    private static final String IMAGE_FOLDER = "users/";
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
                .image(IMAGE_FOLDER + dto.getImage().getSubmittedFileName())
                .role(dto.getRole())
                .build();
    }

    @Override
    public CreatePortalUserDto toDto(PortalUserEntity entity) {
        return null;
    }
}
