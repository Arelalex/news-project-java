package db.service;

import db.dto.NewsFilter;

import java.util.List;

public interface NewsService {

    NewsFilter findById(Long id);

    List<NewsFilter> findAll();

    NewsFilter save(NewsFilter dto);

    NewsFilter update(NewsFilter dto);

    void delete(NewsFilter dto);

    List<NewsFilter> findAllByFilter(NewsFilter filter);

    List<NewsFilter> findByCategoryId(Integer categoryId);
}
