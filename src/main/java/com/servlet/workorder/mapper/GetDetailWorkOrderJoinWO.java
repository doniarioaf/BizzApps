package com.servlet.workorder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.workorder.entity.DetailWorkOrderJoinWO;

public class GetDetailWorkOrderJoinWO implements RowMapper<DetailWorkOrderJoinWO>{
	private String schemaSql;
	
	public GetDetailWorkOrderJoinWO() {
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
	public DetailWorkOrderJoinWO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long idworkorder = rs.getLong("idworkorder");
		final String nodocument = rs.getString("nodocument");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
		//
		
//		private Long id;
//		private String nodocument;
//		private Timestamp tanggal;
//		private Long idwarehouse;
//		private String warehousename;
//		private Long idpartai;
//		private String partainame;
		return null;
	}

}
