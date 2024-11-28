package com.servlet.mappingstock.repo;

import com.servlet.mappingstock.entity.MappingStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("MappingStockRepo")
public interface MappingStockRepo extends JpaRepository<MappingStock, Long> {
}
