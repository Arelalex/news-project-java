package db.service.impl;

import db.dao.impl.CommentDaoImpl;
import db.dto.CreateCommentDto;
import db.enums.Statuses;
import db.exception.ValidationException;
import db.mapper.impl.CreateCommentMapperImpl;
import db.service.CreateCommentService;
import db.service.ImageService;
import db.validator.CreateCommentValidator;

import java.io.IOException;
import java.time.LocalDateTime;

public class CreateCommentServiceImpl implements CreateCommentService {

    private static CreateCommentServiceImpl instance;
    private final CommentDaoImpl commentDao = CommentDaoImpl.getInstance();
    private final CreateCommentMapperImpl createCommentMapper = CreateCommentMapperImpl.getInstance();
    private final CreateCommentValidator createCommentValidator = CreateCommentValidator.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    private CreateCommentServiceImpl() {
    }

    public static synchronized CreateCommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new CreateCommentServiceImpl();
        }
        return instance;
    }

    @Override
    public Long create(CreateCommentDto commentDto) {
        var validationResult = createCommentValidator.isValid(commentDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var commentEntity = createCommentMapper.toEntity(commentDto);

        commentEntity.setCreatedAt(LocalDateTime.now());
        commentEntity.setUpdatedAt(LocalDateTime.now());

        if (commentDto.getAttachment() != null && commentDto.getAttachment().getSize() > 0) {
            try {
                imageService.upload(commentEntity.getAttachment(), commentDto.getAttachment().getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        commentEntity.setStatus(Statuses.ON_MODERATION);

        commentDao.save(commentEntity);
        return commentEntity.getCommentId();
    }
}
