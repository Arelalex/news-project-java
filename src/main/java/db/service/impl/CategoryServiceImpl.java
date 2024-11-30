package db.service.impl;

import db.dao.impl.CategoryDaoImpl;
import db.dto.CategoryFilter;
import db.mapper.CategoryMapper;
import db.mapper.impl.CategoryMapperImpl;
import db.service.CategoryService;

import java.util.List;

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
    public CategoryFilter findById(Integer id) {
        return null;
    }

    @Override
    public List<CategoryFilter> findAll() {
        return categoryDao.findAll()
                .stream()
                .map(category -> CategoryFilter.builder()
                        .id(category.getId())
                        .category(category.getCategory())
                        .build()
                )
                .toList();
    }

    @Override
    public CategoryFilter save(CategoryFilter dto) {
        return null;
    }

    @Override
    public CategoryFilter update(CategoryFilter dto) {
        return null;
    }

    @Override
    public void delete(CategoryFilter dto) {

    }

    @Override
    public List<CategoryFilter> findAllByFilter(CategoryFilter filter) {
        return categoryDao.findAllByFilter(filter)
                .stream()
                .map(entity -> categoryMapper.toDto(entity))
                .toList();
    }
}
