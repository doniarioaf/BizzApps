package com.servlet.transaction.stockproducthistory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.transaction.stockproducthistory.entity.StockProductHistory;

@Repository("StockProductHistoryRepo")
public interface StockProductHistoryRepo extends JpaRepository<StockProductHistory, Long>{

}
