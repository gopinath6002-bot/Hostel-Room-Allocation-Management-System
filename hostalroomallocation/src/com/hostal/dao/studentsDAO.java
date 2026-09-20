package com.hostal.dao;

import java.util.List;
import com.hostal.model.students;

public interface studentsDAO {

    boolean addStudent(students student);

    List<students> getAllStudents();

    students getStudentById(int studentId);

    boolean updateStudent(students student);

    boolean deleteStudent(int studentId);
}
