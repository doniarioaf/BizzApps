package com.servlet.bank.repo;

import com.servlet.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("BankRepo")
public interface BankRepo extends JpaRepository<Bank, Long> {
}
