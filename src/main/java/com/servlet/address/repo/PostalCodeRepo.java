package com.servlet.address.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.address.entity.PostalCode;

@Repository("PostalCodeRepo")
public interface PostalCodeRepo extends JpaRepository<PostalCode, Long>{

}
