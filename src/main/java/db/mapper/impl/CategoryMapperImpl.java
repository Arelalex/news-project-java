package db.mapper.impl;

import db.dto.CategoryDto;
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
    public CategoryEntity toEntity(CategoryDto dto) {
        return CategoryEntity
                .builder()
                .categoryId(dto.getCategoryId())
                .category(dto.getCategory())
                .build();
    }

    @Override
    public CategoryDto toDto(CategoryEntity entity) {
        return CategoryDto
                .builder()
                .categoryId(entity.getCategoryId())
                .category(entity.getCategory())
                .build();
    }
}
