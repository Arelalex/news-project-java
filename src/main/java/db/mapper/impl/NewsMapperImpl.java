package db.mapper.impl;

import db.dto.NewsFilter;
import db.entity.NewsEntity;
import db.mapper.NewsMapper;

public class NewsMapperImpl implements NewsMapper {

    private static NewsMapperImpl instance;

    private NewsMapperImpl() {
    }

    public static NewsMapperImpl getInstance() {
        if (instance == null) {
            instance = new NewsMapperImpl();
        }
        return instance;
    }

    @Override
    public NewsEntity toEntity(NewsFilter dto) {
        return NewsEntity
                .builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .createAt(dto.getCreatedAt())
                .updateAt(dto.getUpdateAt())
                .image(dto.getContent())
                .build();
    }

    @Override
    public NewsFilter toDto(NewsEntity entity) {
        return NewsFilter
                .builder()
                .title(entity.getTitle())
                .description(entity.getDescription())
                .content(entity.getContent())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .image(entity.getContent())
                .build();
    }
}
