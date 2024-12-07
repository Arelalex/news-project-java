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
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .news(dto.getNews())
                .build();
    }

    @Override
    public CommentDto toDto(CommentEntity entity) {
        return CommentDto
                .builder()
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .newsId(entity.getNews().getNewsId())
                .news(entity.getNews())
                .user(entity.getUser())
                .status(entity.getStatus())
                .build();
    }
}
