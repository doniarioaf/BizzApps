package com.servlet.pricelist.repo;

import com.servlet.pricelist.entity.PriceItemPK;
import com.servlet.pricelist.entity.PriceListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository("PriceListItemRepo")
public interface PriceListItemRepo extends JpaRepository<PriceListItem, PriceItemPK> {

    @Transactional
    @Modifying
    @Query(value ="delete from pricelistitem where pricelistid = :pricelistid ",nativeQuery = true)
    void deleteAllDetailByPriceListID(@Param("pricelistid") long pricelistid);
}
