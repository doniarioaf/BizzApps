package com.servlet.purchasereceive.repo;

import com.servlet.purchasereceive.entity.PurchaseReceiveDeposit;
import com.servlet.purchasereceive.entity.PurchaseReceiveDepositPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PurchaseReceiveDepositRepo")
public interface PurchaseReceiveDepositRepo extends JpaRepository<PurchaseReceiveDeposit, PurchaseReceiveDepositPK> {
}
