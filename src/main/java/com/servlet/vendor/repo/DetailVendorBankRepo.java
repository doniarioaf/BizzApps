package com.servlet.vendor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.vendor.entity.DetailVendorBank;
import com.servlet.vendor.entity.DetailVendorBankPK;

@Repository("DetailVendorBankRepo")
public interface DetailVendorBankRepo extends JpaRepository<DetailVendorBank, DetailVendorBankPK>{
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_vendor_bank where idvendor = :idvendor and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllVendorBank(@Param("idvendor") long idvendor,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
