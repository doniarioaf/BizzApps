package com.servlet.draftpurchasereceive.repo;

import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveItems;
import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveItemsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("DraftPurchaseReceiveItemsRepo")
public interface DraftPurchaseReceiveItemsRepo extends JpaRepository<DraftPurchaseReceiveItems, DraftPurchaseReceiveItemsPK> {

    @Transactional
    @Modifying
    @Query(value ="delete from draft_purchasereceive_items where iddraftpurchasereceive = :iddraftpurchasereceive ",nativeQuery = true)
    void deleteAllDetailByIdDraftPurchaseReceive(@Param("iddraftpurchasereceive") long iddraftpurchasereceive);
}
