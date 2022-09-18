package com.servlet.customermanggala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoKementerian;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoKementerianPK;

@Repository("DetailCustomerManggalaInfoKementerianRepo")
public interface DetailCustomerManggalaInfoKementerianRepo extends JpaRepository<DetailCustomerManggalaInfoKementerian, DetailCustomerManggalaInfoKementerianPK>{
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_customer_manggala_info_kementerian where idcustomermanggala = :idcustomermanggala and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllInfoKementerian(@Param("idcustomermanggala") long idcustomermanggala,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
