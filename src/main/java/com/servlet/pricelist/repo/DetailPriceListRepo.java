package com.servlet.pricelist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.pricelist.entity.DetailPriceList;
import com.servlet.pricelist.entity.DetailPriceListPK;

@Repository("DetailPriceListRepo")
public interface DetailPriceListRepo extends JpaRepository<DetailPriceList, DetailPriceListPK>{
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_price_list where idpricelist = :idpricelist and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllDetail(@Param("idpricelist") long idpricelist,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);

}
