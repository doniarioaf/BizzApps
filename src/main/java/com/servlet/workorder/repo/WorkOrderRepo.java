package com.servlet.workorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.workorder.entity.WorkOrder;

@Repository("WorkOrderRepo")
public interface WorkOrderRepo extends JpaRepository<WorkOrder, Long> {

}
