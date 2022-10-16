package com.servlet.paymenttype.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.parameter.service.ParameterService;
import com.servlet.paymenttype.entity.BodyPaymentType;
import com.servlet.paymenttype.entity.PaymentType;
import com.servlet.paymenttype.entity.PaymentTypeData;
import com.servlet.paymenttype.entity.PaymentTypeTemplate;
import com.servlet.paymenttype.mapper.GetPaymentTypeData;
import com.servlet.paymenttype.repo.PaymentTypeRepo;
import com.servlet.paymenttype.service.PaymentTypeService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class PaymentTypeHandler implements PaymentTypeService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PaymentTypeRepo repository;
	@Autowired
	private ParameterService parameterService;
	
	@Override
	public List<PaymentTypeData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPaymentTypeData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPaymentTypeData(), queryParameters);
	}

	@Override
	public List<PaymentTypeData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPaymentTypeData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPaymentTypeData(), queryParameters);
	}

	@Override
	public PaymentTypeData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPaymentTypeData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PaymentTypeData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPaymentTypeData(), queryParameters);
		if(list != null && list.size() > 0) {
			PaymentTypeData val = list.get(0);
			val.setTemplate(getTemplate(idcompany, idbranch));
			return val;
		}
		return null;
	}

	@Override
	public ReturnData savePaymentType(Long idcompany, Long idbranch, Long iduser, BodyPaymentType body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		PaymentTypeData checkName = getByName(idcompany, idbranch, body.getNama());
		if(checkName != null) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PAYMENTTYPE_NAME_EXISTS,"Nama Sudah Dipakai");
			validations.add(msg);
		}
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				PaymentType table = new PaymentType();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setPaymenttype(body.getPaymenttype());
				table.setNama(body.getNama());
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				idsave = repository.saveAndFlush(table).getId();
			}catch (Exception e) {
				// TODO: handle exception
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
				validations.add(msg);
			}
			
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData updatePaymentType(Long idcompany, Long idbranch, Long iduser, Long id, BodyPaymentType body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		
		PaymentTypeData value = getById(idcompany,idbranch,id);
		if(value != null) {
			if(!value.getNama().toLowerCase().equals(body.getNama().toLowerCase())) {
				PaymentTypeData checkName = getByName(idcompany, idbranch, body.getNama());
				if(checkName != null) {
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PAYMENTTYPE_NAME_EXISTS,"Nama Sudah Dipakai");
					validations.add(msg);
				}
			}
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					PaymentType table = repository.getById(id);
					table.setPaymenttype(body.getPaymenttype());
					table.setNama(body.getNama());
					table.setIsactive(body.isIsactive());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
				}catch (Exception e) {
					// TODO: handle exception
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
					validations.add(msg);
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
	public ReturnData deletePaymentType(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		PaymentTypeData value = getById(idcompany,idbranch,id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			PaymentType table = repository.getById(id);
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

	@Override
	public PaymentTypeTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return getDataTemplate(idcompany, idbranch);
	}

	@Override
	public List<PaymentTypeData> getListAllByPaymentType(Long idcompany, Long idbranch, String paymenttype) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private PaymentTypeTemplate getDataTemplate(long idcompany, long idbranch) {
		PaymentTypeTemplate data = new PaymentTypeTemplate();
		data.setPaymentTypeOptions(parameterService.getListParameterByGrup("PAYMENTITEM_TYPE"));
		return data;
	}
	
	private PaymentTypeData getByName(Long idcompany, Long idbranch, String nama) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPaymentTypeData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false and lower(data.nama) = '"+nama.toLowerCase()+"' ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<PaymentTypeData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPaymentTypeData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
