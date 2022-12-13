package com.servlet.pengluarankasbank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBank;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankPK;

@Repository("DetailPengeluaranKasBankRepo")
public interface DetailPengeluaranKasBankRepo extends JpaRepository<DetailPengeluaranKasBank, DetailPengeluaranKasBankPK>{
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_pengeluaran_kas_bank where idpengeluarankasbank = :idpengeluarankasbank and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllDetail(@Param("idpengeluarankasbank") long idpengeluarankasbank,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
