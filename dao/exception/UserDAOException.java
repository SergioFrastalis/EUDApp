package gr.aueb.cf.eudapp.dao.exception;

import java.io.Serial;

public class UserDAOException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserDAOException(String s) {
      super(s);
    }
}
