package com.servlet.admin.customerproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.admin.customerproject.entity.CustomerProject;
import com.servlet.admin.customerproject.entity.CustomerProjectPK;

@Repository("CustomerProjectRepo")
public interface CustomerProjectRepo extends JpaRepository<CustomerProject, CustomerProjectPK>{

}
