package com.servlet.parameter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.parameter.entity.Parameter;

@Repository("ParameterRepo")
public interface ParameterRepo extends JpaRepository<Parameter, String>{

}
