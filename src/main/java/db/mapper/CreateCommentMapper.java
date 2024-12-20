package db.mapper;

import db.dto.CreateCommentDto;
import db.entity.CommentEntity;

public interface CreateCommentMapper extends BaseMapper<CommentEntity, CreateCommentDto> {

    CommentEntity toEntity(CreateCommentDto dto);
}
