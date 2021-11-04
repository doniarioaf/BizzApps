package com.servlet.admin.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.admin.customer.entity.Customer;

@Repository("CustomerRepo")
public interface CustomerRepo extends JpaRepository<Customer, Long>{
	

}
