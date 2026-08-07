package com.sms.ui;

import com.sms.model.Student;
import com.sms.service.StudentService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Search frame to find students by ID or name.
 */
public class SearchStudentFrame extends JFrame {
    private final StudentService service = new StudentService();
    private final JTextField idField = new JTextField(12);
    private final JTextField nameField = new JTextField(12);

    public SearchStudentFrame() {
        setTitle("Search Student");
        setSize(520,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(8,8,8,8); gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0; gbc.gridy=0; p.add(new JLabel("Student ID:"), gbc);
        gbc.gridx=1; p.add(idField, gbc);
        gbc.gridx=0; gbc.gridy=1; p.add(new JLabel("Name:"), gbc);
        gbc.gridx=1; p.add(nameField, gbc);

        JButton byId = new JButton("Search by ID"); byId.addActionListener(e -> searchById());
        JButton byName = new JButton("Search by Name"); byName.addActionListener(e -> searchByName());
        JPanel btns = new JPanel(); btns.add(byId); btns.add(byName);
        gbc.gridx=0; gbc.gridy=2; gbc.gridwidth=2; p.add(btns, gbc);

        add(p);
    }

    private void searchById() {
        try {
            String id = idField.getText().trim();
            if (id.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter ID"); return; }
            Student s = service.findById(id);
            if (s==null) JOptionPane.showMessageDialog(this, "Not found");
            else JOptionPane.showMessageDialog(this, formatStudent(s));
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
    }

    private void searchByName() {
        try {
            String name = nameField.getText().trim();
            if (name.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter name"); return; }
            List<Student> list = service.findByName(name);
            if (list.isEmpty()) JOptionPane.showMessageDialog(this, "No records");
            else {
                StringBuilder sb = new StringBuilder();
                for (Student s: list) sb.append(formatStudent(s)).append("\n----------------\n");
                JTextArea ta = new JTextArea(sb.toString()); ta.setEditable(false);
                JOptionPane.showMessageDialog(this, new JScrollPane(ta), "Results", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
    }

    private String formatStudent(Student s) {
        return String.format("ID: %s\nName: %s\nDept: %s\nSem: %d\nEmail: %s\nPhone: %s\nGender: %s\nDOB: %s\nAddress: %s",
                s.getStudentId(), s.getFullName(), s.getDepartment(), s.getSemester(), s.getEmail(), s.getPhone(), s.getGender(), s.getDateOfBirth(), s.getAddress());
    }
}
