package gr.aueb.cf.eudapp.dto;

public class UserLoginDTO extends BaseUserDTO {

    public UserLoginDTO() {}

    public UserLoginDTO(String password, String username) {
        super(password, username);
    }

}
