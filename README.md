# HR HelpDesk Management System 🧑‍💼💻

A desktop-based HR management application built using Java Swing and MySQL, providing a graphical user interface to perform CRUD operations on employee data with secure login authentication.

---

## 📌 Features

✅ User Login & Authentication  
✅ Add New Employees (Name, Department, Salary)  
✅ View All Employees (with JTable)  
✅ Update Employee Records  
✅ Delete Employee (with confirmation)  
✅ Search Employee by Name  
✅ MySQL Database Integration (via JDBC)

---

## 🛠️ Tech Stack

- **Java 8+** (Swing for GUI)
- **JDBC** (Java Database Connectivity)
- **MySQL** (Database)
- **Eclipse IDE**
- **Maven** (optional for managing dependencies)

---

## 💾 Database Setup

1. Create a new MySQL database:
    ```sql
    CREATE DATABASE hr_system;
    ```

2. Create the `employee` table:
    ```sql
    CREATE TABLE employee (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100),
        department VARCHAR(100),
        salary DOUBLE
    );
    ```

3. Create the `user` table for login:
    ```sql
    CREATE TABLE user (
        username VARCHAR(50) PRIMARY KEY,
        password VARCHAR(50)
    );
    
    INSERT INTO user VALUES ('admin', 'admin123'); -- Default login
    ```

4. Update your DB config in `DBConnection.java`:
    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/hr_system";
    private static final String USER = "root";         // your username
    private static final String PASSWORD = "password"; // your password
    ```

---

## 🚀 How to Run

1. Open the project in **Eclipse**.
2. Add your MySQL JDBC Driver to the build path (e.g., `mysql-connector-java-x.x.x.jar`).
3. Run `Main.java` from `com.hr.ui`.




