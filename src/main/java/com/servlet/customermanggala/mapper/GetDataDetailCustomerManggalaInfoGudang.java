package com.servlet.customermanggala.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoContactData;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoGudangData;

public class GetDataDetailCustomerManggalaInfoGudang implements RowMapper<DetailCustomerManggalaInfoGudangData>{
	private String schemaSql;
	
	public GetDataDetailCustomerManggalaInfoGudang() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.namagudang as namagudang, data.areakirim as areakirim, data.alamatgudang as alamatgudang, data.ancerancer as ancerancer, data.kontakgudang as kontakgudang, data.hpkontakgudang as hpkontakgudang, data.note as note ");
		sqlBuilder.append("from detail_customer_manggala_info_gudang as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public DetailCustomerManggalaInfoGudangData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final String namagudang = rs.getString("namagudang");
		final String areakirim = rs.getString("areakirim");
		final String alamatgudang = rs.getString("alamatgudang");
		final String ancerancer = rs.getString("ancerancer");
		final String hpkontakgudang = rs.getString("hpkontakgudang");
		final String kontakgudang = rs.getString("kontakgudang");
		final String note = rs.getString("note");
		
		DetailCustomerManggalaInfoGudangData data = new DetailCustomerManggalaInfoGudangData();
		data.setNamagudang(namagudang);
		data.setAreakirim(areakirim);
		data.setAlamatgudang(alamatgudang);
		data.setAncerancer(ancerancer);
		data.setHpkontakgudang(hpkontakgudang);
		data.setKontakgudang(kontakgudang);
		data.setNote(note);
		return data;
	}

}
