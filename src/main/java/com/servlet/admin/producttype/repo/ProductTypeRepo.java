package com.servlet.admin.producttype.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.admin.producttype.entity.ProductType;

@Repository("ProductTypeRepo")
public interface ProductTypeRepo extends JpaRepository<ProductType, Long>{

}
