package com.servlet.vendor.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.vendor.entity.VendorData;

public class GetVendorData implements RowMapper<VendorData>{
	
	private String schemaSql;
	
	public GetVendorData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.idvendorcategory as idvendorcategory, data.code as code, ");
		sqlBuilder.append("data.jenisbadanusaha as jenisbadanusaha, data.nama as nama, data.alias as alias, data.npwp as npwp, ");
		sqlBuilder.append("data.address as address, data.provinsi as provinsi, data.kota as kota, data.kodepos as kodepos, data.isactive as isactive, data.district as district, data.alamat2 as alamat2, data.alamat3 as alamat3 ");
		sqlBuilder.append("from m_vendor as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public VendorData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idvendorcategory = rs.getLong("idvendorcategory");
		final String code = rs.getString("code");
		final String jenisbadanusaha = rs.getString("jenisbadanusaha");
		final String nama = rs.getString("nama");
		final String alias = rs.getString("alias");
		final String npwp = rs.getString("npwp");
		final String address = rs.getString("address");
		final String provinsi = rs.getString("provinsi");
		final String kota = rs.getString("kota");
		final String kodepos = rs.getString("kodepos");
		final boolean isactive = rs.getBoolean("isactive");
		final Long district = rs.getLong("district");
		final String alamat2 = rs.getString("alamat2");
		final String alamat3 = rs.getString("alamat3");
		
		
		VendorData data = new VendorData();
		data.setId(id);
		data.setIdvendorcategory(idvendorcategory);
		data.setCode(code);
		data.setJenisbadanusaha(jenisbadanusaha);
		data.setNama(nama);
		data.setAlias(alias);
		data.setNpwp(npwp);
		data.setAddress(address);
		data.setProvinsi(provinsi);
		data.setKota(kota);
		data.setKodepos(kodepos);
		data.setIsactive(isactive);
		data.setDistrict(district);
		data.setAlamat2(alamat2);
		data.setAlamat3(alamat3);
		return data;
	}

}
