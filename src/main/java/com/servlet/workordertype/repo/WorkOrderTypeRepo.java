package com.servlet.workordertype.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.workordertype.entity.WorkOrderType;

@Repository("WorkOrderTypeRepo")
public interface WorkOrderTypeRepo extends JpaRepository<WorkOrderType, Long>{

}
