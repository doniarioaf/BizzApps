package com.servlet.asset.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.asset.entity.HistoryAssetMapping;

@Repository("HistoryAssetMappingRepo")
public interface HistoryAssetMappingRepo extends JpaRepository<HistoryAssetMapping, Long>{

}
