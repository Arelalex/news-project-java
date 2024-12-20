package db.service;

import db.dto.NewsDto;

import java.util.List;

public interface NewsService {

    NewsDto findById(Long id);

    List<NewsDto> findAll();

    NewsDto save(NewsDto dto);

    NewsDto update(NewsDto dto);

    void delete(NewsDto dto);

    List<NewsDto> findAllByFilter(NewsDto filter);

    List<NewsDto> findByCategoryId(Integer categoryId);

    List<NewsDto> findByStatusId(Integer statusId);

    boolean updateStatus(Long newsId, Integer statusId, String reason_rej);
}
