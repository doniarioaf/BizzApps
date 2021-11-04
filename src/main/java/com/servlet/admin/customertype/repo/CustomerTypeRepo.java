package com.servlet.admin.customertype.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.admin.customertype.entity.CustomerType;

@Repository("CustomerTypeRepo")
public interface CustomerTypeRepo extends JpaRepository<CustomerType, Long>{
	
}
