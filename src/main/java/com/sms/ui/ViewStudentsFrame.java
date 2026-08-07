package com.sms.ui;

import com.sms.model.Student;
import com.sms.service.StudentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Shows all students in a JTable with search and refresh.
 */
public class ViewStudentsFrame extends JFrame {
    private final StudentService service = new StudentService();
    private final DefaultTableModel model = new DefaultTableModel();
    private final JTable table = new JTable(model);

    public ViewStudentsFrame() {
        setTitle("View Students");
        setSize(900,520);
        setLocationRelativeTo(null);
        initUI();
        loadData();
    }

    private void initUI() {
        model.setColumnIdentifiers(new Object[]{"ID","Name","Dept","Semester","Email","Phone","Gender","DOB","Address"});
        table.setRowHeight(24);
        table.setAutoCreateRowSorter(true);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2) {
                    int r = table.getSelectedRow();
                    if (r>=0) {
                        int modelRow = table.convertRowIndexToModel(r);
                        String id = (String) model.getValueAt(modelRow, 0);
                        new UpdateStudentFrame(id).setVisible(true);
                    }
                }
            }
        });

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField search = new JTextField(24);
        JButton refresh = new JButton("Refresh"); refresh.addActionListener(e -> loadData());
        top.add(new JLabel("Search:")); top.add(search); top.add(refresh);
        search.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(search.getText()); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(search.getText()); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(search.getText()); }
        });

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void filter(String text) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        if (text==null || text.trim().isEmpty()) sorter.setRowFilter(null);
        else sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)"+text));
    }

    private void loadData() {
        try {
            model.setRowCount(0);
            List<Student> list = service.findAll();
            for (Student s: list) {
                model.addRow(new Object[]{s.getStudentId(), s.getFullName(), s.getDepartment(), s.getSemester(), s.getEmail(), s.getPhone(), s.getGender(), s.getDateOfBirth(), s.getAddress()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not load students: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
