package com.servlet.stockadjusment.repo;

import com.servlet.stockadjusment.entity.StockAdjusment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("StockAdjusmentRepo")
public interface StockAdjusmentRepo extends JpaRepository<StockAdjusment, Long> {
}
