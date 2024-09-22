package gr.aueb.cf.eudapp.authentication;

import gr.aueb.cf.eudapp.dao.IUserDAO;
import gr.aueb.cf.eudapp.dao.UserDAOImpl;
import gr.aueb.cf.eudapp.dao.exception.UserDAOException;
import gr.aueb.cf.eudapp.dto.UserLoginDTO;
import gr.aueb.cf.eudapp.service.IUserService;
import gr.aueb.cf.eudapp.service.UserServiceImpl;

import java.security.Provider;

public class AuthenticationProvider {
    private final static IUserDAO userDAO = new UserDAOImpl();
    private final static IUserService userService = new UserServiceImpl(userDAO);

    private AuthenticationProvider() {}

    public static boolean authenticate(UserLoginDTO userLoginDTO) throws UserDAOException {
        return userService.isUserValid(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }
}
