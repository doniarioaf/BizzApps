package com.servlet.vendor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.vendor.entity.Vendor;

@Repository("VendorRepo")
public interface VendorRepo extends JpaRepository<Vendor, Long>{

}
