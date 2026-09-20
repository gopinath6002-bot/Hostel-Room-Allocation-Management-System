# 🏨 HostelHub – Hostel Room Allocation System

## 📌 Project Overview

**HostelHub** is a Java-based desktop application developed to simplify and manage hostel room allocation efficiently.

The system allows hostel administrators to manage student details, hostel rooms, room allocations, and room vacancies through a simple desktop interface connected to a MySQL database.

---

## 🎯 Problem Statement

Managing hostel room allocation manually can lead to duplicate allocations, incorrect records, difficulty tracking available rooms, and unnecessary paperwork.

**HostelHub** provides a computerized solution to manage students, rooms, and allocations in an organized and efficient manner.

---

## 🎯 Objectives

* Manage student information
* Manage hostel room information
* Allocate rooms to students
* View current room allocations
* Vacate allocated rooms
* Track room availability
* Store records in a MySQL database
* Reduce manual work and errors
* Provide a simple and user-friendly desktop interface

---

## 🛠️ Technologies Used

| Technology      | Purpose                 |
| --------------- | ----------------------- |
| Java            | Application development |
| Java Swing      | Desktop GUI             |
| JDBC            | Database connectivity   |
| MySQL           | Database management     |
| Eclipse IDE     | Development environment |
| MySQL Workbench | Database management     |

---

## 🏗️ Architecture

The application follows a **Layered Architecture** to separate the user interface, business logic, database operations, and data models.

```text
┌─────────────────────────┐
│       UI Layer          │
└────────────┬────────────┘
             ↓
┌─────────────────────────┐
│   Controller Layer      │
└────────────┬────────────┘
             ↓
┌─────────────────────────┐
│     Service Layer       │
└────────────┬────────────┘
             ↓
┌─────────────────────────┐
│       DAO Layer         │
└────────────┬────────────┘
             ↓
┌─────────────────────────┐
│      MySQL Database     │
└─────────────────────────┘
```

---

## 📂 Package Structure

```text
com.hostal
│
├── controller
│   ├── hostalcontroller.java
│
├── dao
│   ├── StudentDAO.java
│   ├── RoomDAO.java
│   └── AllocationDAO.java
│
├── model
│   ├── Student.java
│   ├── Room.java
│   └── Allocation.java
│
├── service
│   ├── StudentService.java
│   ├── RoomService.java
│   └── AllocationService.java
│
├── util
│   └── DBConnection.java
│
└── ui
    ├── HostelHubDashboard.java
    ├── StudentPanel.java
    ├── RoomPanel.java
    ├── AllocationPanel.java
    ├── AllocationsPanel.java
    └── VacatePanel.java
```

### 📦 Package Description

```text
controller → Handles application requests and controls the application flow

dao        → Performs database operations using JDBC

model      → Contains entity/data classes

service    → Contains application business logic

util       → Contains database connection and utility classes

ui         → Contains Java Swing user interface screens
```

### 🧩 Class Responsibilities

#### Controller Package

```text
StudentController.java
→ Handles student-related operations

RoomController.java
→ Handles room-related operations

AllocationController.java
→ Handles room allocation operations
```

#### DAO Package

```text
StudentDAO.java
→ Performs student database operations

RoomDAO.java
→ Performs room database operations

AllocationDAO.java
→ Performs allocation database operations
```

#### Model Package

```text
Student.java
→ Represents and stores student information

Room.java
→ Represents and stores room information

Allocation.java
→ Represents and stores allocation information
```

#### Service Package

```text
StudentService.java
→ Handles student business logic

RoomService.java
→ Handles room business logic

AllocationService.java
→ Handles allocation and room vacancy business logic
```

#### Util Package

```text
DBConnection.java
→ Establishes and manages JDBC connection with MySQL
```

#### UI Package

```text
HostelHubDashboard.java
→ Main application dashboard

StudentPanel.java
→ Student management interface

RoomPanel.java
→ Room management interface

AllocationPanel.java
→ Room allocation interface

AllocationsPanel.java
→ Displays existing room allocations

VacatePanel.java
→ Handles room vacancy operations
```

---

## 📋 Main Modules

### 👨‍🎓 1. Student Management

* Add student
* Store student details
* Manage student information

### 🏠 2. Room Management

* Add hostel rooms
* Store room details
* Track room availability
* Manage room types

### 🔑 3. Room Allocation

