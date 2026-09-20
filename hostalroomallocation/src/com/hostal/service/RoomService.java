package com.hostal.service;

import java.util.List;
import com.hostal.model.room;

public interface RoomService {

    boolean addRoom(room room);

    List<room> getAllRooms();

    room getRoomById(int roomId);

    boolean updateRoom(room room);

    boolean deleteRoom(int roomId);
}
