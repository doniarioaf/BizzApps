package com.servlet.bankaccount.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.usermobile.entity.UserMobile;
import com.servlet.bankaccount.entity.BankAccount;
import com.servlet.bankaccount.entity.BankAccountData;
import com.servlet.bankaccount.entity.BodyBankAccount;
import com.servlet.bankaccount.mapper.GetDataBankAccount;
import com.servlet.bankaccount.repo.BankAccountRepo;
import com.servlet.bankaccount.service.BankAccountService;
import com.servlet.parameter.mapper.GetParameter;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.GlobalFunc;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import java.sql.Timestamp;

@Service
public class BankAccountHandler implements BankAccountService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BankAccountRepo repository;
	
	@Override
	public List<BankAccountData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataBankAccount().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataBankAccount(), queryParameters);
	}

	@Override
	public BankAccountData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataBankAccount().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isactive = true and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<BankAccountData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataBankAccount(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ReturnData saveBankAccount(Long idcompany, Long idbranch, BodyBankAccount body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		if(body.getDateopen() != null) {
			if(!GlobalFunc.isNumeric(body.getDateopen().toString())) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_BANK_ACCOUNT_DATEOPEN_NOTVALID,"Tanggal Pembuka Tidak Valid");
				validations.add(msg);
			}
			
		}else {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_BANK_ACCOUNT_DATEOPEN_EMPTY,"Tanggal Pembuka Tidak Valid");
			validations.add(msg);
		}
		
		if(!GlobalFunc.checkString(body.getNorekening(),false)) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_BANK_ACCOUNT_NO_REK_EMPTY,"No Rekening Tidak Valid");
			validations.add(msg);
		}else if(!GlobalFunc.isNumeric(body.getNorekening())) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_BANK_ACCOUNT_NO_REK_NOTNUMBER,"No Rekening Harus Angka");
			validations.add(msg);
		}
		
		
		if(!GlobalFunc.checkString(body.getCabang(),false)) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_BANK_ACCOUNT_CABANG_EMPTY,"Cabang Tidak Valid");
			validations.add(msg);
		}
		
		
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				BankAccount table = new BankAccount();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setCabang(body.getCabang());
				table.setNorekening(body.getNorekening());
				table.setDateopen(new Timestamp(body.getDateopen()));
				table.setCatatan1(body.getCatatan1());
				table.setCatatan2(body.getCatatan2());
				table.setIsactive(body.isIsactive());
				table.setNamabank(body.getNamabank());
				table.setIsdelete(false);
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
	public ReturnData updateBankAccount(Long idcompany, Long idbranch,Long id, BodyBankAccount body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		if(body.getDateopen() != null) {
			if(!GlobalFunc.isNumeric(body.getDateopen().toString())) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_BANK_ACCOUNT_DATEOPEN_NOTVALID,"Tanggal Pembuka Tidak Valid");
				validations.add(msg);
			}
			
		}else {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_BANK_ACCOUNT_DATEOPEN_EMPTY,"Tanggal Pembuka Tidak Valid");
			validations.add(msg);
		}
		
		if(!GlobalFunc.checkString(body.getNorekening(),false)) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_BANK_ACCOUNT_NO_REK_EMPTY,"No Rekening Tidak Valid");
			validations.add(msg);
		}else if(!GlobalFunc.isNumeric(body.getNorekening())) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_BANK_ACCOUNT_NO_REK_NOTNUMBER,"No Rekening Harus Angka");
			validations.add(msg);
		}
		
		
		if(!GlobalFunc.checkString(body.getCabang(),false)) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_BANK_ACCOUNT_CABANG_EMPTY,"Cabang Tidak Valid");
			validations.add(msg);
		}
		
		long idsave = 0;
		
		BankAccountData value = getById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
				BankAccount table = repository.getById(id);
				table.setCabang(body.getCabang());
				table.setNorekening(body.getNorekening());
				table.setDateopen(new Timestamp(body.getDateopen()));
				table.setCatatan1(body.getCatatan1());
				table.setCatatan2(body.getCatatan2());
				table.setIsactive(body.isIsactive());
				table.setNamabank(body.getNamabank());
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
	public ReturnData deleteBankAccount(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		BankAccountData value = getById(idcompany,idbranch,id);
		if(value != null) {
			BankAccount table = repository.getById(id);
			table.setIsdelete(true);
			idsave = repository.saveAndFlush(table).getId();
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

}
