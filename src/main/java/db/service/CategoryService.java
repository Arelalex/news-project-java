package db.service;

import db.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto findById(Integer id);

    List<CategoryDto> findAll();

    CategoryDto save(CategoryDto dto);

    CategoryDto update(CategoryDto dto);

    void delete(CategoryDto dto);

    List<CategoryDto> findAllByFilter(CategoryDto filter);
}
