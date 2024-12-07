package db.mapper.impl;

import db.dto.NewsDto;
import db.entity.CategoryEntity;
import db.entity.NewsEntity;
import db.entity.PortalUserEntity;
import db.entity.StatusEntity;
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
    public NewsEntity toEntity(NewsDto dto) {
        return NewsEntity
                .builder()
                .newsId(dto.getNewsId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .image(dto.getImage())
                .user(dto.getUser())
                .category(dto.getCategory())
                .status(dto.getStatus())
                .build();
    }

    @Override
    public NewsDto toDto(NewsEntity entity) {
        return NewsDto
                .builder()
                .newsId(entity.getNewsId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .image(entity.getContent())
                .user(entity.getUser())
                .category(entity.getCategory())
                .status(entity.getStatus())
                .userId(entity.getUser().getUserId())
                .build();
    }
}
