package com.servlet.pinjaman.repo;

import com.servlet.pinjaman.entity.Pinjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PinjamanRepo")
public interface PinjamanRepo extends JpaRepository<Pinjaman, Long> {
}
