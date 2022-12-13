package com.servlet.pengluarankasbank.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.asset.service.AssetService;
import com.servlet.bankaccount.service.BankAccountService;
import com.servlet.coa.service.CoaService;
import com.servlet.invoicetype.entity.ParamInvTypeDropDown;
import com.servlet.invoicetype.service.InvoiceTypeService;
import com.servlet.parametermanggala.service.ParameterManggalaService;
import com.servlet.pengluarankasbank.entity.BodyDetailPengeluaranKasBank;
import com.servlet.pengluarankasbank.entity.BodyPengeluaranKasBank;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBank;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankData;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankPK;
import com.servlet.pengluarankasbank.entity.PengeluaranHeaderAndDetail;
import com.servlet.pengluarankasbank.entity.PengeluaranKasBankData;
import com.servlet.pengluarankasbank.entity.PengeluaranKasBankTemplate;
import com.servlet.pengluarankasbank.entity.PengluaranKasBank;
import com.servlet.pengluarankasbank.mapper.GetDetailPengeluaranKasBankData;
import com.servlet.pengluarankasbank.mapper.GetDetailPengeluaranKasBankJoinTable;
import com.servlet.pengluarankasbank.mapper.GetPengeluaranKasBankData;
import com.servlet.pengluarankasbank.mapper.GetPengeluaranKasBankJoinTable;
import com.servlet.pengluarankasbank.mapper.GetTotalAmount;
import com.servlet.pengluarankasbank.repo.DetailPengeluaranKasBankRepo;
import com.servlet.pengluarankasbank.repo.PengeluaranKasBankRepo;
import com.servlet.pengluarankasbank.service.PengeluaranKasBankService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.workorder.entity.ParamDropDownWO;
import com.servlet.workorder.service.WorkOrderService;

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
	@Autowired
	private ParameterManggalaService parameterManggalaService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private InvoiceTypeService invoiceTypeService;
	@Autowired
	private AssetService assetService;
	
	
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
			val.setDisablededitordelete(compareDateDocWithParameter(idcompany,idbranch,val.getPaymentdate()));
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
				table.setIdwo(body.getIdwo());
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
			boolean flag = compareDateDocWithParameter(idcompany,idbranch,value.getPaymentdate());
			if(flag) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PENGELUARAN_DATE_CLOSEBOOK,"Tidak Bisa Edit/Delete, Sudah Tutup Buku");
				validations.add(msg);
			}
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					PengluaranKasBank table = repository.getById(id);
					table.setPaymentdate(new java.sql.Date(body.getPaymentdate()));
					table.setIdcoa(body.getIdcoa());
					table.setIdbank(body.getIdbank());
					table.setPaymentto(body.getPaymentto());
					table.setKeterangan(body.getKeterangan());
					table.setIdwo(body.getIdwo());
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
			boolean flag = compareDateDocWithParameter(idcompany,idbranch,value.getPaymentdate());
			if(flag) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PENGELUARAN_DATE_CLOSEBOOK,"Tidak Bisa Edit/Delete, Sudah Tutup Buku");
				validations.add(msg);
			}
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
		ParamDropDownWO paramwo = new ParamDropDownWO();
		paramwo.setStatus("OPEN");
		paramwo.setMenu("PENGELUARAN_KAS_BANK");
		
		ParamInvTypeDropDown paramInvType = new ParamInvTypeDropDown();
		paramInvType.setInvoicetype("REIMBURSEMENT");
		paramInvType.setMenu("PENGELUARAN_KAS_BANK");
		
		PengeluaranKasBankTemplate template = new PengeluaranKasBankTemplate();
		template.setBankOptions(bankAccountService.getListActiveBankAccount(idcompany, idbranch));
		template.setCoaOptions(coaService.getListActiveCOA(idcompany, idbranch));
		template.setWoOptions(workOrderService.getListDropDownByParam(idcompany, idbranch, paramwo));
		template.setInvoiceItemOptions(invoiceTypeService.getListDropDownInvoiceType(idcompany, idbranch, paramInvType));
		template.setAssetOptions(assetService.getListAssetForPengeluaran(idcompany, idbranch));
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
					detailPengeluaranKasBank.setIdinvoiceitem(detail.getIdinvoiceitem());
					
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

	@Override
	public Double summaryAmountPengeluaranByDate(Long idcompany, Long idbranch, java.sql.Date fromdate,
			java.sql.Date todate, Long idpengeluaran,Long idbank) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetTotalAmount().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? ");
		if(fromdate != null && todate != null) {
			if(idbank != null && idbank.longValue() > 0) {
				sqlBuilder.append(" and data.idpengeluarankasbank in (select pkb.id from m_pengeluaran_kas_bank as pkb where pkb.paymentdate >= '"+fromdate+"'  and pkb.paymentdate <= '"+todate+"' and pkb.isactive = true and pkb.isdelete = false and pkb.idbank = "+idbank+" ) ");
			}else {
				sqlBuilder.append(" and data.idpengeluarankasbank in (select pkb.id from m_pengeluaran_kas_bank as pkb where pkb.paymentdate >= '"+fromdate+"'  and pkb.paymentdate <= '"+todate+"' and pkb.isactive = true and pkb.isdelete = false ) ");
			}
			
		}
		if(idpengeluaran != null) {
			sqlBuilder.append(" and data.idpengeluarankasbank = "+idpengeluaran+" ");
		}
		
		
		
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetTotalAmount(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return 0.0;
	}

	@Override
	public List<DetailPengeluaranKasBankData> getListDetailById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPengeluaranKasBankJoinTable().schema());
		sqlBuilder.append(" where data.idpengeluarankasbank = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPengeluaranKasBankJoinTable(), queryParameters);
	}
	
	private boolean compareDateDocWithParameter(Long idcompany,Long idbranch,Date paymentdate) {
		java.sql.Date dtParamTutupBuku = parameterManggalaService.getValueByParamName(idcompany,idbranch,"BULANTUTUPBUKU","DATE").getDateValue();
		boolean disableBtn = false;
		if(dtParamTutupBuku != null) {
			Timestamp tsDocument = new Timestamp(paymentdate.getTime());
			Timestamp tsTutupBuku = new Timestamp(dtParamTutupBuku.getTime());
			int compare = tsDocument.compareTo(tsTutupBuku);
			if(compare <= 0) {
				disableBtn = true;
			}
			
		}
		return disableBtn;
	}

	@Override
	public Double summaryAmountPengeluaranByIdWo(Long idcompany, Long idbranch, java.sql.Date fromdate,
			java.sql.Date todate, Long idwo,Long idbank) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetTotalAmount().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? ");
		if(fromdate != null && todate != null) {
			if(idbank.longValue() > 0) {
				sqlBuilder.append(" and data.idpengeluarankasbank in (select pkb.id from m_pengeluaran_kas_bank as pkb where pkb.paymentdate >= '"+fromdate+"'  and pkb.paymentdate <= '"+todate+"' and pkb.isactive = true and pkb.isdelete = false and pkb.idbank = "+idbank+" and pkb.idwo = "+idwo+" ) ");
			}else {
				sqlBuilder.append(" and data.idpengeluarankasbank in (select pkb.id from m_pengeluaran_kas_bank as pkb where pkb.paymentdate >= '"+fromdate+"'  and pkb.paymentdate <= '"+todate+"' and pkb.isactive = true and pkb.isdelete = false and pkb.idwo = "+idwo+" ) ");
			}
			
		}

		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetTotalAmount(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return 0.0;
	}
	
	private List<DetailPengeluaranKasBankData> getListDetailByIdWONotJoin(Long idcompany, Long idbranch, Long idWO) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? ");
		sqlBuilder.append(" and data.idpengeluarankasbank in (select m.id from m_pengeluaran_kas_bank as m where m.isactive = true  and m.isdelete = false and m.idwo = "+idWO+" ) ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPengeluaranKasBankData(), queryParameters);
	}

	@Override
	public PengeluaranHeaderAndDetail getListByIdWo(Long idcompany, Long idbranch, Long idWO) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.idwo = ? and data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idWO, idcompany,idbranch};
		List<PengeluaranKasBankData> headers = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPengeluaranKasBankData(), queryParameters);
		
		List<DetailPengeluaranKasBankData> details = getListDetailByIdWONotJoin(idcompany, idbranch, idWO);
		
		PengeluaranHeaderAndDetail data = new PengeluaranHeaderAndDetail();
		data.setHeaders(headers);
		data.setDetails(details);
		
		return data;
	}

}
