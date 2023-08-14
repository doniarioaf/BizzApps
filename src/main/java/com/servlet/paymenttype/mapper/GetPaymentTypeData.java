package com.servlet.paymenttype.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.paymenttype.entity.PaymentTypeData;

public class GetPaymentTypeData implements RowMapper<PaymentTypeData>{
	
	private String schemaSql;
	
	public GetPaymentTypeData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.paymenttype as paymenttype, data.nama as nama, data.isactive as isactive, param.codename as codename, coa.nama as coaname, data.idcoa as idcoa ");
		sqlBuilder.append("from m_payment_type as data ");
		sqlBuilder.append("left join m_parameter as param on param.code = data.paymenttype and param.grup = 'PAYMENTITEM_TYPE' ");
		sqlBuilder.append("left join m_coa as coa on coa.id = data.idcoa ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public PaymentTypeData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String paymenttype = rs.getString("paymenttype");
		final String codename = rs.getString("codename");
		final String nama = rs.getString("nama");
		final boolean isactive = rs.getBoolean("isactive");
		final String coaname = rs.getString("coaname");
		final Long idcoa = rs.getLong("idcoa");
		
		PaymentTypeData data = new PaymentTypeData();
		data.setId(id);
		data.setPaymenttype(paymenttype);
		data.setPaymenttypename(codename);
		data.setNama(nama);
		data.setIsactive(isactive);
		data.setCoaName(coaname);
		data.setIdcoa(idcoa);
		return data;
	}

}
