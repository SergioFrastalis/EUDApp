package gr.aueb.cf.eudapp.dao;

import gr.aueb.cf.eudapp.model.User;
import gr.aueb.cf.eudapp.dao.exception.UserDAOException;

public interface IUserDAO {
    User insert(User user) throws UserDAOException;
    // User update(User user) throws UserDAOException;
    // void delete(int id) throws UserDAOException;
    //List<User> getAll()
    User getByUsername(String username) throws UserDAOException;
    boolean isUserValid(String username, String password) throws UserDAOException;
    boolean isEmailExists(String username) throws UserDAOException;
}
