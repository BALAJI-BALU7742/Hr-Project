package ui;

import dao.EmployeeDAO;
import model.Employee;

import javax.swing.*;
import java.awt.*;

public class EmployeeFormDialog extends JDialog {
    private JTextField nameField, deptField, emailField, phoneField, salaryField;
    private boolean saved = false;

    public EmployeeFormDialog(JFrame parent, String title, Employee emp) {
        super(parent, title, true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Department:"));
        deptField = new JTextField();
        add(deptField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Phone:"));
        phoneField = new JTextField();
        add(phoneField);

        add(new JLabel("Salary:"));
        salaryField = new JTextField();
        add(salaryField);

        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        add(saveBtn);
        add(cancelBtn);

        if (emp != null) {
            nameField.setText(emp.getName());
            deptField.setText(emp.getDepartment());
            emailField.setText(emp.getEmail());
            phoneField.setText(emp.getPhone());
            salaryField.setText(String.valueOf(emp.getSalary()));
        }

        saveBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String dept = deptField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                double salary = Double.parseDouble(salaryField.getText());

                EmployeeDAO dao = new EmployeeDAO();
                if (emp == null) {
                    dao.addEmployee(new Employee(name, dept, email, phone, salary));
                } else {
                    emp.setName(name);
                    emp.setDepartment(dept);
                    emp.setEmail(email);
                    emp.setPhone(phone);
                    emp.setSalary(salary);
                    dao.updateEmployee(emp);
                }
                saved = true;
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        cancelBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    public boolean isSaved() {
        return saved;
    }
}
