package com.servlet.penerimaankasbank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankPK;

@Repository("DetailPenerimaanKasBankRepo")
public interface DetailPenerimaanKasBankRepo extends JpaRepository<DetailPenerimaanKasBank, DetailPenerimaanKasBankPK>{
	
	@Transactional
	@Modifying
	@Query(value ="delete from detail_penerimaan_kas_bank where idpenerimaankasbank = :idpenerimaankasbank and idcompany = :idcompany and idbranch = :idbranch ",nativeQuery = true)
	void deleteAllDetail(@Param("idpenerimaankasbank") long idpenerimaankasbank,@Param("idcompany") long idcompany,@Param("idbranch") long idbranch);
}
