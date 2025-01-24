package com.servlet.deposit.repo;

import com.servlet.deposit.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("DepositRepo")
public interface DepositRepo extends JpaRepository<Deposit, Long> {
}
