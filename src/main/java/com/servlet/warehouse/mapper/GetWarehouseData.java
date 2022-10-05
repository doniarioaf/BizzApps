package com.servlet.warehouse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.warehouse.entity.WarehouseData;

public class GetWarehouseData implements RowMapper<WarehouseData>{
	private String schemaSql;
	
	public GetWarehouseData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.province as province, data.city as city, data.kecamatan as kecamatan,data.nama as nama, data.isactive as isactive, ");
		sqlBuilder.append("data.alamat as alamat, data.ancerancer as ancerancer, data.contactnumber as contactnumber, data.contacthp as contacthp,data.note as note, ");
		sqlBuilder.append("data.idcustomer as idcustomer, prov.prov_name as provincename, city.city_name as cityname, districts.dis_name as kecamatanname, cust.customername as customername ");
		sqlBuilder.append("from m_warehouse as data ");
		sqlBuilder.append("left join provinces as prov on cast(prov.prov_id as text) = data.province ");
		sqlBuilder.append("left join cities as city on cast(city.city_id as text) = data.city ");
		sqlBuilder.append("left join districts as districts on cast(districts.dis_id as text) = data.kecamatan ");
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = data.idcustomer ");
		
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
		final String provincename = rs.getString("provincename");
		final String cityname = rs.getString("cityname");
		final String kecamatanname = rs.getString("kecamatanname");
		final String customername = rs.getString("customername");
		
		WarehouseData data = new WarehouseData();
		data.setId(id);
		data.setProvince(province);
		data.setProvincename(provincename);
		data.setCity(city);
		data.setCityname(cityname);
		data.setKecamatan(kecamatan);
		data.setKecamatanname(kecamatanname);
		data.setNama(nama);
		data.setAlamat(alamat);
		data.setAncerancer(ancerancer);
		data.setContactnumber(contactnumber);
		data.setContacthp(contacthp);
		data.setNote(note);
		data.setIdcustomer(idcustomer);
		data.setCustomername(customername);
		data.setIsactive(isactive);
		return data;
	}

}
