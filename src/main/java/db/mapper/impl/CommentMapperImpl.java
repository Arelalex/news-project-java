package db.mapper.impl;

import db.dto.CommentFilter;
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
    public CommentEntity toEntity(CommentFilter dto) {
        return CommentEntity
                .builder()
                .content(dto.getContent())
                .createAt(dto.getCreatedAt())
                .updateAt(dto.getUpdateAt())
                .build();
    }

    @Override
    public CommentFilter toDto(CommentEntity entity) {
        return CommentFilter
                .builder()
                .content(entity.getContent())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .build();
    }
}
