package db.service;

import db.dto.CommentDto;
import db.dto.NewsDto;

import java.util.List;

public interface CommentService {

    CommentDto findById(Long id);

    List<CommentDto> findAll();

    CommentDto save(CommentDto dto);

    CommentDto update(CommentDto dto);

    void delete(CommentDto dto);

    List<CommentDto> findAllByFilter(CommentDto filter);

    boolean updateStatus(Long commentId, Integer statusId, String reason_rej);

    List<CommentDto> findByStatusId(Integer statusId);

}
