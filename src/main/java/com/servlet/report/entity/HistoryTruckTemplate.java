package com.servlet.report.entity;

import java.util.List;

import com.servlet.asset.entity.AssetData;

public class HistoryTruckTemplate {
	private List<AssetData> assetKepalaOptions;
	private List<AssetData> sparepartAssetOptions;
	public List<AssetData> getAssetKepalaOptions() {
		return assetKepalaOptions;
	}
	public void setAssetKepalaOptions(List<AssetData> assetKepalaOptions) {
		this.assetKepalaOptions = assetKepalaOptions;
	}
	public List<AssetData> getSparepartAssetOptions() {
		return sparepartAssetOptions;
	}
	public void setSparepartAssetOptions(List<AssetData> sparepartAssetOptions) {
		this.sparepartAssetOptions = sparepartAssetOptions;
	}
}
