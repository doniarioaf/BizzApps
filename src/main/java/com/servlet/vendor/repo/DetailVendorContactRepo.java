package com.servlet.vendor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.servlet.vendor.entity.DetailVendorContact;
import com.servlet.vendor.entity.DetailVendorContactPK;

@Repository("DetailVendorContactRepo")
public interface DetailVendorContactRepo extends JpaRepository<DetailVendorContact, DetailVendorContactPK>{
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_vendor_contact where idvendor = :idvendor and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllVendorContact(@Param("idvendor") long idvendor,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
