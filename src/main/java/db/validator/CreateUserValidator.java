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
//        if (object.getRole() == null || Roles.find(object.getRole()).isEmpty()) {
//            validationResult.add(Error.of("invalid.role", "Role is invalid"));
//        }
        if (object.getFirstName().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.name", "Name is invalid"));
        }
        return validationResult;
    }

}
