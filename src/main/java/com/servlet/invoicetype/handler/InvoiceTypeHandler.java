package com.servlet.invoicetype.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.invoicetype.entity.BodyInvoiceType;
import com.servlet.invoicetype.entity.InvoiceType;
import com.servlet.invoicetype.entity.InvoiceTypeData;
import com.servlet.invoicetype.entity.InvoiceTypeTemplate;
import com.servlet.invoicetype.entity.ParamInvTypeDropDown;
import com.servlet.invoicetype.mapper.GetInvoiceTypeData;
import com.servlet.invoicetype.repo.InvoiceTypeRepo;
import com.servlet.invoicetype.service.InvoiceTypeService;
import com.servlet.parameter.service.ParameterService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class InvoiceTypeHandler implements InvoiceTypeService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private InvoiceTypeRepo repository;
	@Autowired
	private ParameterService parameterService;
	
	@Override
	public List<InvoiceTypeData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceTypeData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceTypeData(), queryParameters);
	}

	@Override
	public List<InvoiceTypeData> getListActiveBankAccount(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceTypeData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceTypeData(), queryParameters);
	}

	@Override
	public InvoiceTypeData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceTypeData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<InvoiceTypeData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceTypeData(), queryParameters);
		if(list != null && list.size() > 0) {
			InvoiceTypeData val = list.get(0);
			val.setTemplate(getTemplate(idcompany, idbranch));
			return val;
		}
		return null;
	}

	@Override
	public ReturnData saveInvoiceType(Long idcompany, Long idbranch, Long iduser, BodyInvoiceType body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		InvoiceTypeData checkName = getByName(idcompany, idbranch, body.getNama());
		if(checkName != null) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_INVOICETYPE_NAME_EXISTS,"Nama Sudah Dipakai");
			validations.add(msg);
		}
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				InvoiceType table = new InvoiceType();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setInvoicetype(body.getInvoicetype());
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
	public ReturnData updateInvoiceType(Long idcompany, Long idbranch, Long iduser, Long id, BodyInvoiceType body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		
		InvoiceTypeData value = getById(idcompany,idbranch,id);
		if(value != null) {
			if(!value.getNama().toLowerCase().equals(body.getNama().toLowerCase())) {
				InvoiceTypeData checkName = getByName(idcompany, idbranch, body.getNama());
				if(checkName != null) {
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_INVOICETYPE_NAME_EXISTS,"Nama Sudah Dipakai");
					validations.add(msg);
				}
			}
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					InvoiceType table = repository.getById(id);
					table.setInvoicetype(body.getInvoicetype());
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
	public ReturnData deleteInvoiceType(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		InvoiceTypeData value = getById(idcompany,idbranch,id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			InvoiceType table = repository.getById(id);
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
	
	private InvoiceTypeData getByName(Long idcompany, Long idbranch, String nama) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceTypeData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false and lower(data.nama) = '"+nama.toLowerCase()+"' ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<InvoiceTypeData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceTypeData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public InvoiceTypeTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return getDataTemplate(idcompany,idbranch);
	}
	
	private InvoiceTypeTemplate getDataTemplate(long idcompany, long idbranch) {
		InvoiceTypeTemplate data = new InvoiceTypeTemplate();
		data.setInvoiceTypeOptions(parameterService.getListParameterByGrup("INVOICETYPE"));
		return data;
	}

	@Override
	public List<InvoiceTypeData> getListAllByInvoiceType(Long idcompany, Long idbranch, String invoicetype) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceTypeData().schema());
		sqlBuilder.append(" where data.invoicetype = ? and data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {invoicetype,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceTypeData(), queryParameters);
	}

	@Override
	public List<InvoiceTypeData> getListDropDownInvoiceType(Long idcompany, Long idbranch,ParamInvTypeDropDown param) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceTypeData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		if(param.getMenu().equals("PENGELUARAN_KAS_BANK")) {
			sqlBuilder.append(" and data.invoicetype = '"+param.getInvoicetype()+"' ");
		}
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceTypeData(), queryParameters);
	}

}
