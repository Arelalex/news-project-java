package db.service.impl;

import db.dao.impl.CommentDaoImpl;
import db.dto.CommentDto;
import db.exception.DaoExceptionUpdate;
import db.mapper.CommentMapper;
import db.mapper.impl.CommentMapperImpl;
import db.service.CommentService;

import java.util.List;
import java.util.NoSuchElementException;

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
                        .commentId(comment.getCommentId())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .updatedAt(comment.getUpdatedAt())
                        .attachment(comment.getAttachment())
                        .userId(comment.getUser().getUserId())
                        .user(comment.getUser())
                        .status(comment.getStatus())
                        .newsId(comment.getNews().getNewsId())
                        .news(comment.getNews())
                        .build()
                )
                .orElseThrow(() -> new NoSuchElementException("Comment not found"));
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
    public boolean updateStatus(Long commentId, Integer statusId, String reasonRej) {
        try {
            System.out.println("Updating status for commentId: " + commentId + " with statusId: " + statusId);
            return commentDao.updateStatus(commentId, statusId, reasonRej);
        } catch (DaoExceptionUpdate e) {
            System.err.println("Error while updating status for commentId: " + commentId + ", statusId: " + statusId);
            throw new DaoExceptionUpdate("Error while updating status", e);
        }
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

    @Override
    public List<CommentDto> findByStatusId(Integer statusId) {
        return commentDao.findByStatusId(statusId)
                .stream()
                .map(commentMapper::toDto)
                .toList();
    }
}
