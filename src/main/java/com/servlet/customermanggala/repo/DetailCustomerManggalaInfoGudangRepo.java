package com.servlet.customermanggala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoGudang;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoGudangPK;

@Repository("DetailCustomerManggalaInfoGudangRepo")
public interface DetailCustomerManggalaInfoGudangRepo extends JpaRepository<DetailCustomerManggalaInfoGudang, DetailCustomerManggalaInfoGudangPK>{
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_customer_manggala_info_gudang where idcustomermanggala = :idcustomermanggala and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllInfoGudang(@Param("idcustomermanggala") long idcustomermanggala,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
