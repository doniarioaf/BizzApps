package com.servlet.journal.repo;

import com.servlet.journal.entity.JournalDetail;
import com.servlet.journal.entity.JournalDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("JournalDetailRepo")
public interface JournalDetailRepo extends JpaRepository<JournalDetail, JournalDetailPK> {

    @Transactional
    @Modifying
    @Query(value ="delete from journal_detail where journalid = :journalid ",nativeQuery = true)
    void deleteDetailByIdJournal(@Param("journalid") long journalid);

    @Transactional
    @Modifying
    @Query(value ="delete from journal_detail where sourcenumber = :sourcenumber ",nativeQuery = true)
    void deleteDetailBySourceNumber(@Param("sourcenumber") String sourcenumber);

    @Transactional
    @Modifying
    @Query(value ="delete from journal_detail where sourcenumber IN (:sourcenumber) ",nativeQuery = true)
    void deleteDetailByListSourceNumber(@Param("sourcenumber") List<String> sourcenumber);

    @Transactional
    @Query(
            value = "SELECT * FROM journal_detail WHERE idcompany =:idcompany and idbranch =:idbranch and LOWER(TRIM(sourcenumber)) = LOWER(TRIM(:sourcenumber)) " ,
            nativeQuery = true
    )
    List<JournalDetail> fingBySourceNumber(@Param("idcompany") Long idcompany,@Param("idbranch") Long idbranch,@Param("sourcenumber") String sourcenumber);

    @Transactional
    @Query(
            value = "SELECT * FROM journal_detail WHERE idcompany =:idcompany and idbranch =:idbranch and LOWER(TRIM(sourcenumber)) = LOWER(TRIM(:sourcenumber)) AND accountcode =:accountcode " ,
            nativeQuery = true
    )
    List<JournalDetail> fingBySourceNumberAndAccCode(@Param("idcompany") Long idcompany,@Param("idbranch") Long idbranch,@Param("sourcenumber") String sourcenumber, @Param("accountcode") String accountcode);

    @Transactional
    @Query(
            value = "SELECT * FROM journal_detail WHERE idcompany =:idcompany and idbranch =:idbranch and idvendor =:idvendor and LOWER(TRIM(sourcenumber)) = LOWER(TRIM(:sourcenumber)) " ,
            nativeQuery = true
    )
    List<JournalDetail> fingBySourceNumberAndIdVendor(@Param("idcompany") Long idcompany,@Param("idbranch") Long idbranch,@Param("idvendor") Long idvendor,@Param("sourcenumber") String sourcenumber);

    @Transactional
    @Modifying
    @Query(value ="delete from journal_detail where concat(idvendor,sourcenumber) IN (:idvendorsourcenumber) ",nativeQuery = true)
    void deleteDetailByListIdVendorAndSourceNumber(@Param("idvendorsourcenumber") List<String> idvendorsourcenumber);

    @Transactional
    @Modifying
    @Query(value ="DELETE FROM journal_detail jd WHERE EXISTS ( SELECT 1 FROM journal_detail jd2  WHERE jd.accountcode = jd2.accountcode AND jd.sourcenumber = jd2.sourcenumber AND jd.idvendor = jd2.idvendor  AND jd.journalid < jd2.journalid) ",nativeQuery = true)
    void deleteDetailDoubleSourceNumberData();

    @Transactional
    @Modifying
    @Query(value ="delete from journal_detail where sourcenumber = :sourcenumber AND accountcode = :accountcode ",nativeQuery = true)
    void deleteDetailBySourceNumberAccCode(@Param("sourcenumber") String sourcenumber, @Param("accountcode") String accountcode);
}
