package com.servlet.customermanggala.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoGudangData;

public class GetDataDetailCustomerManggalaInfoGudang implements RowMapper<DetailCustomerManggalaInfoGudangData>{
	private String schemaSql;
	
	public GetDataDetailCustomerManggalaInfoGudang() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idwarehouse as idwarehouse, ");
		sqlBuilder.append("warehouse.nama as nama, warehouse.alamat as alamat, warehouse.ancerancer as ancerancer, ");
		sqlBuilder.append("warehouse.contactnumber as contactnumber, warehouse.contacthp as contacthp, warehouse.note as note, ");
		sqlBuilder.append("prov.prov_name as prov_name, city.city_name as city_name, districts.dis_name as dis_name ");
		sqlBuilder.append("from detail_customer_manggala_info_gudang as data ");
		sqlBuilder.append("left join m_warehouse as warehouse on warehouse.id = data.idwarehouse ");
		sqlBuilder.append("left join provinces as prov on cast(prov.prov_id as text) = warehouse.province ");
		sqlBuilder.append("left join cities as city on cast(city.city_id as text) = warehouse.city ");
		sqlBuilder.append("left join districts as districts on cast(districts.dis_id as text) = warehouse.kecamatan ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public DetailCustomerManggalaInfoGudangData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long idwarehouse = rs.getLong("idwarehouse");
		final String nama = rs.getString("nama");
		final String alamat = rs.getString("alamat");
		final String ancerancer = rs.getString("ancerancer");
		final String contactnumber = rs.getString("contactnumber");
		final String contacthp = rs.getString("contacthp");
		final String note = rs.getString("note");
		final String prov_name = rs.getString("prov_name");
		final String city_name = rs.getString("city_name");
		final String dis_name = rs.getString("dis_name");
		
		
		DetailCustomerManggalaInfoGudangData data = new DetailCustomerManggalaInfoGudangData();
		data.setId(idwarehouse);
		data.setNamagudang(nama);
		data.setAreakirim(dis_name);
		data.setAlamatgudang(alamat);
		data.setAncerancer(ancerancer);
		data.setKontakgudang(contactnumber);
		data.setHpkontakgudang(contacthp);
		data.setNote(note);
		data.setProvince(prov_name);
		data.setKota(city_name);
		return data;
	}

}
