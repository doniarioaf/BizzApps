package com.servlet.vendor.repo;

import com.servlet.vendor.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("VendorRepo")
public interface VendorRepo extends JpaRepository<Vendor, Long> {
}
