package com.sms.model;

import java.time.LocalDateTime;

/**
 * Model class representing a Student record.
 */
public class Student {
    private String studentId;
    private String fullName;
    private String department;
    private int semester;
    private String email;
    private String phone;
    private String gender;
    private String dateOfBirth;
    private String address;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Student() {}

    public Student(String studentId, String fullName, String department, int semester, String email, String phone, String gender, String dateOfBirth, String address) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.department = department;
        this.semester = semester;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
