package com.servlet.penerimaankasbank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.penerimaankasbank.entity.PenerimaanKasBank;

@Repository("PenerimaanKasBankRepo")
public interface PenerimaanKasBankRepo extends JpaRepository<PenerimaanKasBank, Long>{
}
