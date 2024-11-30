package db.service.impl;

import db.dao.impl.CommentDaoImpl;
import db.dto.CommentFilter;
import db.mapper.CommentMapper;
import db.mapper.impl.CommentMapperImpl;
import db.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static CommentServiceImpl instance;
    private final CommentDaoImpl commentDao = CommentDaoImpl.getInstance();
    private final CommentMapper commentMapper = CommentMapperImpl.getInstance();

    private CommentServiceImpl() {
    }

    public static synchronized CommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new CommentServiceImpl();
        }
        return instance;
    }

    @Override
    public CommentFilter findById(Long id) {
        return commentDao.findById(id)
                .map(comment -> CommentFilter.builder()
                        .content(comment.getContent())
                        .createdAt(comment.getCreateAt())
                        .updateAt(comment.getUpdateAt())
                        .build()
                )
                .orElse(null);
    }

    @Override
    public List<CommentFilter> findAll() {
        return commentDao.findAll()
                .stream()
                .map(entity -> commentMapper.toDto(entity))
                .toList();
    }

    @Override
    public CommentFilter save(CommentFilter dto) {
        return null;
    }

    @Override
    public CommentFilter update(CommentFilter dto) {
        return null;
    }

    @Override
    public void delete(CommentFilter dto) {

    }

    @Override
    public List<CommentFilter> findAllByFilter(CommentFilter filter) {
        return commentDao.findAll()
                .stream()
                .map(entity -> commentMapper.toDto(entity))
                .toList();
    }
}
