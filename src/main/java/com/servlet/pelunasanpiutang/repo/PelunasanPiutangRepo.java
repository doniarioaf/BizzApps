package com.servlet.pelunasanpiutang.repo;

import com.servlet.pelunasanpiutang.entity.PelunasanPiutang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PelunasanPiutangRepo")
public interface PelunasanPiutangRepo extends JpaRepository<PelunasanPiutang, Long> {
}
