package com.servlet.mobile.monitorusermobileinfo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.mobile.monitorusermobileinfo.entity.DetailInfo;

public class GetListInfoByInfoHeader implements RowMapper<DetailInfo>{
	private String schemaSql;
	
	public GetListInfoByInfoHeader(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("distinct infoheader.question as question,infodetail.answer as answer,minfo.infoanswer as infoanswer from m_monitor_user_mobile_info as minfo ");
		sqlBuilder.append("join m_info_header_detail infodetail on infodetail.id = minfo.idinfodetail ");
		sqlBuilder.append("join m_info_header infoheader on infoheader.id = minfo.infoid ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public DetailInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final String question = rs.getString("question");
		final String answer = rs.getString("answer");
		final String infoanswer = rs.getString("infoanswer");
		DetailInfo data = new DetailInfo();
		data.setQuestion(question);
		data.setAnswer(answer);
		data.setInfoAnswer(infoanswer);
		return data;
	}

}
