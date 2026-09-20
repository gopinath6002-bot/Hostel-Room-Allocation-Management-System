package com.hostal.service;

import java.util.List;

import com.hostal.dao.studentsDAO;
import com.hostal.dao.studentsDAOImp1;
import com.hostal.model.students;

public class studentServiceImp1 implements studentService {

    private studentsDAO studentDAO;

    public studentServiceImp1() {
        studentDAO = new studentsDAOImp1();
    }

    @Override
    public boolean addStudent(students student) {
        return studentDAO.addStudent(student);
    }

    @Override
    public List<students> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public students getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    @Override
    public boolean updateStudent(students student) {
        return studentDAO.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(int studentId) {
        return studentDAO.deleteStudent(studentId);
    }
}