package com.servlet.address.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.address.entity.SubDistrict;

@Repository("SubDistrictRepo")
public interface SubDistrictRepo extends JpaRepository<SubDistrict, Long>{

}
