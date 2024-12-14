package db.mapper.impl;

import db.dto.CategoryDto;
import db.dto.EditNewsDto;
import db.dto.NewsDto;
import db.dto.PortalUserDto;
import db.entity.CategoryEntity;
import db.entity.NewsEntity;
import db.entity.PortalUserEntity;
import db.mapper.EditNewsMapper;
import db.service.CategoryService;
import db.service.impl.CategoryServiceImpl;
import db.service.impl.NewsServiceImpl;
import db.service.impl.PortalUserServiceImpl;

public class EditNewsMapperImpl implements EditNewsMapper {

    private static final String IMAGE_FOLDER = "news/";
    private static EditNewsMapperImpl instance;
    private static CategoryService categoryService = CategoryServiceImpl.getInstance();
    private static PortalUserServiceImpl portalUserService = PortalUserServiceImpl.getInstance();
    private static PortalUserMapperImpl portalUserMapper = PortalUserMapperImpl.getInstance();
    private static CategoryMapperImpl categoryMapper = CategoryMapperImpl.getInstance();
    private static NewsMapperImpl newsMapper = NewsMapperImpl.getInstance();
    private static NewsServiceImpl newsService = NewsServiceImpl.getInstance();


    private EditNewsMapperImpl() {
    }

    public static EditNewsMapperImpl getInstance() {
        if (instance == null) {
            instance = new EditNewsMapperImpl();
        }
        return instance;
    }

    @Override
    public NewsEntity toEntity(EditNewsDto dto) {

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

        NewsDto newsDto = newsService.findById(Long.valueOf(dto.getNewsId()));
        if (newsDto == null) {
            throw new IllegalArgumentException("Mews not found");
        }
        NewsEntity newsEntity = newsMapper.toEntity(newsDto);

        return NewsEntity
                .builder()
                .newsId(newsEntity.getNewsId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .image(IMAGE_FOLDER + dto.getImage().getSubmittedFileName())
                .user(userEntity)
                .category(categoryEntity)
                .build();
    }

    @Override
    public EditNewsDto toDto(NewsEntity entity) {
        return null;
    }
}
