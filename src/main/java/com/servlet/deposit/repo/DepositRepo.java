package com.servlet.deposit.repo;

import com.servlet.deposit.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Repository("DepositRepo")
public interface DepositRepo extends JpaRepository<Deposit, Long> {

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    @Query(
            value = "SELECT * FROM deposit WHERE depositdate >= :fromdate " +
                    "AND idcompany = :idcompany AND idbranch = :idbranch  "+
                    "AND depositdate <= :thruDate FOR UPDATE ",
            nativeQuery = true
    )
    List<Deposit> fingByRangeDate(@Param("idcompany") Long idcompany, @Param("idbranch") Long idbranch,@Param("fromdate") String fromdate,@Param("thruDate") String thruDate);

//    @Transactional
    @Query(
            value = "SELECT * FROM deposit WHERE " +
                    "idcompany = :idcompany AND idbranch = :idbranch  "+
                    "FOR UPDATE ",
            nativeQuery = true
    )
    List<Deposit> fingByIdcompanyAndBranch(@Param("idcompany") Long idcompany, @Param("idbranch") Long idbranch);
}
