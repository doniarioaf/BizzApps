package com.servlet.vendor.repo;

import com.servlet.vendor.entity.VendorCategoryProductNotInclude;
import com.servlet.vendor.entity.VendorCategoryProductNotIncludePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("VendorCategoryProductNotIncludeRepo")
public interface VendorCategoryProductNotIncludeRepo extends JpaRepository<VendorCategoryProductNotInclude, VendorCategoryProductNotIncludePK> {
    @Transactional
    @Modifying
    @Query(value ="delete from vendor_categoryproduct_not_include where idvendor = :idvendor ",nativeQuery = true)
    void deleteAllByIdVendor(@Param("idvendor") long idvendor);
}
