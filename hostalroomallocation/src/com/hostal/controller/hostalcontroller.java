package com.hostal.controller;

import java.util.Scanner;
import java.util.List;
import com.hostal.model.room;
import com.hostal.model.students;
import com.hostal.service.studentService;
import com.hostal.service.studentServiceImp1;
import com.hostal.service.RoomService;
import com.hostal.service.RoomServiceImp1;
import com.hostal.model.allocation;
import com.hostal.service.allocationService;
import com.hostal.service.allocationServiceImp1;

public class hostalcontroller {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        studentService studentService = new studentServiceImp1();
        RoomService RoomService=new RoomServiceImp1();
        allocationService allocationService = new allocationServiceImp1();

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println(" HOSTAL ROOM ALLOCATION SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. Add Room");
            System.out.println("3. Allocate Room");
            System.out.println("4. View Allocations");
            System.out.println("5. Vacate Room");
            System.out.println("6. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String department = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter Room ID: ");
                    int roomId = sc.nextInt();

                    students student = new students();

                    student.setStudentId(studentId);
                    student.setName(name);
                    student.setDepartment(department);
                    student.setPhone(phone);
                    student.setRoomId(roomId);

                    if (studentService.addStudent(student)) {
                        System.out.println("Student added successfully!");
                    } else {
                        System.out.println("Failed to add student.");
                    }

                    break;                    
                    

                case 2:
                

                    System.out.print("Enter Room ID: ");
                    int room_Id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Room Type: ");
                    String roomType = sc.nextLine();

                    System.out.print("Enter Capacity: ");
                    int capacity = sc.nextInt();

                    System.out.print("Enter Occupied: ");
                    int occupied = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Status: ");
                    String status = sc.nextLine();

                    room newRoom = new room();
                    
                    newRoom.setRoomId(room_Id);
                    newRoom.setRoomType(roomType);
                    newRoom.setCapacity(capacity);
                    newRoom.setOccupied(occupied);
                    newRoom.setStatus(status);

                    if (RoomService.addRoom(newRoom)) {
                        System.out.println("Room added successfully!");
                    } else {
                        System.out.println("Failed to add room.");
                    }

                    break;

                case 3:
                

                    System.out.print("Enter Student ID: ");
                    int allocStudentId = sc.nextInt();

                    System.out.print("Enter Room ID: ");
                    int allocRoomId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Allocation Date (YYYY-MM-DD): ");
                    String dateInput = sc.nextLine();

                    System.out.print("Enter Status: ");
                    String allocStatus = sc.nextLine();

                    allocation newAllocation = new allocation();

                    newAllocation.setStudentId(allocStudentId);
                    newAllocation.setRoomId(allocRoomId);
                    newAllocation.setAllocationDate(
                        java.sql.Date.valueOf(dateInput)
                    );
                    newAllocation.setStatus(allocStatus);

                    if (allocationService.allocateRoom(newAllocation)) {
                        System.out.println("Room allocated successfully!");
                    } else {
                        System.out.println("Failed to allocate room.");
                    }

                    break;

                case 4:
                

                    List<allocation> allocations =
                        allocationService.getAllAllocations();

                    if (allocations.isEmpty()) {

                        System.out.println("No allocations found.");

                    } else {

                        System.out.println("\n===== ROOM ALLOCATIONS =====");

                        for (allocation a : allocations) {

                            System.out.println("Allocation ID: "
                                    + a.getAllocationId());

                            System.out.println("Student ID: "
                                    + a.getStudentId());

                            System.out.println("Room ID: "
                                    + a.getRoomId());

                            System.out.println("Allocation Date: "
                                    + a.getAllocationDate());

                            System.out.println("Status: "
                                    + a.getStatus());

                            System.out.println("----------------------------");
                        }
                    }

                    break;

                case 5:
                
                    System.out.print("Enter Allocation ID to vacate: ");
                    int allocationId = sc.nextInt();

                    if (allocationService.deleteAllocation(allocationId)) {
                        System.out.println("Room vacated successfully!");
                    } else {
                        System.out.println("Failed to vacate room.");
                    }

                    break;

                case 6:
                    System.out.println("Thank you! Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}