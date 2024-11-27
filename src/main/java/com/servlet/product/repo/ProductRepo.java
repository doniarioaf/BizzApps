package com.servlet.product.repo;

import com.servlet.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ProductRepo")
public interface ProductRepo extends JpaRepository<Product, Long> {
}
