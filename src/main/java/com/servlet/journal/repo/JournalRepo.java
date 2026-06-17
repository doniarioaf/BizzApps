package com.servlet.journal.repo;

import com.servlet.journal.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("JournalRepo")
public interface JournalRepo extends JpaRepository<Journal, Long> {

    @Transactional
    @Modifying
    @Query(value ="delete from journal where id = :id ",nativeQuery = true)
    void deleteByIdJournal(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(value ="delete from journal where sourcenumber = :sourcenumber ",nativeQuery = true)
    void deleteBySourceNumber(@Param("sourcenumber") String sourcenumber);

    @Transactional
    @Modifying
    @Query(value ="delete from journal where sourcenumber IN (:sourcenumber) ",nativeQuery = true)
    void deleteByListSourceNumber(@Param("sourcenumber") List<String> sourcenumber);

    @Transactional
    @Modifying
    @Query(value ="delete from journal where concat(idvendor,sourcenumber) IN (:idvendorsourcenumber) ",nativeQuery = true)
    void deleteByListSourceNumberIdVendor(@Param("idvendorsourcenumber") List<String> idvendorsourcenumber);

    @Transactional
    @Modifying
    @Query(value ="delete from journal WHERE idcompany =:idcompany and idbranch =:idbranch ",nativeQuery = true)
    void deleteAllJournal(@Param("idcompany") Long idcompany,@Param("idbranch") Long idbranch);

    @Transactional
    @Modifying
    @Query(value ="delete from journal j where idcompany =:idcompany and idbranch =:idbranch and j.id IN ( select jd.journalid from journal_detail jd where jd.idcompany =:idcompany and jd.idbranch =:idbranch and jd.sourcedocumentdate >= :fromdate AND jd.sourcedocumentdate <= :thruDate ) ",nativeQuery = true)
    void deleteAllJournalBySourceDocDate(@Param("idcompany") Long idcompany,@Param("idbranch") Long idbranch,@Param("fromdate") String fromdate,@Param("thruDate") String thruDate);
}
