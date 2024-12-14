package db.mapper.impl;

import db.dto.CategoryDto;
import db.dto.CreateNewsDto;
import db.dto.PortalUserDto;
import db.entity.CategoryEntity;
import db.entity.NewsEntity;
import db.entity.PortalUserEntity;
import db.mapper.CreateNewsMapper;
import db.service.CategoryService;
import db.service.impl.CategoryServiceImpl;
import db.service.impl.PortalUserServiceImpl;

public class CreateNewsMapperImpl implements CreateNewsMapper {

    private static final String IMAGE_FOLDER = "news/";
    private static CreateNewsMapperImpl instance;
    private static CategoryService categoryService = CategoryServiceImpl.getInstance();
    private static PortalUserServiceImpl portalUserService = PortalUserServiceImpl.getInstance();
    private static PortalUserMapperImpl portalUserMapper = PortalUserMapperImpl.getInstance();
    private static CategoryMapperImpl categoryMapper = CategoryMapperImpl.getInstance();

    private CreateNewsMapperImpl() {
    }

    public static CreateNewsMapperImpl getInstance() {
        if (instance == null) {
            instance = new CreateNewsMapperImpl();
        }
        return instance;
    }

    @Override
    public NewsEntity toEntity(CreateNewsDto dto) {

        PortalUserDto userDto = portalUserService.findById(Integer.valueOf(dto.getUserId()));
        if (userDto == null) {
            throw new IllegalArgumentException("User not found");
        }
        PortalUserEntity userEntity = portalUserMapper.toEntity(userDto);

        CategoryDto categoryDto = categoryService.findById(Integer.valueOf(dto.getCategoryId()));
        if (categoryDto == null) {
            throw new IllegalArgumentException("Category not found");
        }
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDto);


        return NewsEntity
                .builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .image(IMAGE_FOLDER + dto.getImage().getSubmittedFileName())
                .user(userEntity)
                .category(categoryEntity)
                .build();
    }

    @Override
    public CreateNewsDto toDto(NewsEntity entity) {
        return null;
    }
}
