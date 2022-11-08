package com.servlet.penerimaankasbank.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.penerimaankasbank.entity.BodyDetailPenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.BodyPenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankData;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankPK;
import com.servlet.penerimaankasbank.entity.PenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.PenerimaanKasBankData;
import com.servlet.penerimaankasbank.mapper.GetDetailPenerimaanKasBankData;
import com.servlet.penerimaankasbank.mapper.GetPenerimaanKasBankNotJoinTable;
import com.servlet.penerimaankasbank.repo.DetailPenerimaanKasBankRepo;
import com.servlet.penerimaankasbank.repo.PenerimaanKasBankRepo;
import com.servlet.penerimaankasbank.service.PenerimaanKasBankService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class PenerimaanKasBankHandler implements PenerimaanKasBankService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PenerimaanKasBankRepo repository;
	@Autowired
	private RunningNumberService runningNumberService;
	@Autowired
	private DetailPenerimaanKasBankRepo detailPenerimaanKasBankRepo ;
	
	@Override
	public List<PenerimaanKasBankData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanKasBankNotJoinTable().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanKasBankNotJoinTable(), queryParameters);
	}

	@Override
	public List<PenerimaanKasBankData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanKasBankNotJoinTable().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanKasBankNotJoinTable(), queryParameters);
	}

	@Override
	public PenerimaanKasBankData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanKasBankNotJoinTable().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PenerimaanKasBankData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanKasBankNotJoinTable(), queryParameters);
		if(list != null && list.size() > 0) {
			PenerimaanKasBankData val = list.get(0);
			val.setDetails(getDetails(idcompany, idbranch, id));
//			val.setTemplate(getTemplate(idcompany, idbranch));
			return val;
		}
		return null;
	}

	@Override
	public ReturnData saveData(Long idcompany, Long idbranch, Long iduser, BodyPenerimaanKasBank body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PENERIMAANKASBANK, ts);
		if(docNumber.equals("")) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
			validations.add(msg);
		}
		
		if(validations.size() == 0) {
			try {
			PenerimaanKasBank table = new PenerimaanKasBank();
			table.setIdcompany(idcompany);
			table.setIdbranch(idbranch);
			table.setNodocument(docNumber);
			table.setReceivedate(new java.sql.Date(body.getReceivedate()));
			table.setReceivefrom(body.getReceivefrom());
			table.setIdcoa(body.getIdcoa());
			table.setIdbank(body.getIdbank());
			table.setKeterangan(body.getKeterangan());
			table.setIsactive(body.isIsactive());
			table.setIsdelete(false);
			table.setCreatedby(iduser.toString());
			table.setCreateddate(ts);
			
			idsave = repository.saveAndFlush(table).getId();
			
			putDetail(body.getDetails(), idcompany, idbranch, idsave, "ADD");
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
	public ReturnData updateData(Long idcompany, Long idbranch, Long iduser, Long id, BodyPenerimaanKasBank body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		PenerimaanKasBankData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					PenerimaanKasBank table = repository.getById(id);
					table.setReceivedate(new java.sql.Date(body.getReceivedate()));
					table.setReceivefrom(body.getReceivefrom());
					table.setIdcoa(body.getIdcoa());
					table.setIdbank(body.getIdbank());
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
		PenerimaanKasBankData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					PenerimaanKasBank table = repository.getById(id);
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
	
	private PenerimaanKasBankData checkById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanKasBankNotJoinTable().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PenerimaanKasBankData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanKasBankNotJoinTable(), queryParameters);
		if(list != null && list.size() > 0) {
			PenerimaanKasBankData val = list.get(0);
//			val.setTemplate(getTemplate(idcompany, idbranch));
			return val;
		}
		return null;
	}
	
	private String putDetail(BodyDetailPenerimaanKasBank[] details,Long idcompany, Long idbranch,long idsave,String action) {
		if(action.equals("EDIT")) {
			detailPenerimaanKasBankRepo.deleteAllDetail(idsave, idcompany, idbranch);
		}
		if(details != null) {
			if(details.length > 0) {
				long count = 1;
				for(int i=0; i < details.length; i++) {
					BodyDetailPenerimaanKasBank detail = details[i];
					
					DetailPenerimaanKasBankPK pk = new DetailPenerimaanKasBankPK();
					pk.setCounter(count);
					pk.setIdcompany(idcompany);
					pk.setIdbranch(idbranch);
					pk.setIdpenerimaankasbank(idsave);
					
					DetailPenerimaanKasBank detailPenerimaanKasBank = new DetailPenerimaanKasBank();
					detailPenerimaanKasBank.setDetailPenerimaanKasBankPK(pk);
					detailPenerimaanKasBank.setAmount(detail.getAmount());
					detailPenerimaanKasBank.setCatatan(detail.getCatatan());
					detailPenerimaanKasBank.setIdcoa(detail.getIdcoa());
					detailPenerimaanKasBank.setIdinvoice(detail.getIdinvoice());
					detailPenerimaanKasBank.setIdworkorder(detail.getIdworkorder());
					detailPenerimaanKasBank.setIsdownpayment(detail.getIsdownpayment());
					
					detailPenerimaanKasBankRepo.saveAndFlush(detailPenerimaanKasBank);
					count++;
				}
			}
		}
		return "";
	}
	
	private List<DetailPenerimaanKasBankData> getDetails(Long idcompany, Long idbranch,Long idpenerimaankasbank) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPenerimaanKasBankData().schema());
		sqlBuilder.append(" where data.idpenerimaankasbank = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idpenerimaankasbank,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPenerimaanKasBankData(), queryParameters);
	}

}
