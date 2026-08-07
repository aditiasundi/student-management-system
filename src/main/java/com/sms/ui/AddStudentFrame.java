package com.sms.ui;

import com.sms.model.Student;
import com.sms.service.StudentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Frame for adding a new student.
 */
public class AddStudentFrame extends JFrame {
    private final StudentService service = new StudentService();
    private final JTextField idField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JComboBox<String> deptBox = new JComboBox<>(new String[]{"Computer Science","Electronics","Mechanical"});
    private final JSpinner semSpinner = new JSpinner(new SpinnerNumberModel(1,1,12,1));
    private final JTextField emailField = new JTextField();
    private final JTextField phoneField = new JTextField();
    private final JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male","Female","Other"});
    private final JTextField dobField = new JTextField();
    private final JTextArea addressArea = new JTextArea(3,20);

    public AddStudentFrame(JFrame parent) {
        setTitle("Add Student");
        setSize(520,420);
        setLocationRelativeTo(parent);
        initUI();
    }

    private void initUI() {
        JPanel p = new JPanel(new BorderLayout(8,8));
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6); gbc.fill = GridBagConstraints.HORIZONTAL;

        int y=0;
        gbc.gridx=0; gbc.gridy=y; form.add(new JLabel("Student ID:"), gbc);
        gbc.gridx=1; idField.setText(generateId()); form.add(idField, gbc);

        gbc.gridx=0; gbc.gridy=++y; form.add(new JLabel("Full Name:"), gbc);
        gbc.gridx=1; form.add(nameField, gbc);

        gbc.gridx=0; gbc.gridy=++y; form.add(new JLabel("Department:"), gbc);
        gbc.gridx=1; form.add(deptBox, gbc);

        gbc.gridx=0; gbc.gridy=++y; form.add(new JLabel("Semester:"), gbc);
        gbc.gridx=1; form.add(semSpinner, gbc);

        gbc.gridx=0; gbc.gridy=++y; form.add(new JLabel("Email:"), gbc);
        gbc.gridx=1; form.add(emailField, gbc);

        gbc.gridx=0; gbc.gridy=++y; form.add(new JLabel("Phone:"), gbc);
        gbc.gridx=1; form.add(phoneField, gbc);

        gbc.gridx=0; gbc.gridy=++y; form.add(new JLabel("Gender:"), gbc);
        gbc.gridx=1; form.add(genderBox, gbc);

        gbc.gridx=0; gbc.gridy=++y; form.add(new JLabel("DOB (YYYY-MM-DD):"), gbc);
        gbc.gridx=1; form.add(dobField, gbc);

        gbc.gridx=0; gbc.gridy=++y; form.add(new JLabel("Address:"), gbc);
        gbc.gridx=1; form.add(new JScrollPane(addressArea), gbc);

        p.add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton save = new JButton("Save"); save.addActionListener(this::onSave);
        JButton clear = new JButton("Clear"); clear.addActionListener(e -> clearForm());
        JButton back = new JButton("Back"); back.addActionListener(e -> dispose());
        buttons.add(save); buttons.add(clear); buttons.add(back);
        p.add(buttons, BorderLayout.SOUTH);

        add(p);
    }

    private String generateId() {
        return "S" + System.currentTimeMillis()%1000000;
    }

    private void clearForm() {
        nameField.setText(""); emailField.setText(""); phoneField.setText(""); dobField.setText(""); addressArea.setText(""); idField.setText(generateId());
    }

    private void onSave(ActionEvent e) {
        Student s = new Student();
        s.setStudentId(idField.getText().trim());
        s.setFullName(nameField.getText().trim());
        s.setDepartment((String)deptBox.getSelectedItem());
        s.setSemester((Integer)semSpinner.getValue());
        s.setEmail(emailField.getText().trim());
        s.setPhone(phoneField.getText().trim());
        s.setGender((String)genderBox.getSelectedItem());
        s.setDateOfBirth(dobField.getText().trim());
        s.setAddress(addressArea.getText().trim());

        try {
            service.addStudent(s);
            JOptionPane.showMessageDialog(this, "Student added successfully.");
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
