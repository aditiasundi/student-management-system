package com.sms.ui;

import com.sms.model.Student;
import com.sms.service.StudentService;

import javax.swing.*;
import java.awt.*;

/**
 * Update an existing student.
 */
public class UpdateStudentFrame extends JFrame {
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

    public UpdateStudentFrame(String studentId) {
        setTitle("Update Student - " + studentId);
        setSize(520,420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        idField.setEditable(false);
        initUI();
        loadStudent(studentId);
    }

    private void initUI() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6); gbc.fill = GridBagConstraints.HORIZONTAL;
        int y=0;
        gbc.gridx=0; gbc.gridy=y; form.add(new JLabel("Student ID:"), gbc);
        gbc.gridx=1; form.add(idField, gbc);

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

        JPanel buttons = new JPanel();
        JButton update = new JButton("Update"); update.addActionListener(e -> onUpdate());
        JButton delete = new JButton("Delete"); delete.addActionListener(e -> onDelete());
        JButton close = new JButton("Close"); close.addActionListener(e -> dispose());
        buttons.add(update); buttons.add(delete); buttons.add(close);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    private void loadStudent(String id) {
        try {
            Student s = service.findById(id);
            if (s==null) { JOptionPane.showMessageDialog(this, "Student not found"); dispose(); return; }
            idField.setText(s.getStudentId()); nameField.setText(s.getFullName()); deptBox.setSelectedItem(s.getDepartment()); semSpinner.setValue(s.getSemester());
            emailField.setText(s.getEmail()); phoneField.setText(s.getPhone()); genderBox.setSelectedItem(s.getGender()); dobField.setText(s.getDateOfBirth()); addressArea.setText(s.getAddress());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onUpdate() {
        Student s = new Student();
        s.setStudentId(idField.getText()); s.setFullName(nameField.getText()); s.setDepartment((String)deptBox.getSelectedItem()); s.setSemester((Integer)semSpinner.getValue());
        s.setEmail(emailField.getText()); s.setPhone(phoneField.getText()); s.setGender((String)genderBox.getSelectedItem()); s.setDateOfBirth(dobField.getText()); s.setAddress(addressArea.getText());
        try { service.updateStudent(s); JOptionPane.showMessageDialog(this, "Updated successfully"); } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }

    private void onDelete() {
        int ans = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?","Confirm", JOptionPane.YES_NO_OPTION);
        if (ans==JOptionPane.YES_OPTION) {
            try { service.deleteStudent(idField.getText()); JOptionPane.showMessageDialog(this, "Deleted."); dispose(); } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        }
    }
}
