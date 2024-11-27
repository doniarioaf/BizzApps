package com.servlet.inventori.repo;

import com.servlet.inventori.entity.Inventori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("InventoriRepo")
public interface InventoriRepo extends JpaRepository<Inventori, Long> {
}
