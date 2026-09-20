package com.hostal.dao;

import java.util.List;
import com.hostal.model.allocation;

public interface allocationDAO {

    boolean allocateRoom(allocation allocation);

    List<allocation> getAllAllocations();

    allocation getAllocationById(int allocationId);

    boolean updateAllocation(allocation allocation);

    boolean deleteAllocation(int allocationId);
}
