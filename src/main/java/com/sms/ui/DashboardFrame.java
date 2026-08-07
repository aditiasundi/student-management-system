package com.sms.ui;

import com.sms.service.StudentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Dashboard showing statistics and navigation buttons.
 */
public class DashboardFrame extends JFrame {
    private final StudentService service = new StudentService();

    private final JLabel totalLbl = new JLabel("0", SwingConstants.CENTER);
    private final JLabel csLbl = new JLabel("0", SwingConstants.CENTER);
    private final JLabel elecLbl = new JLabel("0", SwingConstants.CENTER);
    private final JLabel mechLbl = new JLabel("0", SwingConstants.CENTER);

    public DashboardFrame() {
        setTitle("Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 520);
        setLocationRelativeTo(null);
        initUI();
        loadStats();
    }

    private void initUI() {
        JPanel root = new JPanel(new BorderLayout(10,10));
        root.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel header = new JLabel("Welcome, Admin", SwingConstants.LEFT);
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        root.add(header, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(1,4,10,10));
        cards.add(cardPanel("Total Students", totalLbl));
        cards.add(cardPanel("Computer Science", csLbl));
        cards.add(cardPanel("Electronics", elecLbl));
        cards.add(cardPanel("Mechanical", mechLbl));
        root.add(cards, BorderLayout.CENTER);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));
        nav.add(button("Add Student", e -> new AddStudentFrame(this).setVisible(true)));
        nav.add(button("View Students", e -> new ViewStudentsFrame().setVisible(true)));
        nav.add(button("Search Student", e -> new SearchStudentFrame().setVisible(true)));
        nav.add(button("Logout", e -> { new LoginFrame().setVisible(true); dispose(); }));
        root.add(nav, BorderLayout.SOUTH);

        add(root);
    }

    private JPanel cardPanel(String title, JLabel value) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        JLabel t = new JLabel(title, SwingConstants.CENTER);
        t.setFont(new Font("SansSerif", Font.PLAIN, 14));
        value.setFont(new Font("SansSerif", Font.BOLD, 20));
        p.add(t, BorderLayout.NORTH);
        p.add(value, BorderLayout.CENTER);
        return p;
    }

    private JButton button(String text, AbstractAction action) {
        JButton b = new JButton(text);
        b.addActionListener(action);
        return b;
    }

    public void loadStats() {
        try {
            totalLbl.setText(String.valueOf(service.countAll()));
            csLbl.setText(String.valueOf(service.countByDepartment("Computer Science")));
            elecLbl.setText(String.valueOf(service.countByDepartment("Electronics")));
            mechLbl.setText(String.valueOf(service.countByDepartment("Mechanical")));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not load stats: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
