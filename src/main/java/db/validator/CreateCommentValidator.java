package db.validator;

import db.dao.impl.PortalUserDaoImpl;
import db.dto.CreateCommentDto;
import db.dto.CreateNewsDto;

public class CreateCommentValidator implements Validator<CreateCommentDto> {

    private static CreateCommentValidator instance;

    private CreateCommentValidator() {
    }

    public static synchronized CreateCommentValidator getInstance() {
        if (instance == null) {
            instance = new CreateCommentValidator();
        }
        return instance;
    }

    @Override
    public ValidationResult isValid(CreateCommentDto object) {
        var validationResult = new ValidationResult();
        if (object.getContent().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.content", "Content is invalid"));
        }
        return validationResult;
    }
}
