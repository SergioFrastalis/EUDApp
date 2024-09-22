package gr.aueb.cf.eudapp.dto;

public class EmployeeReadOnlyDTO extends BaseDTO {
    private Integer id;

    public EmployeeReadOnlyDTO() {}

    public EmployeeReadOnlyDTO(Integer id, String firstname, String lastname) {
        super(firstname, lastname);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
