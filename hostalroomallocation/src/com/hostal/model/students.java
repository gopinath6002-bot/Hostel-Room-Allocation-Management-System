package com.hostal.model;

public class students {

    private int studentId;
    private String name;
    private String department;
    private String phone;
    private int roomId;

    public students() {
    }

    public students(int studentId, String name, String department,
                   String phone, int roomId) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.phone = phone;
        this.roomId = roomId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}