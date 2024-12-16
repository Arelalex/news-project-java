package db.service.impl;

import db.dao.impl.CategoryDaoImpl;
import db.dto.CategoryDto;
import db.entity.CategoryEntity;
import db.mapper.CategoryMapper;
import db.mapper.impl.CategoryMapperImpl;
import db.service.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private static CategoryServiceImpl instance;
    private final CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
    private final CategoryMapper categoryMapper = CategoryMapperImpl.getInstance();

    private CategoryServiceImpl() {
    }

    public static synchronized CategoryServiceImpl getInstance() {
        if (instance == null) {
            instance = new CategoryServiceImpl();
        }
        return instance;
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryDao.findById(id)
                .map(category -> CategoryDto.builder()
                        .categoryId(category.getCategoryId())
                        .category(category.getCategory())
                        .build()
                )
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryDao.findAll()
                .stream()
                .map(category -> CategoryDto.builder()
                        .categoryId(category.getCategoryId())
                        .category(category.getCategory())
                        .build()
                )
                .toList();
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        return null;
    }

    @Override
    public CategoryDto update(CategoryDto dto) {
        return null;
    }

    @Override
    public void delete(CategoryDto dto) {

    }

    @Override
    public List<CategoryDto> findAllByFilter(CategoryDto filter) {
        return categoryDao.findAllByFilter(filter)
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    public Optional<CategoryEntity> findByName(String category) {
        return categoryDao.findByName(category);
    }
}
