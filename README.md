# 🏨 HostelHub – Hostel Room Allocation Management System

A Java-based Hostel Room Allocation Management System developed using **Java Swing, JDBC, and MySQL**. The system provides a user-friendly desktop interface for managing students, hostel rooms, room allocations, allocation records, and room vacancies.

---

## 📌 Project Overview

Hostel room management is often handled using manual records or spreadsheets, which can lead to data duplication, errors, difficulty in tracking room availability, and time-consuming updates.

**HostelHub** provides a centralized desktop application that simplifies hostel management by storing student, room, and allocation information in a MySQL database.

The project follows a **Layered Architecture**, separating the user interface, business logic, database operations, and database connectivity.

---

## 🎯 Objectives

- Manage student information efficiently
- Manage hostel room information
- Allocate rooms to students
- View existing allocation records
- Vacate allocated rooms
- Store data securely in MySQL
- Reduce manual record maintenance
- Provide a simple and professional desktop interface
- Maintain separation of responsibilities using layered architecture

---

## ✨ Features

### 👨‍🎓 Student Management
- Add student
- View students
- Update student details
- Delete student
- Refresh student records

### 🏠 Room Management
- Add room
- View rooms
- Update room details
- Delete room
- Track room capacity and status

### 🔑 Room Allocation
- Allocate a room to a student
- Store allocation date
- Maintain allocation status
- View allocation records

### 🚪 Vacate Room
- Search allocation
- Vacate an allocated room
- Remove the allocation record

### 🖥️ User Interface
- Java Swing desktop interface
- Dashboard
- Navigation sidebar
- Forms and tables
- Search and refresh functionality

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core programming language |
| Java Swing | Graphical User Interface |
| JDBC | Database connectivity |
| MySQL | Database management |
| Eclipse IDE | Development environment |
| MySQL Workbench | Database management |
| Layered Architecture | Application architecture |

---

## 🏗️ Project Architecture

The project follows a **Layered Architecture**.

```text
                    USER
                      │
                      ▼
              ┌───────────────┐
              │   UI Layer    │
              │  Java Swing   │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │  Controller   │
              │     Layer     │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Service Layer │
              │ Business Logic│
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │   DAO Layer   │
              │ Database CRUD │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ JDBC / Utility│
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │    MySQL      │
              │   Database    │
              └──────────────--
-----------------------------------------------------------------------------------------------------------------------------------------
## **📦 Package Structure**

The project follows a layered architecture with separate packages for the user interface, controller, model, service, DAO, and utility components.

```text
HostelRoomAllocation
│
├── com.hostal.controller
│   └── HostalController.java
│
├── com.hostal.model
│   ├── students.java
│   ├── room.java
│   └── allocation.java
│
├── com.hostal.dao
│   ├── studentsDAO.java
│   ├── studentsDAOImpl.java
│   ├── RoomDAO.java
│   ├── RoomDAOImpl.java
│   ├── allocationDAO.java
│   └── allocationDAOImpl.java
│
├── com.hostal.service
│   ├── studentsService.java
│   ├── studentsServiceImpl.java
│   ├── RoomService.java
│   ├── RoomServiceImpl.java
│   ├── allocationService.java
│   └── allocationServiceImpl.java
│
├── com.hostal.util
│   ├── dbconnection.java
│   └── testconnection.java
│
└── com.hostal.ui
    ├── HostelHubDashboard.java
    ├── StudentPanel.java
    ├── RoomPanel.java
    ├── AllocationPanel.java
    ├── AllocationsPanel.java
    └── VacatePanel.java
-----------------------------------------------------------------------------------------------------------------------------------------
### 🔹 Flow Apllication

```text
User enters Student ID and Room ID
              ↓
       AllocationPanel
              ↓
     allocationService
              ↓
     allocationServiceImpl
              ↓
     allocationDAO
              ↓
     allocationDAOImpl
              ↓
            JDBC
              ↓
      MySQL Database
              ↓
     Allocation Record
              ↓
        Swing UI
-----------------------------------------------------------------------------------------------------------------------------------------
## 🔄 CRUD Operations

The HostelHub system performs CRUD operations using Java, JDBC, and MySQL.

```text
                 CRUD OPERATIONS
                       │
        ┌──────────────┼──────────────┐
        ↓              ↓              ↓
      CREATE          READ          UPDATE
        │              │              │
   Add Student    View Students   Update Student
   Add Room       View Rooms      Update Room
   Allocate Room  View Allocations
        │              │              │
        └──────────────┼──────────────┘
                       ↓
                     DELETE
                       │
                Delete Student
                Delete Room
                Vacate Allocation
------------------------------------------------------------------------------------------------------------------------------------------
### 🔹 Database Connection

```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/hostalroomallocation",
    "root",
    "YOUR_PASSWORD"
);
------------------------------------------------------------------------------------------------------------------------------------------
## 🚀 Future Enhancements

- 🌐 Develop a web-based version of the system.
- 📱 Create a mobile application for students and administrators.
- 🔐 Add secure login and role-based access.
- 📧 Add email/SMS notifications for room allocation.
- 📊 Add advanced reports and analytics.
- ☁️ Move the database to cloud storage.
- 🔄 Add automatic room availability updates.
------------------------------------------------------------------------------------------------------------------------------------------
## 🎯 Conclusion

HostelHub – Hostel Room Allocation Management System provides an efficient way to manage students, rooms, and room allocations digitally.

The project uses **Java, Java Swing, JDBC, MySQL, and Layered Architecture** to provide a structured and user-friendly application. It reduces manual work, improves data management, and makes hostel room allocation easier and more organized.

