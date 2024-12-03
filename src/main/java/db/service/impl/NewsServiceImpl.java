package db.service.impl;

import db.dao.impl.NewsDaoImpl;
import db.dto.NewsFilter;
import db.mapper.NewsMapper;
import db.mapper.impl.NewsMapperImpl;
import db.service.NewsService;

import java.util.List;
import java.util.stream.Collectors;

public class NewsServiceImpl implements NewsService {

    private static NewsServiceImpl instance;
    private final NewsDaoImpl newsDao = NewsDaoImpl.getInstance();
    private final NewsMapper newsMapper = NewsMapperImpl.getInstance();

    private NewsServiceImpl() {
    }

    public static synchronized NewsServiceImpl getInstance() {
        if (instance == null) {
            instance = new NewsServiceImpl();
        }
        return instance;
    }

    @Override
    public NewsFilter findById(Long id) {
        return newsDao.findById(id)
                .map(news -> NewsFilter.builder()
                        .title(news.getTitle())
                        .description(news.getDescription())
                        .content(news.getContent())
                        .createdAt(news.getCreateAt())
                        .updateAt(news.getUpdateAt())
                        .image(news.getImage())
                        .build()
                )
                .orElse(null);
    }

    @Override
    public List<NewsFilter> findAll() {
        return newsDao.findAll()
                .stream()
                .map(entity -> newsMapper.toDto(entity))
                .toList();
    }

    @Override
    public NewsFilter save(NewsFilter dto) {
        return null;
    }

    @Override
    public NewsFilter update(NewsFilter dto) {
        return null;
    }

    @Override
    public void delete(NewsFilter dto) {

    }

    @Override
    public List<NewsFilter> findAllByFilter(NewsFilter filter) {
        return newsDao.findAllByFilter(filter)
                .stream()
                .map(newsMapper::toDto)
                .toList();
    }

    @Override
    public List<NewsFilter> findByCategoryId(Integer categoryId) {
        return newsDao.findByCategoryId(categoryId)
                .stream()
                .map(news -> new NewsFilter(news.getTitle(), news.getDescription()))
                .collect(Collectors.toList());
    }
}
