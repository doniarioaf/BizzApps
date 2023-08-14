package com.servlet.runningnumber.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.runningnumber.entity.RunningNumber;
import com.servlet.runningnumber.entity.RunningNumberPK;

@Repository("RunningNumberRepo")
public interface RunningNumberRepo extends JpaRepository<RunningNumber, RunningNumberPK>{

}
