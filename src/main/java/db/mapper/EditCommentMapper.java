package db.mapper;

import db.dto.EditCommentDto;
import db.dto.EditNewsDto;
import db.entity.CommentEntity;
import db.entity.NewsEntity;

public interface EditCommentMapper extends BaseMapper<CommentEntity, EditCommentDto> {

    CommentEntity toEntity(EditCommentDto dto);
}
