package com.servlet.address.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.address.entity.District;

@Repository("DistrictRepo")
public interface DistrictRepo extends JpaRepository<District, Long>{

}
