package com.servlet.cancelpackinglist.repo;

import com.servlet.cancelpackinglist.entity.CancelPackingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("CancelPackingListRepo")
public interface CancelPackingListRepo extends JpaRepository<CancelPackingList, Long> {

    @Transactional
    @Modifying
    @Query(value ="update cancel_packinglist set isdelete = true, deleteby = :iduser, deletedate = CURRENT_TIMESTAMP where idcompany = :idcompany and idbranch = :idbranch and  idpackinglist = :idpackinglist and isdelete = false ",nativeQuery = true)
    void deleteAllByIdPackingList(@Param("iduser") long iduser, @Param("idcompany") long idcompany, @Param("idbranch") long idbranch, @Param("idpackinglist") long idpackinglist);
}
