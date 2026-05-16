package com.servlet.packinglist.repo;

import com.servlet.packinglist.entity.PackingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("PakcingListRepo")
public interface PakcingListRepo extends JpaRepository<PackingList, Long> {

    @Transactional
    @Modifying
    @Query(value ="update packinglist set isalreadyupdateprice = false where idcompany = :idcompany and idbranch = :idbranch and  idcustomer = :idcustomer and idpricelist = :idpricelist ",nativeQuery = true)
    void updateColumsIsAlreadyUpdatePriceToFalse(@Param("idcompany") long idcompany, @Param("idbranch") long idbranch,@Param("idcustomer") long idcustomer, @Param("idpricelist") long idpricelist);
}
