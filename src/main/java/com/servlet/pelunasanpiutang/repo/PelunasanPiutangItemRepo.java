package com.servlet.pelunasanpiutang.repo;

import com.servlet.pelunasanpiutang.entity.PelunasanPiutangItem;
import com.servlet.pelunasanpiutang.entity.PelunasanPiutangItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("PelunasanPiutangItemRepo")
public interface PelunasanPiutangItemRepo extends JpaRepository<PelunasanPiutangItem, PelunasanPiutangItemPK> {

    @Transactional
    @Modifying
    @Query(value ="delete from pelunasanpiutang_item where idpelunasanpiutang = :idpelunasanpiutang ",nativeQuery = true)
    void deleteAllDetailByIdPelunasanPiutang(@Param("idpelunasanpiutang") long idpelunasanpiutang);
}
