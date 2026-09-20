package com.hostal.service;

import java.util.List;
import com.hostal.model.students;

public interface studentService {

    boolean addStudent(students student);

    List<students> getAllStudents();

    students getStudentById(int studentId);

    boolean updateStudent(students student);

    boolean deleteStudent(int studentId);
}