package com.servlet.admin.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.admin.product.entity.Product;

@Repository("ProductRepo")
public interface ProductRepo extends JpaRepository<Product, Long>{

}
