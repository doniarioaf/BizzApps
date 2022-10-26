package com.servlet.suratjalan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.suratjalan.entity.HistorySuratJalan;

@Repository("HistorySuratJalanRepo")
public interface HistorySuratJalanRepo extends JpaRepository<HistorySuratJalan, Long>{

}
