-- SQL script to create database and students table
CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

CREATE TABLE IF NOT EXISTS students (
  student_id VARCHAR(50) PRIMARY KEY,
  full_name VARCHAR(200) NOT NULL,
  department VARCHAR(100) NOT NULL,
  semester INT NOT NULL,
  email VARCHAR(150),
  phone VARCHAR(50),
  gender VARCHAR(20),
  date_of_birth VARCHAR(50),
  address TEXT,
  created_at DATETIME
);

-- Sample records
INSERT INTO students (student_id, full_name, department, semester, email, phone, gender, date_of_birth, address, created_at) VALUES
('S1001','Alice Johnson','Computer Science',4,'alice.johnson@example.com','9876543210','Female','2003-05-12','123 Maple Street', NOW()),
('S1002','Bob Smith','Electronics',3,'bob.smith@example.com','9123456780','Male','2003-08-20','456 Oak Avenue', NOW()),
('S1003','Charlie Lee','Mechanical',2,'charlie.lee@example.com','9988776655','Male','2004-01-15','789 Pine Road', NOW());
