package com.servlet.customer.repo;

import com.servlet.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("CustomerRepo")
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
