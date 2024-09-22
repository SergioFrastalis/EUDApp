package gr.aueb.cf.eudapp.service.exceptions;

import gr.aueb.cf.eudapp.model.Employee;

public class EmployeeNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(Employee Employee) {
        super("Employee with id " + Employee.getId() + " not found");
    }

    public EmployeeNotFoundException(String s) {
        super(s);
    }
}
