package ui;

import dao.EmployeeDAO;
import model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainDashboard extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    private EmployeeDAO dao;

    public MainDashboard() {
        dao = new EmployeeDAO();
        setTitle("HR HelpDesk - Employee Management");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table setup
        String[] columns = {"ID", "Name", "Department", "Email", "Phone", "Salary"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        loadTable("");

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Top panel: Search
        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                loadTable(searchField.getText());
            }
        });
        topPanel.add(new JLabel(" 🔍 Search by Name: "), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Bottom panel: Buttons
        JPanel bottomPanel = new JPanel();
        JButton addBtn = new JButton("➕ Add");
        JButton editBtn = new JButton("✏️ Edit");
        JButton deleteBtn = new JButton("🗑 Delete");

        bottomPanel.add(addBtn);
        bottomPanel.add(editBtn);
        bottomPanel.add(deleteBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        addBtn.addActionListener(e -> {
            EmployeeFormDialog dialog = new EmployeeFormDialog(this, "Add Employee", null);
            if (dialog.isSaved()) loadTable("");
        });

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                Employee emp = getEmployeeFromRow(row);
                EmployeeFormDialog dialog = new EmployeeFormDialog(this, "Edit Employee", emp);
                if (dialog.isSaved()) loadTable("");
            } else {
                JOptionPane.showMessageDialog(this, "Select an employee to edit.");
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    int id = (int) model.getValueAt(row, 0);
                    try {
                        dao.deleteEmployee(id);
                        loadTable("");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select an employee to delete.");
            }
        });
    }

    private void loadTable(String keyword) {
        try {
            model.setRowCount(0);
            List<Employee> employees = dao.getAllEmployees();
            for (Employee e : employees) {
                if (keyword.isEmpty() || e.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    model.addRow(new Object[]{e.getId(), e.getName(), e.getDepartment(), e.getEmail(), e.getPhone(), e.getSalary()});
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Employee getEmployeeFromRow(int row) {
        int id = (int) model.getValueAt(row, 0);
        String name = (String) model.getValueAt(row, 1);
        String dept = (String) model.getValueAt(row, 2);
        String email = (String) model.getValueAt(row, 3);
        String phone = (String) model.getValueAt(row, 4);
        double salary = (double) model.getValueAt(row, 5);
        return new Employee(id, name, dept, email, phone, salary);
    }
}
