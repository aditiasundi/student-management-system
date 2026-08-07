package com.sms.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Simple login screen. Uses hardcoded credentials for demonstration.
 */
public class LoginFrame extends JFrame {
    private final JTextField userField = new JTextField();
    private final JPasswordField passField = new JPasswordField();

    public LoginFrame() {
        setTitle("Student Management - Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 260);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(230, 245, 255));

        JLabel title = new JLabel("Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        p.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(4,1,6,6));
        form.setBorder(BorderFactory.createEmptyBorder(10,40,10,40));
        form.setOpaque(false);
        form.add(new JLabel("Username:")); form.add(userField);
        form.add(new JLabel("Password:")); form.add(passField);
        p.add(form, BorderLayout.CENTER);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(this::onLogin);
        JPanel south = new JPanel(); south.setOpaque(false); south.add(loginBtn);
        p.add(south, BorderLayout.SOUTH);

        add(p);
    }

    private void onLogin(ActionEvent e) {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword());
        // For demo: hardcoded credentials
        if ("admin".equals(user) && "admin".equals(pass)) {
            new DashboardFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
