package db.mapper.impl;

import db.dto.CreateCommentDto;
import db.dto.NewsDto;
import db.dto.PortalUserDto;
import db.entity.CommentEntity;
import db.entity.NewsEntity;
import db.entity.PortalUserEntity;
import db.mapper.CreateCommentMapper;
import db.service.NewsService;
import db.service.impl.NewsServiceImpl;
import db.service.impl.PortalUserServiceImpl;

public class CreateCommentMapperImpl implements CreateCommentMapper {

    private static final String IMAGE_FOLDER = "comments/";
    private static CreateCommentMapperImpl instance;
    private static NewsService newsService = NewsServiceImpl.getInstance();
    private static PortalUserServiceImpl portalUserService = PortalUserServiceImpl.getInstance();
    private static PortalUserMapperImpl portalUserMapper = PortalUserMapperImpl.getInstance();
    private static NewsMapperImpl newsMapper = NewsMapperImpl.getInstance();

    private CreateCommentMapperImpl() {
    }

    public static CreateCommentMapperImpl getInstance() {
        if (instance == null) {
            instance = new CreateCommentMapperImpl();
        }
        return instance;
    }

    @Override
    public CommentEntity toEntity(CreateCommentDto dto) {

        PortalUserDto userDto = portalUserService.findById(Integer.valueOf(dto.getUserId()));
        if (userDto == null) {
            throw new IllegalArgumentException("User not found");
        }
        PortalUserEntity userEntity = portalUserMapper.toEntity(userDto);

        NewsDto newsDto = newsService.findById(Long.valueOf(dto.getNewsId()));
        if (newsDto == null) {
            throw new IllegalArgumentException("News not found");
        }
        NewsEntity newsEntity = newsMapper.toEntity(newsDto);


        return CommentEntity
                .builder()
                .content(dto.getContent())
                .attachment(IMAGE_FOLDER + dto.getAttachment().getSubmittedFileName())
                .news(newsEntity)
                .user(userEntity)
                .build();
    }

    @Override
    public CreateCommentDto toDto(CommentEntity entity) {
        return null;
    }
}
