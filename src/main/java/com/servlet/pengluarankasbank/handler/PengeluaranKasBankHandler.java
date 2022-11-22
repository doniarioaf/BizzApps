package com.servlet.pengluarankasbank.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.bankaccount.service.BankAccountService;
import com.servlet.coa.service.CoaService;
import com.servlet.pengluarankasbank.entity.BodyDetailPengeluaranKasBank;
import com.servlet.pengluarankasbank.entity.BodyPengeluaranKasBank;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBank;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankData;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankPK;
import com.servlet.pengluarankasbank.entity.PengeluaranKasBankData;
import com.servlet.pengluarankasbank.entity.PengeluaranKasBankTemplate;
import com.servlet.pengluarankasbank.entity.PengluaranKasBank;
import com.servlet.pengluarankasbank.mapper.GetDetailPengeluaranKasBankJoinTable;
import com.servlet.pengluarankasbank.mapper.GetPengeluaranKasBankData;
import com.servlet.pengluarankasbank.mapper.GetPengeluaranKasBankJoinTable;
import com.servlet.pengluarankasbank.repo.DetailPengeluaranKasBankRepo;
import com.servlet.pengluarankasbank.repo.PengeluaranKasBankRepo;
import com.servlet.pengluarankasbank.service.PengeluaranKasBankService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class PengeluaranKasBankHandler implements PengeluaranKasBankService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PengeluaranKasBankRepo repository;
	@Autowired
	private DetailPengeluaranKasBankRepo detailPengeluaranKasBankRepo;
	@Autowired
	private RunningNumberService runningNumberService;
	@Autowired
	private CoaService coaService;
	@Autowired
	private BankAccountService bankAccountService;
	
	@Override
	public List<PengeluaranKasBankData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPengeluaranKasBankData(), queryParameters);
	}

	@Override
	public List<PengeluaranKasBankData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPengeluaranKasBankData(), queryParameters);
	}

	@Override
	public PengeluaranKasBankData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPengeluaranKasBankJoinTable().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PengeluaranKasBankData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPengeluaranKasBankJoinTable(), queryParameters);
		if(list != null && list.size() > 0) {
			PengeluaranKasBankData val = list.get(0);
			val.setDetails(getDetails(idcompany,idbranch,id));
			return val;
		}
		return null;
	}

	@Override
	public ReturnData saveData(Long idcompany, Long idbranch, Long iduser, BodyPengeluaranKasBank body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PENGELUARANKASBANK, ts);
		if(docNumber.equals("")) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
			validations.add(msg);
		}
		
		if(validations.size() == 0) {
			try {
				try {
				PengluaranKasBank table = new PengluaranKasBank();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setNodocument(docNumber);
				table.setPaymentdate(new java.sql.Date(body.getPaymentdate()));
				table.setIdcoa(body.getIdcoa());
				table.setIdbank(body.getIdbank());
				table.setPaymentto(body.getPaymentto());
				table.setKeterangan(body.getKeterangan());
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				
				idsave = repository.saveAndFlush(table).getId();
				}catch (Exception e) {
					// TODO: handle exception
					runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PENGELUARANKASBANK);
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
					validations.add(msg);
					e.printStackTrace();
				}
				if(idsave > 0) {
					putDetail(body.getDetails(), idcompany, idbranch, idsave, "ADD");
				}
				
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
	public ReturnData updateData(Long idcompany, Long idbranch, Long iduser, Long id, BodyPengeluaranKasBank body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		PengeluaranKasBankData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					PengluaranKasBank table = repository.getById(id);
					table.setPaymentdate(new java.sql.Date(body.getPaymentdate()));
					table.setIdcoa(body.getIdcoa());
					table.setIdbank(body.getIdbank());
					table.setPaymentto(body.getPaymentto());
					table.setKeterangan(body.getKeterangan());
					table.setIsactive(body.isIsactive());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					
					idsave = repository.saveAndFlush(table).getId();
					
					putDetail(body.getDetails(), idcompany, idbranch, idsave, "EDIT");
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
	public ReturnData deleteData(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		PengeluaranKasBankData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					PengluaranKasBank table = repository.getById(id);
					table.setIsdelete(true);
					table.setDeleteby(iduser.toString());
					table.setDeletedate(ts);
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
	public PengeluaranKasBankTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return setTemplate(idcompany,idbranch);
	}

	@Override
	public PengeluaranKasBankData getByIdWithTemplate(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PengeluaranKasBankData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPengeluaranKasBankData(), queryParameters);
		if(list != null && list.size() > 0) {
			PengeluaranKasBankData val = list.get(0);
			val.setDetails(getDetails(idcompany,idbranch,id));
			val.setTemplate(setTemplate(idcompany,idbranch));
			return val;
		}
		return null;
	}
	
	private PengeluaranKasBankData checkById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PengeluaranKasBankData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPengeluaranKasBankData(), queryParameters);
		if(list != null && list.size() > 0) {
			PengeluaranKasBankData val = list.get(0);
//			val.setTemplate(getTemplate(idcompany, idbranch));
			return val;
		}
		return null;
	}
	
	private PengeluaranKasBankTemplate setTemplate(Long idcompany, Long idbranch) {
		PengeluaranKasBankTemplate template = new PengeluaranKasBankTemplate();
		template.setBankOptions(bankAccountService.getListActiveBankAccount(idcompany, idbranch));
		template.setCoaOptions(coaService.getListActiveCOA(idcompany, idbranch));
		return template;
	}
	
	private String putDetail(BodyDetailPengeluaranKasBank[] details,Long idcompany, Long idbranch,long idsave,String action) {
		//detailPengeluaranKasBankRepo
		if(action.equals("EDIT")) {
			detailPengeluaranKasBankRepo.deleteAllDetail(idsave, idcompany, idbranch);
		}
		
		if(details != null) {
			if(details.length > 0) {
				long count = 1;
				for(int i=0; i < details.length; i++) {
					BodyDetailPengeluaranKasBank detail = details[i];
					
					DetailPengeluaranKasBankPK pk = new DetailPengeluaranKasBankPK();
					pk.setCounter(count);
					pk.setIdcompany(idcompany);
					pk.setIdbranch(idbranch);
					pk.setIdpengeluarankasbank(idsave);
					
					DetailPengeluaranKasBank detailPengeluaranKasBank = new DetailPengeluaranKasBank();
					detailPengeluaranKasBank.setDetailPengeluaranKasBankPK(pk);
					detailPengeluaranKasBank.setIdcoa(detail.getIdcoa());
					detailPengeluaranKasBank.setCatatan(detail.getCatatan());
					detailPengeluaranKasBank.setAmount(detail.getAmount());
					detailPengeluaranKasBank.setIdasset(detail.getIdasset());
					
					detailPengeluaranKasBankRepo.saveAndFlush(detailPengeluaranKasBank);
					count++;
				}
			}
		}
		
		return "";
	}
	
	private List<DetailPengeluaranKasBankData> getDetails(Long idcompany, Long idbranch,Long idpengeluarankasbank) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPengeluaranKasBankJoinTable().schema());
		sqlBuilder.append(" where data.idpengeluarankasbank = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idpengeluarankasbank,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPengeluaranKasBankJoinTable(), queryParameters);
	}

}
