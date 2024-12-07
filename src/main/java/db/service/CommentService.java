package db.service;

import db.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto findById(Long id);

    List<CommentDto> findAll();

    CommentDto save(CommentDto dto);

    CommentDto update(CommentDto dto);

    void delete(CommentDto dto);

    List<CommentDto> findAllByFilter(CommentDto filter);

}
