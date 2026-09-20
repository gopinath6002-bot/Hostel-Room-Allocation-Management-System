package com.hostal.dao;

import java.util.List;
import com.hostal.model.room;

public interface RoomDAO {

    boolean addRoom(room room);

    List<room> getAllrooms();

    room getroomById(int roomId);

    boolean updateroom(room room);

    boolean deleteroom(int roomId);
}
