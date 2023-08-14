package com.servlet.mapping.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.mapping.entity.BodyMapping;
import com.servlet.mapping.entity.BodyMappingSingle;
import com.servlet.mapping.entity.Mapping;
import com.servlet.mapping.entity.MappingData;
import com.servlet.mapping.mapper.GetMappingData;
import com.servlet.mapping.repo.MappingRepo;
import com.servlet.mapping.service.MappingService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class MappingHandler implements MappingService{
	@Autowired
	private MappingRepo repository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public ReturnData saveMapping(Long idcompany, Long idbranch, Long iduser,BodyMapping body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		try {
			Timestamp ts = new Timestamp(new Date().getTime());
			
			if(body.getMappings() != null) {
				if(body.getMappings().length > 0) {
					for(int i=0; i < body.getMappings().length; i++) {
						BodyMappingSingle det = body.getMappings()[i]; 
						Mapping table = repository.getById(det.getId());
						table.setIdmaster(det.getIdmaster());
//						table.setIsdelete(false);
						table.setCreatedby(iduser.toString());
						table.setCreateddate(ts);
						table.setUpdateby(iduser.toString());
						table.setUpdatedate(ts);
						idsave = repository.saveAndFlush(table).getId();
					}
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
			validations.add(msg);
			e.printStackTrace();
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}
	@Override
	public List<MappingData> getListMapping(long idcompany, long idbranch, String mappinggrup) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetMappingData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.mappinggrup = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch,mappinggrup};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetMappingData(), queryParameters);
	}
}
