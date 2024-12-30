package com.servlet.cargo.repo;

import com.servlet.cargo.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("CargoRepo")
public interface CargoRepo extends JpaRepository<Cargo, Long> {
}
