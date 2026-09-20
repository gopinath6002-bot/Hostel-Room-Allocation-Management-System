package com.hostal.service;

import java.util.List;

import com.hostal.dao.allocationDAO;
import com.hostal.dao.allocationDAOImp1;
import com.hostal.model.allocation;

public class allocationServiceImp1 implements allocationService {

    private allocationDAO allocationDAO;

    public allocationServiceImp1() {
        allocationDAO = new allocationDAOImp1();
    }

    @Override
    public boolean allocateRoom(allocation allocation) {
        return allocationDAO.allocateRoom(allocation);
    }

    @Override
    public List<allocation> getAllAllocations() {
        return allocationDAO.getAllAllocations();
    }

    @Override
    public allocation getAllocationById(int allocationId) {
        return allocationDAO.getAllocationById(allocationId);
    }

    @Override
    public boolean updateAllocation(allocation allocation) {
        return allocationDAO.updateAllocation(allocation);
    }

    @Override
    public boolean deleteAllocation(int allocationId) {
        return allocationDAO.deleteAllocation(allocationId);
    }
}