package com.servlet.parameterclient.repo;

import com.servlet.parameterclient.entity.ParameterClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ParameterClientRepo")
public interface ParameterClientRepo extends JpaRepository<ParameterClient, Long>{

}
