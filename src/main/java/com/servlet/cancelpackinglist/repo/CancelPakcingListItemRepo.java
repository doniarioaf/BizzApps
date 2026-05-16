package com.servlet.cancelpackinglist.repo;

import com.servlet.cancelpackinglist.entity.CancelPackingListItem;
import com.servlet.cancelpackinglist.entity.CancelPackingListItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CancelPakcingListItemRepo extends JpaRepository<CancelPackingListItem, CancelPackingListItemPK> {

    @Transactional
    @Modifying
    @Query(value ="delete from cancel_packinglistitems where idcancelpackinglist = :idcancelpackinglist ",nativeQuery = true)
    void deleteAllDetailByIdCancelPackingList(@Param("idcancelpackinglist") long idcancelpackinglist);
}
