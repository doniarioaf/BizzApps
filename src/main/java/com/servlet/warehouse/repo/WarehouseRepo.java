package com.servlet.warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.warehouse.entity.Warehouse;

@Repository("WarehouseRepo")
public interface WarehouseRepo extends JpaRepository<Warehouse, Long>{
	
	@Transactional
	@Modifying
	@Query(value ="update m_warehouse set idcustomer = null where idcustomer = :idcustomer and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void setCustomerNullByIdCustomer(@Param("idcustomer") long id,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
