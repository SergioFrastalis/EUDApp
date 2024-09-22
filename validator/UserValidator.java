package gr.aueb.cf.eudapp.validator;

import gr.aueb.cf.eudapp.dao.IUserDAO;
import gr.aueb.cf.eudapp.dao.UserDAOImpl;
import gr.aueb.cf.eudapp.dao.exception.UserDAOException;
import gr.aueb.cf.eudapp.dto.BaseUserDTO;
import gr.aueb.cf.eudapp.dto.InsertUserDTO;
import gr.aueb.cf.eudapp.service.IUserService;
import gr.aueb.cf.eudapp.service.UserServiceImpl;

import javax.xml.validation.Validator;
import java.util.HashMap;
import java.util.Map;

public class UserValidator<T> {

    private static final IUserDAO userDAO = new UserDAOImpl();
    private static final IUserService userService = new UserServiceImpl(userDAO);

    private UserValidator() {}

    public static <T extends BaseUserDTO> Map<String, String> validate(T dto) throws UserDAOException{
        Map<String, String> errors = new HashMap<>();

        if(!dto.getPassword().equals(dto.getConfirmedPassword())) {
            errors.put("confirmedPassword", "Passwords do not match");
        }

        if(dto.getPassword().length() < 5 || dto.getPassword().length() > 32) {
            errors.put("password", "The password must be between 5 and 32 characters");
        }

        if (dto.getUsername().matches("^.*\\s+.*$")) {
            errors.put("username", "The username must not contain spaces");
        }

        if (dto.getPassword().matches("^.*\\s+.*$")) {
            errors.put("password", "The password must not contain spaces");
        }

        if (userService.isEmailExists(dto.getUsername())) {
            errors.put("username", "This username is already in use");
        }


        return errors;
    }
}
