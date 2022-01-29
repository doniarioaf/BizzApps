package com.servlet.transaction.productstock.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.transaction.productstock.entity.ProductStock;
import com.servlet.transaction.productstock.entity.ProductStockPK;

@Repository("ProductStockRepo")
public interface ProductStockRepo extends JpaRepository<ProductStock, ProductStockPK>{

}
