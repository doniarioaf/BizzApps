package com.servlet.warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.warehouse.entity.Warehouse;

@Repository("WarehouseRepo")
public interface WarehouseRepo extends JpaRepository<Warehouse, Long>{

}
