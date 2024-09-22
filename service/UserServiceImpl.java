package gr.aueb.cf.eudapp.service;

import gr.aueb.cf.eudapp.dao.IUserDAO;
import gr.aueb.cf.eudapp.dao.exception.UserDAOException;
import gr.aueb.cf.eudapp.dto.InsertUserDTO;
import gr.aueb.cf.eudapp.model.User;
import gr.aueb.cf.eudapp.service.exceptions.UserNotFoundException;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User insertUser(InsertUserDTO dto) throws UserDAOException {
        User user;

        try {
            user = mapToUser(dto);
            return userDAO.insert(user);
        }catch (UserDAOException e) {
            e.printStackTrace();
            //log
            //rollback
            throw e;
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException, UserDAOException {
        User user;

        try {
            user = userDAO.getByUsername(username);

            if (user == null) {
                throw new UserNotFoundException("User with username " + username + " not found");
            }
            return user;
        }catch (UserDAOException e) {
            e.printStackTrace();
            //log
            //rollback
            throw e;
        }
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        try {

            //logging
            return userDAO.isUserValid(username, password);
        } catch (UserDAOException e) {
            e.printStackTrace();
            //log
            throw e;
        }
    }

    @Override
    public boolean isEmailExists(String username) throws UserDAOException {
        try {

            //logging
            return userDAO.isEmailExists(username);
        } catch (UserDAOException e) {
            e.printStackTrace();
            //log
            throw e;
        }
    }

    private User mapToUser(InsertUserDTO dto) {
        return new User(null, dto.getUsername(), dto.getPassword());
    }
}
