package com.servlet.pricelist.repo;

import com.servlet.pricelist.entity.PriceItemPK;
import com.servlet.pricelist.entity.PriceListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PriceListItemRepo")
public interface PriceListItemRepo extends JpaRepository<PriceListItem, PriceItemPK> {
}
