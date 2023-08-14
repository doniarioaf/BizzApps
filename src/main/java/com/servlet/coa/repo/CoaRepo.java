package com.servlet.coa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.coa.entity.Coa;

@Repository("CoaRepo")
public interface CoaRepo extends JpaRepository<Coa, Long>{

}
