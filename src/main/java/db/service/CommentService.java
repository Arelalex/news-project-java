package db.service;

import db.dto.CommentFilter;

import java.util.List;

public interface CommentService {

    CommentFilter findById(Long id);

    List<CommentFilter> findAll();

    CommentFilter save(CommentFilter dto);

    CommentFilter update(CommentFilter dto);

    void delete(CommentFilter dto);

    List<CommentFilter> findAllByFilter(CommentFilter filter);

}
