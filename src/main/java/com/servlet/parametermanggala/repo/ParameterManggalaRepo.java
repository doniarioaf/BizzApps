package com.servlet.parametermanggala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.parametermanggala.entity.ParameterManggala;

@Repository("ParameterManggalaRepo")
public interface ParameterManggalaRepo extends JpaRepository<ParameterManggala, Long>{

}
