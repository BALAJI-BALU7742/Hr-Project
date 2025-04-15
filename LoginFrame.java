package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("HR HelpDesk - Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        userPanel.add(usernameField);
        add(userPanel);

        JPanel passPanel = new JPanel(new FlowLayout());
        passPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        passPanel.add(passwordField);
        add(passPanel);

        JButton loginBtn = new JButton("Login");
        add(loginBtn);

        JLabel msgLabel = new JLabel("", SwingConstants.CENTER);
        msgLabel.setForeground(Color.RED);
        add(msgLabel);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            // ✅ Replace this logic with DB check if needed
            if (user.equals("admin") && pass.equals("admin123")) {
                dispose();  // close login
                new MainDashboard().setVisible(true);  // open main
            } else {
                msgLabel.setText("Invalid credentials!");
            }
        });
    }
}
