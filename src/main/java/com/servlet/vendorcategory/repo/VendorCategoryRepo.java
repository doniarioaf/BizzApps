package com.servlet.vendorcategory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.vendorcategory.entity.VendorCategory;

@Repository("VendorCategoryRepo")
public interface VendorCategoryRepo extends JpaRepository<VendorCategory, Long>{

}
