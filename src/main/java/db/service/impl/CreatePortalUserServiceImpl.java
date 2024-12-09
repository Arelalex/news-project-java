package db.service.impl;

import db.dao.impl.PortalUserDaoImpl;
import db.dto.CreatePortalUserDto;
import db.exception.ValidationException;
import db.mapper.impl.CreateUserMapperImpl;
import db.service.CreatePortalUserService;
import db.service.ImageService;
import db.validator.CreateUserValidator;
import lombok.SneakyThrows;

public class CreatePortalUserServiceImpl implements CreatePortalUserService {

    private static CreatePortalUserServiceImpl instance;
    private final PortalUserDaoImpl portalUserDao = PortalUserDaoImpl.getInstance();
    private final CreateUserMapperImpl portalUserMapper = CreateUserMapperImpl.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    private CreatePortalUserServiceImpl() {
    }

    public static synchronized CreatePortalUserServiceImpl getInstance() {
        if (instance == null) {
            instance = new CreatePortalUserServiceImpl();
        }
        return instance;
    }

    @SneakyThrows
    public Integer create(CreatePortalUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var portalUserEntity = portalUserMapper.toEntity(userDto);
        imageService.upload(portalUserEntity.getImage(), userDto.getImage().getInputStream());
        portalUserDao.save(portalUserEntity);

        return portalUserEntity.getUserId();
    }
}
