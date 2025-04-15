package dao;

import model.Employee;
import java.sql.*;
import java.util.*;

public class EmployeeDAO {
    private final String URL = "jdbc:mysql://localhost:3306/hr_helpdesk";
    private final String USER = "root";
    private final String PASS ="Balu@123";

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public List<Employee> getAllEmployees() throws Exception {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection con = getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getDouble("salary")
                ));
            }
        }
        return list;
    }

    public void addEmployee(Employee emp) throws Exception {
        String sql = "INSERT INTO employees(name, department, email, phone, salary) VALUES(?,?,?,?,?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone());
            ps.setDouble(5, emp.getSalary());
            ps.executeUpdate();
        }
    }

    public void updateEmployee(Employee emp) throws Exception {
        String sql = "UPDATE employees SET name=?, department=?, email=?, phone=?, salary=? WHERE id=?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone());
            ps.setDouble(5, emp.getSalary());
            ps.setInt(6, emp.getId());
            ps.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws Exception {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
