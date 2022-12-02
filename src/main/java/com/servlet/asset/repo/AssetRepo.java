package com.servlet.asset.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.asset.entity.Asset;

@Repository("AssetRepo")
public interface AssetRepo extends JpaRepository<Asset, Long>{

}