* Select a student
* Select an available room
* Allocate the room
* Store allocation details
* Update room availability

### 📋 4. View Allocations

* View allocated rooms
* View student details
* View allocation information

### 🚪 5. Vacate Room

* Select an allocated room
* Vacate the room
* Update room status
* Make the room available again

### 📊 6. Dashboard

The dashboard provides an overview of hostel information:

```text
Total Students
Total Rooms
Allocated Rooms
Available Rooms
```

---

## 🔄 Application Flow

```text
                START
                  ↓
              Dashboard
                  ↓
        ┌─────────┴─────────┐
        ↓                   ↓
   Manage Students     Manage Rooms
        ↓                   ↓
        └─────────┬─────────┘
                  ↓
           Allocate Room
                  ↓
         Check Availability
                  ↓
          Save Allocation
                  ↓
          Update Room Status
                  ↓
          View Allocations
                  ↓
             Vacate Room
                  ↓
        Update Room Status
                  ↓
                 END
```

---

## 🧑‍🎓 Student Allocation Flow

```text
Select Student
      ↓
Select Available Room
      ↓
Check Room Availability
      ↓
Create Allocation
      ↓
Save Allocation in Database
      ↓
Update Room Status
      ↓
Allocation Completed
```

---

## 🗄️ Database Design

HostelHub uses **MySQL** for storing and managing application data.

### Main Tables

```text
student
room
allocation
```

### Student Table

```text
student
├── student_id
├── name
└── phone
```

### Room Table

```text
room
├── room_id
├── room_type
└── status
```

### Allocation Table

```text
allocation
├── allocation_id
├── student_id
├── room_id
└── allocation_date
```

### Database Relationship

```text
Student
   │
   │ student_id
   ↓
Allocation
   ↑
   │ room_id
   │
 Room
```

---

## 🔌 Database Connection

The application uses **JDBC** to connect the Java application with MySQL.

The database connection is managed through the `DBConnection` utility class.

```text
Java Application
       ↓
      JDBC
       ↓
     MySQL
```

---

## ⚙️ How to Run the Project

### 1. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

### 2. Open the Project

Open the project in **Eclipse IDE**.

### 3. Configure MySQL

Create the required database and tables using MySQL Workbench.

### 4. Configure Database Connection

Update the database details inside the `DBConnection` class.

```text
Database URL
Username
Password
```

### 5. Add MySQL JDBC Driver

Add the **MySQL Connector/J** `.jar` file to the project build path.

### 6. Run the Application

In Eclipse:

```text
Right Click Main Class
        ↓
Run As
        ↓
Java Application
```

---

## ✨ Key Features

```text
✔ Student Management
✔ Room Management
✔ Room Allocation
✔ View Allocations
✔ Vacate Room
✔ Room Availability Tracking
✔ MySQL Database Integration
✔ JDBC Connectivity
✔ Java Swing Desktop Interface
✔ Layered Architecture
✔ Centralized Data Management
```

---

## 🔐 Data Management

All student, room, and allocation information is stored in the MySQL database.

The system helps maintain consistent records and reduces errors that can occur during manual hostel management.

---

## 🚀 Future Enhancements

The project can be extended with the following features:

* 🔐 Admin login and authentication
* 👥 Role-based access control
* 🔍 Student and room search
* 💰 Hostel fee management
* 📅 Check-in and check-out management
* 📧 Email notifications
* 📱 SMS notifications
* 📊 Reports and analytics
* 💾 Database backup and restore
* 🌐 Web-based version
* 📱 Mobile application
* 📝 Hostel complaint management
* 🏢 Multiple hostel/building management

---

## ✅ Advantages

* Reduces manual paperwork
* Saves time
* Improves data accuracy
* Simplifies room allocation
* Tracks room availability easily
* Provides centralized data management
* Easy-to-use desktop interface
* Uses structured layered architecture
* Provides reliable database storage

---

## 🎓 Project Information

```text
Project Name  : HostelHub
Project Type  : Java Desktop Application
Domain        : Hostel Management
Architecture  : Layered Architecture
Database      : MySQL
Connectivity  : JDBC
GUI           : Java Swing
IDE           : Eclipse
```

---

## 👨‍💻 Developed By

**Gopinath**

**Department of Computer Science and Engineering**

---

## 📄 License

This project is developed for **educational and academic purposes**.
