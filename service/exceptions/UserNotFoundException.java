package gr.aueb.cf.eudapp.service.exceptions;

import gr.aueb.cf.eudapp.model.User;

import java.io.Serial;

public class UserNotFoundException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public UserNotFoundException(User user) { super("User with id " + user.getUsername() + " not found"); }

  public UserNotFoundException(String s) { super(s); }
}
