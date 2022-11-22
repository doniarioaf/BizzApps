package com.servlet.invoice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.invoice.entity.DetailInvoicePrice;
import com.servlet.invoice.entity.DetailInvoicePricePK;

@Repository("DetailInvoicePriceRepo")
public interface DetailInvoicePriceRepo extends JpaRepository<DetailInvoicePrice, DetailInvoicePricePK>{
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_invoice_price where idinvoice = :idinvoice and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllDetail(@Param("idinvoice") long idinvoice,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
