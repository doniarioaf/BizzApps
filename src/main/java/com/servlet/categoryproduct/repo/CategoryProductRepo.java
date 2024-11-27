package com.servlet.categoryproduct.repo;

import com.servlet.categoryproduct.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("CategoryProductRepo")
public interface CategoryProductRepo extends JpaRepository<CategoryProduct, Long> {
}
