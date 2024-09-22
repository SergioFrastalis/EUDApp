package gr.aueb.cf.eudapp.service;


import gr.aueb.cf.eudapp.dao.exception.EmployeeDAOException;
import gr.aueb.cf.eudapp.dto.FiltersDTO;
import gr.aueb.cf.eudapp.dto.EmployeeInsertDTO;
import gr.aueb.cf.eudapp.dto.EmployeeUpdateDTO;
import gr.aueb.cf.eudapp.model.Employee;
import gr.aueb.cf.eudapp.service.exceptions.EmployeeNotFoundException;
import java.util.List;

public interface IEmployeeservice {
    Employee insertEmployee(EmployeeInsertDTO dto) throws EmployeeDAOException;
    Employee updateEmployee(EmployeeUpdateDTO dto) throws EmployeeNotFoundException, EmployeeDAOException;
    void deleteEmployee(Integer id) throws EmployeeNotFoundException, EmployeeDAOException;
    Employee getEmployeeByID(Integer id) throws EmployeeNotFoundException, EmployeeDAOException;
    List<Employee> getFilteredEmployees(FiltersDTO filters) throws EmployeeDAOException;

}
