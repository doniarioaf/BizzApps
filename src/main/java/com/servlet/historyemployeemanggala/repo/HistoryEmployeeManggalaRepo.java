package com.servlet.historyemployeemanggala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.historyemployeemanggala.entity.HistoryEmployeeManggala;

@Repository("HistoryEmployeeManggalaRepo")
public interface HistoryEmployeeManggalaRepo extends JpaRepository<HistoryEmployeeManggala, Long>{

}
