package com.hostal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hostal.model.students;
import com.hostal.util.dbconnection;

public class studentsDAOImp1 implements studentsDAO {

    @Override
    public boolean addStudent(students student) {
        String sql = "INSERT INTO students "
                   + "(student_id, name, department, phone, room_id) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, student.getStudentId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getDepartment());
            ps.setString(4, student.getPhone());
            ps.setInt(5, student.getRoomId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<students> getAllStudents() {
        List<students> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                students student = new students();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setDepartment(rs.getString("department"));
                student.setPhone(rs.getString("phone"));
                student.setRoomId(rs.getInt("room_id"));

                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public students getStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                students student = new students();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setDepartment(rs.getString("department"));
                student.setPhone(rs.getString("phone"));
                student.setRoomId(rs.getInt("room_id"));

                return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateStudent(students student) {
        String sql = "UPDATE students SET name=?, department=?, "
                   + "phone=?, room_id=? WHERE student_id=?";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getDepartment());
            ps.setString(3, student.getPhone());
            ps.setInt(4, student.getRoomId());
            ps.setInt(5, student.getStudentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
