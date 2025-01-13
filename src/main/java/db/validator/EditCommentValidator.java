package db.validator;

import db.dto.CreateCommentDto;
import db.dto.EditCommentDto;

public class EditCommentValidator implements Validator<EditCommentDto> {

    private static EditCommentValidator instance;

    private EditCommentValidator() {
    }

    public static synchronized EditCommentValidator getInstance() {
        if (instance == null) {
            instance = new EditCommentValidator();
        }
        return instance;
    }

    @Override
    public ValidationResult isValid(EditCommentDto object) {
        var validationResult = new ValidationResult();
        if (object.getContent().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.content", "Content is invalid"));
        }
        return validationResult;
    }
}
