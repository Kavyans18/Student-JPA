package com.pw.Student.controller;

import com.pw.Student.entity.Student;
import com.pw.Student.service.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentServiceImp service;
    @PostMapping()
    public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
        service.insertStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student stud = service.getStudentById(id);
        if (stud == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        stud.setFirstName(student.getFirstName());
        stud.setLastName(student.getLastName());
        stud.setEmail(student.getEmail());
        service.updateStudent(id, stud);
        return new ResponseEntity<>(stud, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = service.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = service.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable int id) {
        Student student = service.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
