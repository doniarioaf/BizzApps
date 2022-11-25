package com.servlet.parametermanggala.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.parameter.service.ParameterService;
import com.servlet.parametermanggala.entity.BodyParameterManggala;
import com.servlet.parametermanggala.entity.ParameterManggala;
import com.servlet.parametermanggala.entity.ParameterManggalaData;
import com.servlet.parametermanggala.entity.ParameterManggalaTemplate;
import com.servlet.parametermanggala.entity.ValueParameter;
import com.servlet.parametermanggala.mapper.GetParameterManggalaData;
import com.servlet.parametermanggala.repo.ParameterManggalaRepo;
import com.servlet.parametermanggala.service.ParameterManggalaService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class ParameterManggalaHandler implements ParameterManggalaService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ParameterManggalaRepo repository;
	@Autowired
	private ParameterService parameterService;
	
	@Override
	public List<ParameterManggalaData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetParameterManggalaData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetParameterManggalaData(), queryParameters);
	}

	@Override
	public List<ParameterManggalaData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetParameterManggalaData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetParameterManggalaData(), queryParameters);
	}

	@Override
	public ParameterManggalaData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetParameterManggalaData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<ParameterManggalaData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetParameterManggalaData(), queryParameters);
		if(list != null && list.size() > 0) {
			ParameterManggalaData val = list.get(0);
			val.setTemplate(setTemplate(idcompany, idbranch));
			return val;
		}
		return null;
	}

	@Override
	public ParameterManggalaData getByParamName(Long idcompany, Long idbranch, String paramName) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetParameterManggalaData().schema());
		sqlBuilder.append(" where data.paramname = ? and data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {paramName,idcompany,idbranch};
		List<ParameterManggalaData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetParameterManggalaData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ReturnData saveParameterManggala(Long idcompany, Long idbranch, Long iduser, BodyParameterManggala body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		
		ParameterManggalaData checkParamName = checkByParamName(idcompany, idbranch, body.getParamname().toUpperCase().replaceAll(" ", ""));
		if(checkParamName != null) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PARAMETERMANGGALA_PARAMNAME_EXIST,"Nama Parameter Sudah Ada");
			validations.add(msg);
		}
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				ParameterManggala table = new ParameterManggala();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setParamname(body.getParamname().toUpperCase().replaceAll(" ", ""));
				table.setParamvalue(body.getParamvalue());
				table.setParamtype(body.getParamtype());
				table.setParamdate(body.getParamdate() != null?new java.sql.Date(body.getParamdate()):null);
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
	public ReturnData updateParameterManggala(Long idcompany, Long idbranch, Long iduser, Long id,
			BodyParameterManggala body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		ParameterManggalaData value = getById(idcompany, idbranch, id);
		if(value != null) {
			if(!value.getParamname().equalsIgnoreCase(body.getParamname())) {
				ParameterManggalaData checkParamName = checkByParamName(idcompany, idbranch, body.getParamname().toUpperCase().replaceAll(" ", ""));
				if(checkParamName != null) {
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PARAMETERMANGGALA_PARAMNAME_EXIST,"Nama Parameter Sudah Ada");
					validations.add(msg);
				}
			}
			
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					ParameterManggala table = repository.getById(id);
					table.setIdcompany(idcompany);
					table.setIdbranch(idbranch);
					//Parameter name tidak bisa di ubah
//					table.setParamname(body.getParamname());
					table.setParamvalue(body.getParamvalue());
					table.setIsactive(body.isIsactive());
					table.setParamtype(body.getParamtype());
					table.setParamdate(body.getParamdate() != null?new java.sql.Date(body.getParamdate()):null);
					table.setIsdelete(false);
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
	public ReturnData deleteParameterManggala(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		ParameterManggalaData value = getById(idcompany, idbranch, id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			ParameterManggala table = repository.getById(id);
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
	
	public ParameterManggalaData checkByParamName(Long idcompany, Long idbranch, String paramName) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetParameterManggalaData().schema());
		sqlBuilder.append(" where data.paramname = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {paramName,idcompany,idbranch};
		List<ParameterManggalaData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetParameterManggalaData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	private ParameterManggalaTemplate setTemplate(long idcompany, long idbranch) {
		ParameterManggalaTemplate template = new ParameterManggalaTemplate();
		template.setParameterTypeOptions(parameterService.getListParameterByGrup("PARAMETER_TYPE"));
		return template;
		
	}

	@Override
	public ParameterManggalaTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return setTemplate(idcompany,idbranch);
	}

	@Override
	public ValueParameter getValueByParamName(Long idcompany, Long idbranch, String paramName, String type) {
		// TODO Auto-generated method stub
		ParameterManggalaData val = getByParamName(idcompany, idbranch, paramName);
		ValueParameter value = new ValueParameter();
		if(val != null) {
			if(type.equals("TEXT") && val.getParamtype().equals("TEXT")) {
				value.setDateValue(null);
				value.setDoubleValue(null);
				value.setStrValue(val.getParamvalue());
			}else if(type.equals("NUMBER") && val.getParamtype().equals("NUMBER")) {
				value.setDateValue(null);
				value.setDoubleValue(new Double(val.getParamvalue()));
				value.setStrValue("");
			}else if(type.equals("DATE") && val.getParamtype().equals("DATE")) {
				value.setDateValue(val.getParamdate());
				value.setDoubleValue(null);
				value.setStrValue("");
			}else {
				value.setDateValue(null);
				value.setDoubleValue(null);
				value.setStrValue("");
			}
		}else {
			value.setDateValue(null);
			value.setDoubleValue(null);
			value.setStrValue("");
		}
		
		return value;
	}

}
