package db.validator;

import db.dao.impl.PortalUserDaoImpl;
import db.dto.EditNewsDto;

public class EditNewsValidator implements Validator<EditNewsDto> {

    private static EditNewsValidator instance;
    private final PortalUserDaoImpl statusDao = PortalUserDaoImpl.getInstance();

    private EditNewsValidator() {
    }

    public static synchronized EditNewsValidator getInstance() {
        if (instance == null) {
            instance = new EditNewsValidator();
        }
        return instance;
    }

    @Override
    public ValidationResult isValid(EditNewsDto object) {
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
       /* if (object.getImage().getSize() == 0) {
            validationResult.add(Error.of("invalid.image", "Image is empty"));
        }*/
        return validationResult;
    }
}
