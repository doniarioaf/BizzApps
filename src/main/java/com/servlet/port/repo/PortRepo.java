package com.servlet.port.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.port.entity.Port;

@Repository("PortRepo")
public interface PortRepo extends JpaRepository<Port, Long>{

}
