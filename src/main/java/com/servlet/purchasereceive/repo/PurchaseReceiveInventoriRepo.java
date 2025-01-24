package com.servlet.purchasereceive.repo;

import com.servlet.purchasereceive.entity.PurchaseReceiveInventori;
import com.servlet.purchasereceive.entity.PurchaseReceiveInventoriPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("PurchaseReceiveInventoriRepo")
public interface PurchaseReceiveInventoriRepo extends JpaRepository<PurchaseReceiveInventori, PurchaseReceiveInventoriPK> {
    @Transactional
    @Modifying
    @Query(value ="delete from purchasereceive_inventori where idpurchasereceive = :idpurchasereceive ",nativeQuery = true)
    void deleteAllDetailByIdPurchaseReceiveInventory(@Param("idpurchasereceive") long idpurchasereceive);
}
