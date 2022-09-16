package com.servlet.customermanggala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.customermanggala.entity.CustomerManggala;


@Repository("CustomerManggalaRepo")
public interface CustomerManggalaRepo extends JpaRepository<CustomerManggala, Long>{

}
