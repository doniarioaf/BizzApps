package com.servlet.purchasereceive.repo;

import com.servlet.purchasereceive.entity.PurchaseReceiveItems;
import com.servlet.purchasereceive.entity.PurchaseReceiveItemsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("PurchaseReceiveItemsRepo")
public interface PurchaseReceiveItemsRepo extends JpaRepository<PurchaseReceiveItems, PurchaseReceiveItemsPK> {

    @Transactional
    @Modifying
    @Query(value ="delete from purchasereceive_item where idpurchasereceive = :idpurchasereceive ",nativeQuery = true)
    void deleteAllDetailByIdPurchaseReceive(@Param("idpurchasereceive") long idpurchasereceive);
}
