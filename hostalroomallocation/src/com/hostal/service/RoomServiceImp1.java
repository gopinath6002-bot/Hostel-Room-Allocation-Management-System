package com.hostal.service;

import java.util.List;

import com.hostal.dao.RoomDAO;
import com.hostal.dao.RoomDAOImp1;
import com.hostal.model.room;

public class RoomServiceImp1 implements RoomService {

    private RoomDAO roomDAO;

    public RoomServiceImp1() {
        roomDAO = new RoomDAOImp1();
    }

    @Override
    public boolean addRoom(room room) {
        return roomDAO.addRoom(room);
    }

    @Override
    public List<room> getAllRooms() {
        return roomDAO.getAllrooms();
    }

    @Override
    public room getRoomById(int roomId) {
        return roomDAO.getroomById(roomId);
    }

    @Override
    public boolean updateRoom(room room) {
        return roomDAO.updateroom(room);
    }

    @Override
    public boolean deleteRoom(int roomId) {
        return roomDAO.deleteroom(roomId);
    }
}
