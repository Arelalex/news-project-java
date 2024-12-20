package db.mapper.impl;

import db.dto.CommentDto;
import db.dto.EditCommentDto;
import db.dto.NewsDto;
import db.dto.PortalUserDto;
import db.entity.CommentEntity;
import db.entity.NewsEntity;
import db.entity.PortalUserEntity;
import db.mapper.EditCommentMapper;
import db.service.impl.CommentServiceImpl;
import db.service.impl.NewsServiceImpl;
import db.service.impl.PortalUserServiceImpl;

public class EditCommentMapperImpl implements EditCommentMapper {

    private static final String IMAGE_FOLDER = "comments/";
    private static EditCommentMapperImpl instance;
    private static CommentServiceImpl commentService = CommentServiceImpl.getInstance();
    private static PortalUserServiceImpl portalUserService = PortalUserServiceImpl.getInstance();
    private static PortalUserMapperImpl portalUserMapper = PortalUserMapperImpl.getInstance();
    private static NewsMapperImpl newsMapper = NewsMapperImpl.getInstance();
    private static CommentMapperImpl commentMapper = CommentMapperImpl.getInstance();
    private static NewsServiceImpl newsService = NewsServiceImpl.getInstance();


    private EditCommentMapperImpl() {
    }

    public static EditCommentMapperImpl getInstance() {
        if (instance == null) {
            instance = new EditCommentMapperImpl();
        }
        return instance;
    }

    @Override
    public CommentEntity toEntity(EditCommentDto dto) {

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

        CommentDto commentDto = commentService.findById(Long.valueOf(dto.getCommentId()));
        if (commentDto == null) {
            throw new IllegalArgumentException("Comment not found");
        }
        CommentEntity commentEntity = commentMapper.toEntity(commentDto);

        return CommentEntity
                .builder()
                .commentId(commentEntity.getCommentId())
                .content(dto.getContent())
                .attachment(IMAGE_FOLDER + dto.getAttachment().getSubmittedFileName())
                .news(newsEntity)
                .user(userEntity)
                .build();
    }

    @Override
    public EditCommentDto toDto(CommentEntity entity) {
        return null;
    }
}
