package com.servlet.workorder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.workorder.entity.DetailWorkOrderData;

public class GetDetailWorkOrderJoinTable implements RowMapper<DetailWorkOrderData>{
	private String schemaSql;
	
	public GetDetailWorkOrderJoinTable() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idworkorder as idworkorder, data.idpartai as idpartai, data.jumlahkoli as jumlahkoli, data.jumlahkg as jumlahkg,data.nocontainer as nocontainer, data.noseal as noseal, ");
		sqlBuilder.append("data.barang as barang, partai.name as partainame ");
		sqlBuilder.append("from detail_work_order as data ");
		sqlBuilder.append("left join m_partai as partai on partai.id = data.idpartai ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public DetailWorkOrderData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long idworkorder = rs.getLong("idworkorder");
		final Long idpartai = rs.getLong("idpartai");
		final String jumlahkoli = rs.getString("jumlahkoli");
		final String jumlahkg = rs.getString("jumlahkg");
		final String nocontainer = rs.getString("nocontainer");
		final String noseal = rs.getString("noseal");
		final String barang = rs.getString("barang");
		final String partainame = rs.getString("partainame");
		
		DetailWorkOrderData data = new DetailWorkOrderData();
		data.setIdworkorder(idworkorder);
		data.setIdpartai(idpartai);
		data.setJumlahkoli(jumlahkoli);
		data.setJumlahkg(jumlahkg);
		data.setNocontainer(nocontainer);
		data.setNoseal(noseal);
		data.setBarang(barang);
		data.setPartainame(partainame);
		
		return data;
	}

}
