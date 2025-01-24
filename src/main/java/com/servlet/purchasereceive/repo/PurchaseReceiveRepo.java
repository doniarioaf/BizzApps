package com.servlet.purchasereceive.repo;

import com.servlet.purchasereceive.entity.PurchaseReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PurchaseReceiveRepoo")
public interface PurchaseReceiveRepo extends JpaRepository<PurchaseReceive, Long> {
}
