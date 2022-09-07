package com.servlet.address.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.address.entity.City;

@Repository("CityRepo")
public interface CityRepo extends JpaRepository<City, Long>{

}
