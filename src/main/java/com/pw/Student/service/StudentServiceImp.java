package com.pw.Student.service;

import com.pw.Student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public Student insertStudent(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(student);
        transaction.commit();
        entityManager.close();
        return student;
    }

    @Override
    public Student updateStudent(int id, Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Student stud = entityManager.find(Student.class, id);
        if (stud == null) {
            entityManager.close();
            System.out.println("Student details not found");
        }
        stud.setFirstName(student.getFirstName());
        stud.setLastName(student.getLastName());
        stud.setEmail(student.getEmail());
        entityManager.merge(stud);
        transaction.commit();
        entityManager.close();
        return stud;
    }

    @Override
    public Student getStudentById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = entityManager.find(Student.class, id);
        entityManager.close();
        if (student == null) {
            System.out.println("Student details not found");
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Student> studentList = entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        entityManager.close();
        return studentList;
    }

    @Override
    public void deleteStudent(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            entityManager.close();
            System.out.println("Student details not found");
        }
        entityManager.remove(student);
        transaction.commit();
        entityManager.close();
    }
}

