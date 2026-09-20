package com.hostal.model;

public class room {

    private int roomId;
    private String roomType;
    private int capacity;
    private int occupied;
    private String status;

    public room() {
    }

    public room(int roomId, String roomType, int capacity,
                int occupied, String status) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.capacity = capacity;
        this.occupied = occupied;
        this.status = status;
    }

    public int getroomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
