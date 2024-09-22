package gr.aueb.cf.eudapp.dao;

import gr.aueb.cf.eudapp.model.Employee;
import gr.aueb.cf.eudapp.dao.exception.EmployeeDAOException;
import java.util.List;

public interface IEmployeeDAO {
    Employee insert(Employee Employee) throws EmployeeDAOException;
    Employee update(Employee Employee) throws EmployeeDAOException;
    void delete(Integer id) throws EmployeeDAOException;
    Employee getById(Integer id) throws EmployeeDAOException;
//    Employee getByUsername(String username) throws EmployeeDAOException;
    List<Employee> getFilteredEmployees(String firstname, String lastname)
        throws EmployeeDAOException;
}
