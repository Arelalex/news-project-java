package db.service.impl;

import db.dao.impl.NewsDaoImpl;
import db.dto.NewsDto;
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
    public NewsDto findById(Long id) {
        return newsDao.findById(id)
                .map(news -> NewsDto.builder()
                        .newsId(news.getNewsId())
                        .title(news.getTitle())
                        .description(news.getDescription())
                        .content(news.getContent())
                        .createdAt(news.getCreatedAt())
                        .updatedAt(news.getUpdatedAt())
                        .image(news.getImage())
                        .user(news.getUser())
                        .category(news.getCategory())
                        .status(news.getStatus())
                        .userId(news.getUser().getUserId())
                        .build()
                )
                .orElse(null);
    }

    @Override
    public List<NewsDto> findAll() {
        return newsDao.findAll()
                .stream()
                .map(entity -> newsMapper.toDto(entity))
                .toList();
    }

    @Override
    public NewsDto save(NewsDto dto) {
        return null;
    }

    @Override
    public NewsDto update(NewsDto dto) {
        return null;
    }

    @Override
    public void delete(NewsDto dto) {

    }

    @Override
    public List<NewsDto> findAllByFilter(NewsDto filter) {
        return newsDao.findAllByFilter(filter)
                .stream()
                .map(newsMapper::toDto)
                .toList();
    }

    @Override
    public List<NewsDto> findByCategoryId(Integer categoryId) {
        return newsDao.findByCategoryId(categoryId)
                .stream()
                .map(news -> NewsDto.builder()
                        .newsId(news.getNewsId())
                        .title(news.getTitle())
                        .description(news.getDescription())
                        .content(news.getContent())
                        .createdAt(news.getCreatedAt())
                        .updatedAt(news.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
