package com.servlet.packinglist.repo;

import com.servlet.packinglist.entity.PackingListItem;
import com.servlet.packinglist.entity.PackingListItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("PakcingListItemRepo")
public interface PakcingListItemRepo extends JpaRepository<PackingListItem, PackingListItemPK> {

    @Transactional
    @Modifying
    @Query(value ="delete from packinglist_item where idpackinglist = :idpackinglist ",nativeQuery = true)
    void deleteAllDetailByIdPackingList(@Param("idpackinglist") long idpackinglist);
}
