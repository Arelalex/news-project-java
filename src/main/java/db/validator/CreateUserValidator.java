package db.validator;

import db.dao.impl.PortalUserDaoImpl;
import db.dto.CreatePortalUserDto;
import db.enums.Roles;

public class CreateUserValidator implements Validator<CreatePortalUserDto> {

    private static CreateUserValidator instance;
    private final PortalUserDaoImpl statusDao = PortalUserDaoImpl.getInstance();

    private CreateUserValidator() {
    }

    public static synchronized CreateUserValidator getInstance() {
        if (instance == null) {
            instance = new CreateUserValidator();
        }
        return instance;
    }

    @Override
    public ValidationResult isValid(CreatePortalUserDto object) {
        var validationResult = new ValidationResult();
        if (object.getFirstName().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.firstname", "First name is invalid"));
        }
        if (object.getLastName().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.lastname", "Last name is invalid"));
        }
        if (object.getNickname().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.nickname", "Nickname is invalid"));
        }
        if (object.getEmail().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.email", "Email is invalid"));
        }
        if (object.getPassword().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.password", "Password is invalid"));
        }
        return validationResult;
    }

}
