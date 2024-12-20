package db.service;

import db.dto.CreateCommentDto;

public interface CreateCommentService {

    Long create(CreateCommentDto commentDto);

}
