package com.servlet.draftpurchasereceive.repo;

import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("DraftPurchaseReceiveRepo")
public interface DraftPurchaseReceiveRepo extends JpaRepository<DraftPurchaseReceive, Long> {
}
