package db.service;

import db.dto.EditCommentDto;

public interface EditCommentService {

    Long update(EditCommentDto commentDto);

}
