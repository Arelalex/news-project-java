package db.mapper.impl;

import db.dto.CategoryFilter;
import db.entity.CategoryEntity;
import db.mapper.CategoryMapper;

public class CategoryMapperImpl implements CategoryMapper {

    private static CategoryMapperImpl instance;

    private CategoryMapperImpl() {
    }

    public static CategoryMapperImpl getInstance() {
        if (instance == null) {
            instance = new CategoryMapperImpl();
        }
        return instance;
    }

    @Override
    public CategoryEntity toEntity(CategoryFilter dto) {
        return CategoryEntity
                .builder()
                .id(dto.getId())
                .category(dto.getCategory())
                .build();
    }

    @Override
    public CategoryFilter toDto(CategoryEntity entity) {
        return CategoryFilter
                .builder()
                .id(entity.getId())
                .category(entity.getCategory())
                .build();
    }
}
