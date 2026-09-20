package com.hostal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hostal.model.room;
import com.hostal.util.dbconnection;

public class RoomDAOImp1 implements RoomDAO {

    @Override
    public boolean addRoom(room room) {
        String sql = "INSERT INTO room "
                   + "(room_id, room_type, capacity, occupied, status) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, room.getroomId());
            ps.setString(2, room.getRoomType());
            ps.setInt(3, room.getCapacity());
            ps.setInt(4, room.getOccupied());
            ps.setString(5, room.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<room> getAllrooms() {
        List<room> rooms = new ArrayList<>();

        String sql = "SELECT * FROM room";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                room r = new room();

                r.setRoomId(rs.getInt("room_id"));
                r.setRoomType(rs.getString("room_type"));
                r.setCapacity(rs.getInt("capacity"));
                r.setOccupied(rs.getInt("occupied"));
                r.setStatus(rs.getString("status"));

                rooms.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public room getroomById(int roomId) {
        String sql = "SELECT * FROM room WHERE room_id = ?";
        room r = null;

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = new room();

                r.setRoomId(rs.getInt("room_id"));
                r.setRoomType(rs.getString("room_type"));
                r.setCapacity(rs.getInt("capacity"));
                r.setOccupied(rs.getInt("occupied"));
                r.setStatus(rs.getString("status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }

    @Override
    public boolean updateroom(room room) {
        String sql = "UPDATE room SET room_type=?, capacity=?, "
                   + "occupied=?, status=? WHERE room_id=?";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, room.getRoomType());
            ps.setInt(2, room.getCapacity());
            ps.setInt(3, room.getOccupied());
            ps.setString(4, room.getStatus());
            ps.setInt(5, room.getroomId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteroom(int roomId) {
        String sql = "DELETE FROM room WHERE room_id = ?";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}