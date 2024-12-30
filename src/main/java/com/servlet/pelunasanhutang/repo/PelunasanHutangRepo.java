package com.servlet.pelunasanhutang.repo;

import com.servlet.pelunasanhutang.entity.PelunasanHutang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PelunasanHutangRepo")
public interface PelunasanHutangRepo extends JpaRepository<PelunasanHutang, Long> {
}
