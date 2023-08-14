package com.servlet.bankaccount.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.bankaccount.entity.BankAccount;

@Repository("BankAccountRepo")
public interface BankAccountRepo extends JpaRepository<BankAccount, Long>{

}
