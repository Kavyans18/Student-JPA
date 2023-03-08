package com.pw.Student.service;

import com.pw.Student.entity.Student;

import java.util.List;

public interface StudentService {

    Student insertStudent(Student student);

    Student updateStudent(int id, Student student);

    Student getStudentById(int id);

    List<Student> getAllStudents();

    void deleteStudent(int id);
}

