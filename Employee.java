package model;

public class Employee {
    private int id;
    private String name, department, email, phone;
    private double salary;

    public Employee(int id, String name, String department, String email, String phone, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
    }

    public Employee(String name, String department, String email, String phone, double salary) {
        this(0, name, department, email, phone, salary);
    }

    // Getters and setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public double getSalary() { return salary; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setSalary(double salary) { this.salary = salary; }
}
