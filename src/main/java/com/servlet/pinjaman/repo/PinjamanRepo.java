package com.servlet.pinjaman.repo;

import com.servlet.pinjaman.entity.Pinjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository("PinjamanRepo")
public interface PinjamanRepo extends JpaRepository<Pinjaman, Long> {

    @Transactional
    @Query(
            value = "SELECT * FROM pinjaman WHERE date >= :fromdate " +
                    "AND idcompany = :idcompany AND idbranch = :idbranch  "+
                    "AND date <= :thruDate FOR UPDATE ",
            nativeQuery = true
    )
    List<Pinjaman> fingByRangeDate(@Param("idcompany") Long idcompany, @Param("idbranch") Long idbranch,@Param("fromdate") String fromdate, @Param("thruDate") String thruDate);

    @Transactional
    @Query(
            value = "SELECT * FROM pinjaman WHERE " +
                    "idcompany = :idcompany AND idbranch = :idbranch "+
                    "FOR UPDATE ",
            nativeQuery = true
    )
    List<Pinjaman> fingByIdcompanyAndBranch(@Param("idcompany") Long idcompany, @Param("idbranch") Long idbranch);
}
