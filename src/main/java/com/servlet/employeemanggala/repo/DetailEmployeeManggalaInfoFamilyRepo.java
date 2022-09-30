package com.servlet.employeemanggala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.employeemanggala.entity.DetailEmployeeManggalaInfoFamily;
import com.servlet.employeemanggala.entity.DetailEmployeeManggalaInfoFamilyPK;

@Repository("DetailEmployeeManggalaInfoFamilyRepo")
public interface DetailEmployeeManggalaInfoFamilyRepo extends JpaRepository<DetailEmployeeManggalaInfoFamily, DetailEmployeeManggalaInfoFamilyPK> {
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_employee_manggala_family where idemployeemanggala = :idemployeemanggala and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllInfoFamily(@Param("idemployeemanggala") long idemployeemanggala,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
