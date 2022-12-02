package com.servlet.asset.service;

import java.util.List;

import com.servlet.asset.entity.AssetData;
import com.servlet.asset.entity.AssetTemplate;
import com.servlet.asset.entity.BodyAsset;
import com.servlet.asset.entity.BodyAssetMapping;
import com.servlet.asset.entity.HistoryAssetMappingData;
import com.servlet.shared.ReturnData;

public interface AssetService {
	List<AssetData> getListAll(Long idcompany,Long idbranch);
	List<AssetData> getListActive(Long idcompany,Long idbranch);
	AssetData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveAsset(Long idcompany,Long idbranch,Long iduser,BodyAsset body);
	ReturnData updateAsset(Long idcompany,Long idbranch,Long iduser,Long id,BodyAsset body);
	ReturnData deleteAsset(Long idcompany,Long idbranch,Long iduser,Long id);
	AssetTemplate getTemplate(Long idcompany,Long idbranch);
	ReturnData saveAssetMapping(Long idcompany,Long idbranch,Long iduser,BodyAssetMapping body);
	ReturnData deleteHistoryAssetMapping(Long idcompany,Long idbranch,Long id);
	List<AssetData> getListAssetForPenandaanSuratJalan(Long idcompany,Long idbranch,Long idsuratjalan);
	List<AssetData> getListAssetForMapping(Long idcompany,Long idbranch,Long id,String jenisasset);
	List<HistoryAssetMappingData> getListHistoryMapping(Long idcompany,Long idbranch, Long id);
}
