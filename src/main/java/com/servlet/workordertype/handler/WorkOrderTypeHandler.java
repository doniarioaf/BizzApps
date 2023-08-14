package com.servlet.workordertype.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.workordertype.entity.BodyWorkOrderType;
import com.servlet.workordertype.entity.WorkOrderType;
import com.servlet.workordertype.entity.WorkOrderTypeData;
import com.servlet.workordertype.mapper.GetWorkOrderTypeData;
import com.servlet.workordertype.repo.WorkOrderTypeRepo;
import com.servlet.workordertype.service.WorkOrderTypeService;

@Service
public class WorkOrderTypeHandler implements WorkOrderTypeService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private WorkOrderTypeRepo repository;
	
	@Override
	public List<WorkOrderTypeData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderTypeData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderTypeData(), queryParameters);
	}

	@Override
	public List<WorkOrderTypeData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderTypeData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderTypeData(), queryParameters);
	}

	@Override
	public WorkOrderTypeData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderTypeData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<WorkOrderTypeData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderTypeData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ReturnData saveWorkOrderType(Long idcompany, Long idbranch, Long iduser, BodyWorkOrderType body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		WorkOrderTypeData checkCode = checkByCode(idcompany,idbranch,body.getCode());
		if(checkCode != null && !body.getCode().equals("")) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_WORKORDERTYPE_CODE_EXIST,"Code Sudah Ada");
			validations.add(msg);
		}
		
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				WorkOrderType table = new WorkOrderType();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setName(body.getName());
				table.setCode(body.getCode());
				table.setNote(body.getNote());
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				
				idsave = repository.saveAndFlush(table).getId();
			}catch (Exception e) {
				// TODO: handle exception
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
				validations.add(msg);
				e.printStackTrace();
			}
		}
		
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData updateWorkOrderType(Long idcompany, Long idbranch, Long iduser, Long id, BodyWorkOrderType body) {
		// TODO Auto-generated method stub
		
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		WorkOrderTypeData value = getById(idcompany, idbranch, id);
		if(value != null) {
			if(!value.getCode().equals(body.getCode()) && !body.getCode().equals("") ) {
				WorkOrderTypeData checkCode = checkByCode(idcompany,idbranch,body.getCode());
				if(checkCode != null) {
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_WORKORDERTYPE_CODE_EXIST,"Code Sudah Ada");
					validations.add(msg);
				}
			}
			
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					WorkOrderType table = repository.getById(id);
					table.setName(body.getName());
					table.setCode(body.getCode());
					table.setNote(body.getNote());
					table.setIsactive(body.isIsactive());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
				}catch (Exception e) {
					// TODO: handle exception
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
					validations.add(msg);
					e.printStackTrace();
				}
				
			}
			
		}
		
		
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData deleteWorkOrderType(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		WorkOrderTypeData value = getById(idcompany, idbranch, id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			WorkOrderType table = repository.getById(id);
			table.setIsdelete(true);
			table.setDeleteby(iduser.toString());
			table.setDeletedate(ts);
			idsave = repository.saveAndFlush(table).getId();
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}
	
	private WorkOrderTypeData checkByCode(Long idcompany, Long idbranch, String code) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderTypeData().schema());
		sqlBuilder.append(" where data.code = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {code,idcompany,idbranch};
		List<WorkOrderTypeData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderTypeData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
