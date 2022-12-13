package com.servlet.asset.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.asset.entity.AssetMapping;

@Repository("AssetMappingRepo")
public interface AssetMappingRepo extends JpaRepository<AssetMapping, Long>{

}
