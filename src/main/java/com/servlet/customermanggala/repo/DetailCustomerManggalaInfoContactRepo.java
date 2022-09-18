package com.servlet.customermanggala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoContact;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoContactPK;

@Repository("DetailCustomerManggalaInfoContactRepo")
public interface DetailCustomerManggalaInfoContactRepo extends JpaRepository<DetailCustomerManggalaInfoContact, DetailCustomerManggalaInfoContactPK>{
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_customer_manggala_info_contact where idcustomermanggala = :idcustomermanggala and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllInfoContact(@Param("idcustomermanggala") long idcustomermanggala,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
