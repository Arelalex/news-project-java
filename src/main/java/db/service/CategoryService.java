package db.service;

import db.dto.CategoryFilter;

import java.util.List;

public interface CategoryService {

    CategoryFilter findById(Integer id);

    List<CategoryFilter> findAll();

    CategoryFilter save(CategoryFilter dto);

    CategoryFilter update(CategoryFilter dto);

    void delete(CategoryFilter dto);

    List<CategoryFilter> findAllByFilter(CategoryFilter filter);
}
