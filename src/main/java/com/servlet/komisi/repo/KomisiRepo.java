package com.servlet.komisi.repo;

import com.servlet.komisi.entity.Komisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("KomisiRepo")
public interface KomisiRepo extends JpaRepository<Komisi, Long> {
}
