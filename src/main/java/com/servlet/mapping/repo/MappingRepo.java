package com.servlet.mapping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.mapping.entity.Mapping;

@Repository("MappingRepo")
public interface MappingRepo extends JpaRepository<Mapping, Long>{

}
