package com.servlet.stockitems.repo;

import com.servlet.stockitems.entity.StockItems;
import com.servlet.stockitems.entity.StockItemsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PurchaseReceiveRepo")
public interface StockItemsRepo extends JpaRepository<StockItems, StockItemsPK> {
}
