package db.mapper.impl;

import db.dto.CommentDto;
import db.entity.CommentEntity;
import db.mapper.CommentMapper;

public class CommentMapperImpl implements CommentMapper {

    private static CommentMapperImpl instance;

    private CommentMapperImpl() {
    }

    public static CommentMapperImpl getInstance() {
        if (instance == null) {
            instance = new CommentMapperImpl();
        }
        return instance;
    }

    @Override
    public CommentEntity toEntity(CommentDto dto) {
        return CommentEntity
                .builder()
                .commentId(dto.getCommentId())
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .attachment(dto.getAttachment())
                .news(dto.getNews())
                .user(dto.getUser())
                .status(dto.getStatus())
                .reasonRej(dto.getReasonRej())
                .build();
    }

    @Override
    public CommentDto toDto(CommentEntity entity) {
        return CommentDto
                .builder()
                .commentId(entity.getCommentId())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .attachment(entity.getAttachment())
                .newsId(entity.getNews().getNewsId())
                .news(entity.getNews())
                .userId(entity.getUser().getUserId())
                .user(entity.getUser())
                .statusId(entity.getStatus().getId())
                .status(entity.getStatus())
                .reasonRej(entity.getReasonRej())
                .build();
    }
}
