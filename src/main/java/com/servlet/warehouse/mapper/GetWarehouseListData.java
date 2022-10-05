package com.servlet.warehouse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.warehouse.entity.WarehouseData;

public class GetWarehouseListData implements RowMapper<WarehouseData>{
	private String schemaSql;
	
	public GetWarehouseListData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.province as province, data.city as city, data.kecamatan as kecamatan,data.nama as nama, data.isactive as isactive, ");
		sqlBuilder.append("data.alamat as alamat, data.ancerancer as ancerancer, data.contactnumber as contactnumber, data.contacthp as contacthp,data.note as note, ");
		sqlBuilder.append("data.idcustomer as idcustomer ");
		sqlBuilder.append("from m_warehouse as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public WarehouseData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String province = rs.getString("province");
		final String city = rs.getString("city");
		final String kecamatan = rs.getString("kecamatan");
		final String nama = rs.getString("nama");
		final boolean isactive = rs.getBoolean("isactive");
		final String alamat = rs.getString("alamat");
		final String ancerancer = rs.getString("ancerancer");
		final String contactnumber = rs.getString("contactnumber");
		final String contacthp = rs.getString("contacthp");
		final String note = rs.getString("note");
		final Long idcustomer = rs.getLong("idcustomer");
		
		WarehouseData data = new WarehouseData();
		data.setId(id);
		data.setProvince(province);
		data.setProvincename("");
		data.setCity(city);
		data.setCityname("");
		data.setKecamatan(kecamatan);
		data.setKecamatanname("");
		data.setNama(nama);
		data.setAlamat(alamat);
		data.setAncerancer(ancerancer);
		data.setContactnumber(contactnumber);
		data.setContacthp(contacthp);
		data.setNote(note);
		data.setIdcustomer(idcustomer);
		data.setCustomername("");
		data.setIsactive(isactive);
		return data;
	}

}
