package db.validator;

import db.dao.impl.PortalUserDaoImpl;
import db.dto.CreateNewsDto;
import db.dto.CreatePortalUserDto;
import db.util.LocalDateFormatter;

public class CreateNewsValidator implements Validator<CreateNewsDto> {

    private static CreateNewsValidator instance;

    private CreateNewsValidator() {
    }

    public static synchronized CreateNewsValidator getInstance() {
        if (instance == null) {
            instance = new CreateNewsValidator();
        }
        return instance;
    }

    @Override
    public ValidationResult isValid(CreateNewsDto object) {
        var validationResult = new ValidationResult();
        if (object.getTitle().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.title", "Title is invalid"));
        }
        if (object.getDescription().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.description", "Description is invalid"));
        }
        if (object.getContent().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.content", "Content is invalid"));
        }
        return validationResult;
    }
}
