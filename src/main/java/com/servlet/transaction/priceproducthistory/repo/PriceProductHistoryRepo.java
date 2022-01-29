package com.servlet.transaction.priceproducthistory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.transaction.priceproducthistory.entity.PriceProductHistory;

@Repository("PriceProductHistoryRepo")
public interface PriceProductHistoryRepo extends JpaRepository<PriceProductHistory, Long>{

}
