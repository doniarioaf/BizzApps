package com.servlet.purchasereceive.repo;

import com.servlet.purchasereceive.entity.PurchaseReceiveCharge;
import com.servlet.purchasereceive.entity.PurchaseReceiveChargePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("PurchaseReceiveChargeRepo")
public interface PurchaseReceiveChargeRepo extends JpaRepository<PurchaseReceiveCharge, PurchaseReceiveChargePK> {

    @Transactional
    @Modifying
    @Query(value ="delete from purchasereceive_charge where idpurchasereceive = :idpurchasereceive ",nativeQuery = true)
    void deleteAllDetailByIdPurchaseReceive(@Param("idpurchasereceive") long idpurchasereceive);
}
