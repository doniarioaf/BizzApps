package com.servlet.pricelist.repo;

import com.servlet.pricelist.entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PriceListRepo")
public interface PriceListRepo extends JpaRepository<PriceList, Long> {
}
