package com.servlet.address.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.address.entity.Province;

@Repository("ProvinceRepo")
public interface ProvinceRepo extends JpaRepository<Province, Long>{

}
