package gr.aueb.cf.eudapp.dao;

import gr.aueb.cf.eudapp.model.Employee;
import gr.aueb.cf.eudapp.dao.exception.EmployeeDAOException;
import gr.aueb.cf.eudapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements IEmployeeDAO {
    @Override
    public Employee insert(Employee Employee) throws EmployeeDAOException {
        String sql = "INSERT INTO Employees (firstname, lastname) VALUES (?,?)";

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            // Extract model info
            String firstname = Employee.getFirstname();
            String lastname = Employee.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);

            ps.executeUpdate();
            // logging
            return Employee;

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new EmployeeDAOException("Insert SQL error. Employee: " + Employee + "not inserted");
        }
    }

    @Override
    public Employee update(Employee Employee) throws EmployeeDAOException {
        String sql = "UPDATE Employees set firstname = ?, lastname = ? WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Extract model info
            int id = Employee.getId();
            String firstname = Employee.getFirstname();
            String lastname = Employee.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            ps.executeUpdate();
            // logging
            return Employee;

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new EmployeeDAOException("Update SQL error. Employee: " + Employee + "not updated");
        }
    }

    @Override
    public void delete(Integer id) throws EmployeeDAOException {
        String sql = "DELETE FROM Employees WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id); // Use the ID directly
            ps.executeUpdate();
            // Logging
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeDAOException("Delete SQL error. Employee with id: " + id + " not deleted");
        }
    }


    @Override
    public Employee getById(Integer id) throws EmployeeDAOException {
        String sql = "SELECT * FROM Employees WHERE id = ?";
        Employee Employee = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id); // Use the ID directly
            rs = ps.executeQuery();

            if (rs.next()) {
                Employee = new Employee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }
            // Logging
            return Employee;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeDAOException("SQL error in get by id with id" + id);
        }
    }

//    @Override
//    public Employee getByUsername(String username) throws EmployeeDAOException {
//        String sql = "SELECT * FROM Employees WHERE id = ?";
//        Employee Employee = null;
//        ResultSet rs;
//
//        try (Connection connection = DBUtil.getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//
//            ps.setInt(1, id); // Use the ID directly
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                Employee = new Employee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
//            }
//            // Logging
//            return Employee;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new EmployeeDAOException("SQL error in get by id with id" + id);
//        }
//    }

    @Override
    public List<Employee> getFilteredEmployees(String firstname, String lastname) throws EmployeeDAOException {
        String sql = "SELECT * FROM Employees WHERE firstname = ? AND lastname LIKE ?";
        List<Employee> Employees = new ArrayList<>(); //isEmpty == true
        ResultSet rs;
        Employee Employee;

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, firstname + "%");
            ps.setString(2, lastname + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Employee = new Employee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
                Employees.add(Employee);
            }
            // Logging
            return Employees;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeDAOException("SQL error in filtered get");
        }
    }
}
