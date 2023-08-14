package com.servlet.partai.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.partai.entity.Partai;

@Repository("PartaiRepo")
public interface PartaiRepo extends JpaRepository<Partai, Long>{

}
