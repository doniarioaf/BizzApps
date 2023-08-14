package com.servlet.employeemanggala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.employeemanggala.entity.EmployeeManggala;

@Repository("EmployeeManggalaRepo")
public interface EmployeeManggalaRepo extends JpaRepository<EmployeeManggala, Long>{

}
