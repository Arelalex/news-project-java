package db.service.impl;

import db.dao.impl.CommentDaoImpl;
import db.dto.CommentDto;
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
    public CommentDto findById(Long id) {
        return commentDao.findById(id)
                .map(comment -> CommentDto.builder()
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .updatedAt(comment.getUpdatedAt())
                        .build()
                )
                .orElse(null);
    }

    @Override
    public List<CommentDto> findAll() {
        return commentDao.findAll()
                .stream()
                .map(commentMapper::toDto)
                .toList();
    }

    @Override
    public CommentDto save(CommentDto dto) {
        return null;
    }

    @Override
    public CommentDto update(CommentDto dto) {
        return null;
    }

    @Override
    public void delete(CommentDto dto) {

    }

    @Override
    public List<CommentDto> findAllByFilter(CommentDto filter) {
        return commentDao.findAllByFilter(filter)
                .stream()
                .map(commentMapper::toDto)
                .toList();
    }
}
