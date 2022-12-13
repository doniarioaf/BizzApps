package com.servlet.pengluarankasbank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.pengluarankasbank.entity.PengluaranKasBank;

@Repository("PengeluaranKasBankRepo")
public interface PengeluaranKasBankRepo extends JpaRepository<PengluaranKasBank, Long>{

}
