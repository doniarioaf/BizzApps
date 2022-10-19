package com.servlet.workorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.workorder.entity.DetailWorkOrder;
import com.servlet.workorder.entity.DetailWorkOrderPK;

@Repository("DetailWorkOrderRepo")
public interface DetailWorkOrderRepo extends JpaRepository<DetailWorkOrder, DetailWorkOrderPK> {
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_work_order where idworkorder = :idworkorder and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllDetail(@Param("idworkorder") long idworkorder,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
