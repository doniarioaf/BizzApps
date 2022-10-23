package com.servlet.suratjalan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.suratjalan.entity.SuratJalan;

@Repository("SuratJalanRepo")
public interface SuratJalanRepo extends JpaRepository<SuratJalan, Long>{

}
