package com.servlet.admin.company.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.servlet.admin.company.entity.Company;


@Repository("CompanyRepo")
public interface CompanyRepo extends JpaRepository<Company, Long>{
	
	@Query(value =" select * from m_company "
			+ " where isactive = true and isdelete = false ",nativeQuery = true)
	public List<Company> getListCompanyActive();
}
