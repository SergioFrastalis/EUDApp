package gr.aueb.cf.eudapp.service;
import gr.aueb.cf.eudapp.dao.IEmployeeDAO;
import gr.aueb.cf.eudapp.dao.exception.EmployeeDAOException;
import gr.aueb.cf.eudapp.dto.FiltersDTO;
import gr.aueb.cf.eudapp.dto.EmployeeInsertDTO;
import gr.aueb.cf.eudapp.dto.EmployeeUpdateDTO;
import gr.aueb.cf.eudapp.model.Employee;
import gr.aueb.cf.eudapp.service.exceptions.EmployeeNotFoundException;

import java.util.List;

public class EmployeeserviceImpl implements IEmployeeservice {

    private final IEmployeeDAO EmployeeDAO;

    public EmployeeserviceImpl(IEmployeeDAO EmployeeDAO) {
        this.EmployeeDAO = EmployeeDAO;
    }

    @Override
    public Employee insertEmployee(EmployeeInsertDTO dto) throws EmployeeDAOException {
        Employee Employee;

        try {
            Employee = mapToEmployee(dto);
            return EmployeeDAO.insert(Employee);
        } catch (EmployeeDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public Employee updateEmployee(EmployeeUpdateDTO dto) throws EmployeeNotFoundException, EmployeeDAOException {
        Employee Employee;

        try {
            Employee = mapToEmployee(dto);

            if (EmployeeDAO.getById(Employee.getId()) == null) {
                throw new EmployeeNotFoundException(Employee);
            }

            return EmployeeDAO.update(Employee);
        } catch (EmployeeDAOException | EmployeeNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public void deleteEmployee(Integer id) throws EmployeeNotFoundException, EmployeeDAOException {
        try {
            if (EmployeeDAO.getById(id) == null) {
                throw new EmployeeNotFoundException("Employee not found");
            }

            EmployeeDAO.delete(id);
        } catch (EmployeeDAOException | EmployeeNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }

    }

    @Override
    public Employee getEmployeeByID(Integer id) throws EmployeeNotFoundException, EmployeeDAOException {
        Employee Employee;

        try {
            Employee = EmployeeDAO.getById(id);
            if (Employee== null) {
                throw new EmployeeNotFoundException("Employee with id: " + id + " not found");
            }

            return Employee;
        } catch (EmployeeDAOException | EmployeeNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public List<Employee> getFilteredEmployees(FiltersDTO filters) throws EmployeeDAOException {
        List<Employee> Employees;

        try {
            Employees = EmployeeDAO.getFilteredEmployees(filters.getFirstname(), filters.getLastname());
            return Employees;
        } catch (EmployeeDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }


    private Employee mapToEmployee(EmployeeInsertDTO dto){
        return new Employee(null, dto.getFirstname(), dto.getLastname());
    }

    private Employee mapToEmployee(EmployeeUpdateDTO dto){
        return new Employee(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}
