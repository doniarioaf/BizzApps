package com.servlet.pricelist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.pricelist.entity.PriceList;

@Repository("PriceListRepo")
public interface PriceListRepo extends JpaRepository<PriceList, Long>{

}
