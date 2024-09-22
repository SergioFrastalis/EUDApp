package gr.aueb.cf.eudapp.dto;

public class InsertUserDTO extends BaseUserDTO {
    private String confirmPassword;

    public InsertUserDTO() {
    }


    public InsertUserDTO(String username, String password, String confirmedPassword, String confirmPassword) {
        super(username, password, confirmedPassword);

    }

    public InsertUserDTO(String username, String password, String confirmPassword) {
    }
}