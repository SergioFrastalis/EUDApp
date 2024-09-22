package gr.aueb.cf.eudapp.service;

import gr.aueb.cf.eudapp.dao.exception.UserDAOException;
import gr.aueb.cf.eudapp.dto.InsertUserDTO;
import gr.aueb.cf.eudapp.model.User;
import gr.aueb.cf.eudapp.service.exceptions.UserNotFoundException;

public interface IUserService {
    User insertUser(InsertUserDTO dto) throws UserDAOException;
    User getUserByUsername(String username) throws UserNotFoundException, UserDAOException;
    boolean isUserValid(String username, String password) throws UserDAOException;
    boolean isEmailExists(String username) throws UserDAOException;
}
