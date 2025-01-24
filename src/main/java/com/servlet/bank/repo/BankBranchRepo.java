package com.servlet.bank.repo;

import com.servlet.bank.entity.BankBranch;
import com.servlet.bank.entity.BankBranchPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("BankBranchRepo")
public interface BankBranchRepo extends JpaRepository<BankBranch, BankBranchPK> {

    @Transactional
    @Modifying
    @Query(value ="delete from bank_branch where idbank = :idbank ",nativeQuery = true)
    void deleteAllDetailByIdBank(@Param("idbank") long idbank);
}
