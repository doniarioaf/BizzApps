package com.servlet.asset.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.asset.entity.AssetMappingData;

public class GetAssetMapping implements RowMapper<AssetMappingData>{
	
	private String schemaSql;
	
	public GetAssetMapping() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.idasset as idasset, data.idasset_mapping as idasset_mapping, data.type as type ");
		sqlBuilder.append("from m_asset_mapping as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public AssetMappingData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idasset = rs.getLong("idasset");
		final Long idasset_mapping = rs.getLong("idasset_mapping");
		final String type = rs.getString("type");
		
		AssetMappingData data = new AssetMappingData();
		data.setId(id);
		data.setIdasset(idasset);
		data.setIdasset_mapping(idasset_mapping);
		data.setType(type);
		return data;
	}

}
