package com.sms.service;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;
import com.sms.utils.Validation;

import java.sql.SQLException;
import java.util.List;

/**
 * Service layer to coordinate validation and DAO operations.
 */
public class StudentService {
    private final StudentDAO dao = new StudentDAO();

    public void addStudent(Student s) throws Exception {
        // Validate
        if (!Validation.isNotEmpty(s.getStudentId()) || !Validation.isNotEmpty(s.getFullName()))
            throw new Exception("Student ID and Full Name are required");
        if (!Validation.isValidEmail(s.getEmail())) throw new Exception("Invalid email");
        if (!Validation.isValidPhone(s.getPhone())) throw new Exception("Invalid phone");
        try {
            if (dao.existsById(s.getStudentId())) throw new Exception("Duplicate Student ID");
            if (!dao.insertStudent(s)) throw new Exception("Insert failed");
        } catch (SQLException e) {
            throw new Exception("Database error: " + e.getMessage(), e);
        }
    }

    public void updateStudent(Student s) throws Exception {
        if (!Validation.isNotEmpty(s.getStudentId())) throw new Exception("Student ID required");
        try {
            if (!dao.existsById(s.getStudentId())) throw new Exception("Student not found");
            if (!dao.updateStudent(s)) throw new Exception("Update failed");
        } catch (SQLException e) {
            throw new Exception("Database error: " + e.getMessage(), e);
        }
    }

    public void deleteStudent(String studentId) throws Exception {
        try {
            if (!dao.existsById(studentId)) throw new Exception("Student not found");
            if (!dao.deleteStudent(studentId)) throw new Exception("Delete failed");
        } catch (SQLException e) {
            throw new Exception("Database error: " + e.getMessage(), e);
        }
    }

    public Student findById(String id) throws Exception {
        try {
            return dao.findById(id);
        } catch (SQLException e) {
            throw new Exception("Database error: " + e.getMessage(), e);
        }
    }

    public List<Student> findByName(String name) throws Exception {
        try { return dao.findByName(name); } catch (SQLException e) { throw new Exception(e); }
    }

    public List<Student> findAll() throws Exception {
        try { return dao.findAll(); } catch (SQLException e) { throw new Exception(e); }
    }

    public int countAll() throws Exception { try { return dao.countAll(); } catch (SQLException e) { throw new Exception(e); } }
    public int countByDepartment(String dept) throws Exception { try { return dao.countByDepartment(dept); } catch (SQLException e) { throw new Exception(e); } }
}
