package com.hostal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hostal.model.allocation;
import com.hostal.util.dbconnection;

public class allocationDAOImp1 implements allocationDAO {

    @Override
    public boolean allocateRoom(allocation allocation) {

        String sql = "INSERT INTO allocation "
                   + "(student_id, room_id, allocation_date, status) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, allocation.getStudentId());
            ps.setInt(2, allocation.getRoomId());
            ps.setDate(3, allocation.getAllocationDate());
            ps.setString(4, allocation.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<allocation> getAllAllocations() {

        List<allocation> allocations = new ArrayList<>();

        String sql = "SELECT * FROM allocation";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                allocation a = new allocation();

                a.setAllocationId(
                    rs.getInt("allocation_id"));

                a.setStudentId(
                    rs.getInt("student_id"));

                a.setRoomId(
                    rs.getInt("room_id"));

                a.setAllocationDate(
                    rs.getDate("allocation_date"));

                a.setStatus(
                    rs.getString("status"));

                allocations.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allocations;
    }

    @Override
    public allocation getAllocationById(int allocationId) {

        String sql = "SELECT * FROM allocation "
                   + "WHERE allocation_id = ?";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, allocationId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                allocation a = new allocation();

                a.setAllocationId(
                    rs.getInt("allocation_id"));

                a.setStudentId(
                    rs.getInt("student_id"));

                a.setRoomId(
                    rs.getInt("room_id"));

                a.setAllocationDate(
                    rs.getDate("allocation_date"));

                a.setStatus(
                    rs.getString("status"));

                return a;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateAllocation(allocation allocation) {

        String sql = "UPDATE allocation SET "
                   + "student_id=?, room_id=?, "
                   + "allocation_date=?, status=? "
                   + "WHERE allocation_id=?";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, allocation.getStudentId());
            ps.setInt(2, allocation.getRoomId());
            ps.setDate(3, allocation.getAllocationDate());
            ps.setString(4, allocation.getStatus());
            ps.setInt(5, allocation.getAllocationId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteAllocation(int allocationId) {

        String sql = "DELETE FROM allocation "
                   + "WHERE allocation_id = ?";

        try (Connection con = dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, allocationId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
