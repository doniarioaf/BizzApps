package com.servlet.purchasereceive.repo;

import com.servlet.purchasereceive.entity.PurchaseReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("PurchaseReceiveRepoo")
public interface PurchaseReceiveRepo extends JpaRepository<PurchaseReceive, Long> {

    @Transactional
    @Query(
            value = "SELECT * FROM purchasereceive WHERE transactiondate >= :fromdate" +
                    "AND transactiondate < :thruDate FOR UPDATE ",
            nativeQuery = true
    )
    List<PurchaseReceive> fingByRangeDate(@Param("fromdate") String fromdate, @Param("thruDate") String thruDate);
}
