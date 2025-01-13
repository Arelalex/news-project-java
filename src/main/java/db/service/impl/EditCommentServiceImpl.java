package db.service.impl;

import db.dao.impl.CommentDaoImpl;
import db.dto.EditCommentDto;
import db.enums.Statuses;
import db.exception.ValidationException;
import db.mapper.impl.EditCommentMapperImpl;
import db.service.EditCommentService;
import db.service.ImageService;
import db.validator.EditCommentValidator;

import java.io.IOException;
import java.time.LocalDateTime;

public class EditCommentServiceImpl implements EditCommentService {

    private static EditCommentServiceImpl instance;
    private final CommentDaoImpl commentDao = CommentDaoImpl.getInstance();
    private final EditCommentMapperImpl editCommentMapper = EditCommentMapperImpl.getInstance();
    private final EditCommentValidator editCommentValidator = EditCommentValidator.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    private EditCommentServiceImpl() {
    }

    public static synchronized EditCommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new EditCommentServiceImpl();
        }
        return instance;
    }

    public Long update(EditCommentDto commentDto) {
        var validationResult = editCommentValidator.isValid(commentDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        var commentEntity = editCommentMapper.toEntity(commentDto);
        commentEntity.setUpdatedAt(LocalDateTime.now());
        commentEntity.setStatus(Statuses.ON_MODERATION);

        if (commentEntity.getAttachment() != null && commentDto.getAttachment().getSize() > 0) {
            try {
                imageService.upload(commentEntity.getAttachment(), commentDto.getAttachment().getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        commentDao.update(commentEntity);

        return commentEntity.getCommentId();
    }
}
