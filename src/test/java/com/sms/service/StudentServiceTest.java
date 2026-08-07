package com.sms.service;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentDAO dao;

    private StudentService service;

    @BeforeEach
    public void setUp() {
        service = new StudentService(dao);
    }

    @Test
    public void testAddStudent_Success() throws Exception {
        Student s = new Student();
        s.setStudentId("S123");
        s.setFullName("John Doe");
        s.setEmail("john.doe@example.com");
        s.setPhone("1234567890");

        when(dao.existsById("S123")).thenReturn(false);
        when(dao.insertStudent(s)).thenReturn(true);

        service.addStudent(s);

        verify(dao).existsById("S123");
        verify(dao).insertStudent(s);
    }

    @Test
    public void testAddStudent_OptionalFieldsEmpty() throws Exception {
        Student s = new Student();
        s.setStudentId("S123");
        s.setFullName("John Doe");
        s.setEmail("");
        s.setPhone("");

        when(dao.existsById("S123")).thenReturn(false);
        when(dao.insertStudent(s)).thenReturn(true);

        service.addStudent(s);

        verify(dao).existsById("S123");
        verify(dao).insertStudent(s);
    }

    @Test
    public void testAddStudent_MissingId() {
        Student s = new Student();
        s.setFullName("John Doe");

        Exception ex = assertThrows(Exception.class, () -> service.addStudent(s));
        assertEquals("Student ID and Full Name are required", ex.getMessage());
    }

    @Test
    public void testAddStudent_MissingName() {
        Student s = new Student();
        s.setStudentId("S123");

        Exception ex = assertThrows(Exception.class, () -> service.addStudent(s));
        assertEquals("Student ID and Full Name are required", ex.getMessage());
    }

    @Test
    public void testAddStudent_InvalidEmail() {
        Student s = new Student();
        s.setStudentId("S123");
        s.setFullName("John Doe");
        s.setEmail("invalid-email");

        Exception ex = assertThrows(Exception.class, () -> service.addStudent(s));
        assertEquals("Invalid email", ex.getMessage());
    }

    @Test
    public void testAddStudent_InvalidPhone() {
        Student s = new Student();
        s.setStudentId("S123");
        s.setFullName("John Doe");
        s.setPhone("12345"); // too short

        Exception ex = assertThrows(Exception.class, () -> service.addStudent(s));
        assertEquals("Invalid phone", ex.getMessage());
    }

    @Test
    public void testAddStudent_DuplicateId() throws SQLException {
        Student s = new Student();
        s.setStudentId("S123");
        s.setFullName("John Doe");

        when(dao.existsById("S123")).thenReturn(true);

        Exception ex = assertThrows(Exception.class, () -> service.addStudent(s));
        assertEquals("Duplicate Student ID", ex.getMessage());
    }

    @Test
    public void testUpdateStudent_Success() throws Exception {
        Student s = new Student();
        s.setStudentId("S123");
        s.setFullName("John Doe");

        when(dao.existsById("S123")).thenReturn(true);
        when(dao.updateStudent(s)).thenReturn(true);

        service.updateStudent(s);

        verify(dao).existsById("S123");
        verify(dao).updateStudent(s);
    }

    @Test
    public void testUpdateStudent_NotFound() throws SQLException {
        Student s = new Student();
        s.setStudentId("S123");
        s.setFullName("John Doe");

        when(dao.existsById("S123")).thenReturn(false);

        Exception ex = assertThrows(Exception.class, () -> service.updateStudent(s));
        assertEquals("Student not found", ex.getMessage());
    }

    @Test
    public void testDeleteStudent_Success() throws Exception {
        when(dao.existsById("S123")).thenReturn(true);
        when(dao.deleteStudent("S123")).thenReturn(true);

        service.deleteStudent("S123");

        verify(dao).existsById("S123");
        verify(dao).deleteStudent("S123");
    }

    @Test
    public void testDeleteStudent_NotFound() throws SQLException {
        when(dao.existsById("S123")).thenReturn(false);

        Exception ex = assertThrows(Exception.class, () -> service.deleteStudent("S123"));
        assertEquals("Student not found", ex.getMessage());
    }

    @Test
    public void testFindById() throws Exception {
        Student s = new Student();
        when(dao.findById("S123")).thenReturn(s);

        Student result = service.findById("S123");
        assertSame(s, result);
    }

    @Test
    public void testFindByName() throws Exception {
        List<Student> list = new ArrayList<>();
        when(dao.findByName("John")).thenReturn(list);

        List<Student> result = service.findByName("John");
        assertSame(list, result);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Student> list = new ArrayList<>();
        when(dao.findAll()).thenReturn(list);

        List<Student> result = service.findAll();
        assertSame(list, result);
    }

    @Test
    public void testCountAll() throws Exception {
        when(dao.countAll()).thenReturn(5);

        int result = service.countAll();
        assertEquals(5, result);
    }

    @Test
    public void testCountByDepartment() throws Exception {
        when(dao.countByDepartment("CS")).thenReturn(3);

        int result = service.countByDepartment("CS");
        assertEquals(3, result);
    }
}
