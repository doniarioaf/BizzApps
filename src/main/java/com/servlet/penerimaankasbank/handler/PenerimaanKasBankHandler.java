package com.servlet.penerimaankasbank.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.bankaccount.entity.BankAccount;
import com.servlet.bankaccount.service.BankAccountService;
import com.servlet.coa.service.CoaService;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.parametermanggala.service.ParameterManggalaService;
import com.servlet.penerimaankasbank.entity.BodyDetailPenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.BodyPenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankData;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankPK;
import com.servlet.penerimaankasbank.entity.PenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.PenerimaanKasBankData;
import com.servlet.penerimaankasbank.entity.PenerimaanKasBankTemplate;
import com.servlet.penerimaankasbank.entity.PenerimaanPengeluaranData;
import com.servlet.penerimaankasbank.mapper.GetDataReportKasBankMapper;
import com.servlet.penerimaankasbank.mapper.GetDetailPenerimaanKasBankData;
import com.servlet.penerimaankasbank.mapper.GetDetailPenerimaanKasBankJoinTable;
import com.servlet.penerimaankasbank.mapper.GetPenerimaanKasBankJoinBank;
import com.servlet.penerimaankasbank.mapper.GetPenerimaanKasBankJoinTable;
import com.servlet.penerimaankasbank.mapper.GetPenerimaanKasBankNotJoinTable;
import com.servlet.penerimaankasbank.mapper.GetPenerimaanPengeluaranData;
import com.servlet.penerimaankasbank.mapper.GetTotalAmount;
import com.servlet.penerimaankasbank.repo.DetailPenerimaanKasBankRepo;
import com.servlet.penerimaankasbank.repo.PenerimaanKasBankRepo;
import com.servlet.penerimaankasbank.service.PenerimaanKasBankService;
import com.servlet.report.entity.EntityHelperKasBank;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.user.entity.UserPermissionData;
import com.servlet.user.service.UserAppsService;
import com.servlet.workorder.entity.WorkOrder;
import com.servlet.workorder.entity.WorkOrderData;
import com.servlet.workorder.repo.WorkOrderRepo;
import com.servlet.workorder.service.WorkOrderService;

@Service
public class PenerimaanKasBankHandler implements PenerimaanKasBankService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PenerimaanKasBankRepo repository;
	@Autowired
	private RunningNumberService runningNumberService;
	@Autowired
	private DetailPenerimaanKasBankRepo detailPenerimaanKasBankRepo;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private CoaService coaService;
	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private WorkOrderRepo workOrderRepo;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private ParameterManggalaService parameterManggalaService;
	@Autowired
	private UserAppsService userAppsService;
	
	@Override
	public List<PenerimaanKasBankData> getListAll(Long idcompany, Long idbranch, Long iduser) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanKasBankNotJoinTable().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanKasBankNotJoinTable(), queryParameters);
	}
	
	@Override
	public List<PenerimaanKasBankData> getListAllCheckBank(Long idcompany, Long idbranch, Long iduser) {
		// TODO Auto-generated method stub
		boolean checkFinanceJunior = checkFinanceJunior(iduser);
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanKasBankJoinBank().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		if(checkFinanceJunior) {
			sqlBuilder.append(" and bank.showfinancejunior = true ");
		}
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanKasBankJoinBank(), queryParameters);
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
	public PenerimaanKasBankData getById(Long idcompany, Long idbranch, Long id, Long iduser) {
		// TODO Auto-generated method stub
		boolean checkFinanceJunior = checkFinanceJunior(iduser);
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanKasBankJoinTable().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		if(checkFinanceJunior) {
			sqlBuilder.append(" and bank.showfinancejunior = true ");
		}
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PenerimaanKasBankData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanKasBankJoinTable(), queryParameters);
		if(list != null && list.size() > 0) {
			PenerimaanKasBankData val = list.get(0);
			val.setDetails(getDetails(idcompany, idbranch, id));
			val.setDisablededitordelete(compareDateDocWithParameter(idcompany,idbranch,val.getReceivedate()));
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
		String docNumber = "";
		
		boolean checkFinanceJunior = checkFinanceJunior(iduser);
		if(checkFinanceJunior) {
			BankAccount bank = bankAccountService.getId(body.getIdbank());
			if(bank != null && bank.getId() != 0) {
				if(!bank.isShowfinancejunior()) {
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_SAVE_BANK_FINANCE_JUNIOR,"Gagal Save");
					validations.add(msg);
				}
			}
		}
		
		if(validations.size() == 0) {
			docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PENERIMAANKASBANK, ts);
			if(docNumber.equals("")) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
				validations.add(msg);
			}
		}
		
		List<ValidationDataMessage> validationsDetails = checkDetail(body.getDetails(), idcompany, idbranch,null,"ADD");
		validations.addAll(validationsDetails);
		
		if(validations.size() == 0 && validationsDetails.size() == 0) {
			try {
			
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
			}catch (Exception e) {
				// TODO: handle exception
				runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PENERIMAANKASBANK);
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
	public ReturnData updateData(Long idcompany, Long idbranch, Long iduser, Long id, BodyPenerimaanKasBank body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		PenerimaanKasBankData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			List<ValidationDataMessage> validationsDetails = checkDetail(body.getDetails(), idcompany, idbranch,id,"EDIT");
			validations.addAll(validationsDetails);
			
			boolean flag = compareDateDocWithParameter(idcompany,idbranch,value.getReceivedate());
			if(flag) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PENERIMAAN_DATE_CLOSEBOOK,"Tidak Bisa Edit/Delete, Sudah Tutup Buku");
				validations.add(msg);
			}
			
			if(validations.size() == 0 && validationsDetails.size() == 0) {
				boolean checkFinanceJunior = checkFinanceJunior(iduser);
				if(checkFinanceJunior) {
					BankAccount bank = bankAccountService.getId(body.getIdbank());
					if(bank != null && bank.getId() != 0) {
						if(!bank.isShowfinancejunior()) {
							ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_SAVE_BANK_FINANCE_JUNIOR,"Gagal Save");
							validations.add(msg);
						}
					}
				}
			}
			if(validations.size() == 0 && validationsDetails.size() == 0) {
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
			boolean flag = compareDateDocWithParameter(idcompany,idbranch,value.getReceivedate());
			if(flag) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PENERIMAAN_DATE_CLOSEBOOK,"Tidak Bisa Edit/Delete, Sudah Tutup Buku");
				validations.add(msg);
			}
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
	
	private List<ValidationDataMessage> checkDetail(BodyDetailPenerimaanKasBank[] details,Long idcompany, Long idbranch,Long id,String action) {
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		if(details != null) {
			if(details.length > 0) {
				for(int i=0; i < details.length; i++) {
					BodyDetailPenerimaanKasBank detail = details[i];
					boolean isNext = true;
					if(action.equals("EDIT")) {
						HashMap<String, Object> param = new HashMap<String, Object>();
						param.put("type", "WO");
						param.put("idwo", detail.getIdworkorder());
						List<DetailPenerimaanKasBankData> list = getDetailsForCheck(idcompany,idbranch,id,param);
						if(list != null) {
							isNext = false;
							continue;
						}
					}
					if(isNext) {
						if(detail.getIdworkorder() != null) {
							WorkOrderData woData = workOrderService.getByIdNotJoin(idcompany, idbranch, detail.getIdworkorder());
							if(woData != null) {
								if(!woData.getStatus().equals("OPEN")) {
									ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PENERIMAANKASBANK_WO_STATUS_NOT_OPEN,"Check status WO "+woData.getNodocument());
									validations.add(msg);
								}
							}else {
								Optional<WorkOrder> opWo =  workOrderRepo.findById(detail.getIdworkorder());
								if(opWo.isPresent()) {
									if(!opWo.get().isIsactive() || opWo.get().isIsdelete()) {
										ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PENERIMAANKASBANK_WO_NOT_ACTIVE,"WO "+opWo.get().getNodocument()+" tidak ditemukan");
										validations.add(msg);
									}
								}else {
									ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PENERIMAANKASBANK_WO_NOT_ACTIVE,"WO "+detail.getIdworkorder());
									validations.add(msg);
								}
								
							}
						}
					}
				}
			}
		}
		return validations;
	}
	private String putDetail(BodyDetailPenerimaanKasBank[] details,Long idcompany, Long idbranch,long idsave,String action) {
		if(action.equals("EDIT")) {
			detailPenerimaanKasBankRepo.deleteAllDetail(idsave, idcompany, idbranch);
		}
		List<Long> listIdWo = new ArrayList<Long>();
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
					
//					if(detail.getIdworkorder() != null) {
//						if(detail.getIdworkorder().longValue() > 0) {
//							listIdWo.add(detail.getIdworkorder());
//						}
//					}
				}
			}
			
//			if(listIdWo.size() > 0) {
//				for(Long idwo : listIdWo) {
//					double amountPenerimaan = 0.0;
//					double amountInvoice = 0.0;
//					
//					List<InvoiceData> listInv = invoiceService.getListInvoiceByIdWo(idcompany, idbranch, idwo);
//					if(listInv != null && listInv.size() > 0) {
//						for(InvoiceData invData : listInv) {
//							amountInvoice += invData.getTotalinvoice().doubleValue();
//						}
//					}
//					
//					List<DetailPenerimaanKasBankData> listPenerimaan = getDetailsByIdWo(idcompany, idbranch, idwo);
//					if(listPenerimaan != null && listPenerimaan.size() > 0) {
//						for(DetailPenerimaanKasBankData data : listPenerimaan) {
//							amountPenerimaan += data.getAmount().doubleValue();
//						}
//					}
//					
//					if(amountPenerimaan >= amountInvoice) {
//						workOrderService.changeStatusWO(idcompany, idbranch, idwo, "CLOSE");
//					}else{
//						workOrderService.changeStatusWO(idcompany, idbranch, idwo, "OPEN");
//					}
//					
//				}
//			}
			
			
		}
		return "";
	}
	
	private PenerimaanKasBankTemplate setTemplate(Long idcompany, Long idbranch, Long id, Long iduser) {
		boolean checkFinanceJunior = checkFinanceJunior(iduser);
		PenerimaanKasBankTemplate template = new PenerimaanKasBankTemplate();
		template.setBankOptions(bankAccountService.getListActiveBankAccountCheckFinanceJunior(idcompany, idbranch, checkFinanceJunior));
		template.setCoaOptions(coaService.getListActiveCOA(idcompany, idbranch));
		if(id != null) {
			HashMap<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("type", "PENERIMAANKASBANK");
			mapParam.put("idpenerimaankasbank", id);
			template.setWoOptions(workOrderService.getListWOByStatus(idcompany, idbranch, "OPEN",mapParam));
		}else {
			template.setWoOptions(workOrderService.getListWOByStatus(idcompany, idbranch, "OPEN",null));
		}
		
		return template;
	}
	
	private List<DetailPenerimaanKasBankData> getDetails(Long idcompany, Long idbranch,Long idpenerimaankasbank) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPenerimaanKasBankJoinTable().schema());
		sqlBuilder.append(" where data.idpenerimaankasbank = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idpenerimaankasbank,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPenerimaanKasBankJoinTable(), queryParameters);
	}
	
	private List<DetailPenerimaanKasBankData> getDetailsForCheck(Long idcompany, Long idbranch,Long idpenerimaankasbank,HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPenerimaanKasBankData().schema());
		sqlBuilder.append(" where data.idpenerimaankasbank = ? and data.idcompany = ? and data.idbranch = ? ");
		if(param != null) {
			String type = (String) param.get("type");
			if(type.equals("WO")) {
				Long idwo = (Long) param.get("idwo");
				sqlBuilder.append(" and data.idworkorder = "+idwo+" ");
			}
		}
		final Object[] queryParameters = new Object[] {idpenerimaankasbank,idcompany,idbranch};
		List<DetailPenerimaanKasBankData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPenerimaanKasBankData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public PenerimaanKasBankTemplate getTemplate(Long idcompany, Long idbranch, Long iduser) {
		// TODO Auto-generated method stub
		return setTemplate(idcompany,idbranch,null,iduser);
	}

	@Override
	public PenerimaanKasBankData getByIdWithTemplate(Long idcompany, Long idbranch, Long id, Long iduser) {
		// TODO Auto-generated method stub
		boolean checkFinanceJunior = checkFinanceJunior(iduser);
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanKasBankJoinBank().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		if(checkFinanceJunior) {
			sqlBuilder.append(" and bank.showfinancejunior = true ");
		}
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PenerimaanKasBankData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanKasBankJoinBank(), queryParameters);
		if(list != null && list.size() > 0) {
			PenerimaanKasBankData val = list.get(0);
			val.setDetails(getDetails(idcompany, idbranch, id));
			val.setTemplate(setTemplate(idcompany, idbranch, id,iduser));
			return val;
		}
		return null;
	}

	@Override
	public List<DetailPenerimaanKasBankData> getListDetailByIdInvoice(Long idcompany, Long idbranch, Long idInvoice) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPenerimaanKasBankData().schema());
		sqlBuilder.append(" where data.idinvoice = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idInvoice,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPenerimaanKasBankData(), queryParameters);
	}

	@Override
	public List<PenerimaanKasBankData> getListByDetailIdInvoice(Long idcompany, Long idbranch, Long idinvoice) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanKasBankNotJoinTable().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		sqlBuilder.append(" and data.id in (select detail.idpenerimaankasbank from detail_penerimaan_kas_bank as detail where detail.idinvoice = "+idinvoice+" ) ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanKasBankNotJoinTable(), queryParameters);
	}

	@Override
	public List<DetailPenerimaanKasBankData> getListDetailByIdWO(Long idcompany, Long idbranch, Long idWo) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPenerimaanKasBankData().schema());
		sqlBuilder.append(" where data.idworkorder = ? and data.idcompany = ? and data.idbranch = ? and (data.idinvoice isnull or data.idinvoice = 0) order by data.amount asc");
		final Object[] queryParameters = new Object[] {idWo,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPenerimaanKasBankData(), queryParameters);
	}
	
	private List<DetailPenerimaanKasBankData> getDetailsByIdWo(Long idcompany, Long idbranch,Long idworkorder) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPenerimaanKasBankData().schema());
		sqlBuilder.append(" where data.idworkorder = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idworkorder,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPenerimaanKasBankData(), queryParameters);
	}

	@Override
	public List<PenerimaanKasBankData> getListByDetailIdInvoiceJoinBank(Long idcompany, Long idbranch, Long idinvoice) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanKasBankJoinBank().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		sqlBuilder.append(" and data.id in (select detail.idpenerimaankasbank from detail_penerimaan_kas_bank as detail where detail.idinvoice = "+idinvoice+" ) ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanKasBankJoinBank(), queryParameters);
	}

	@Override
	public Double summaryAmountPenerimaanByDate(Long idcompany, Long idbranch, java.sql.Date fromdate,
			java.sql.Date todate,Long idpenerimaan,Long idbank) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetTotalAmount().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? ");
		if(fromdate != null && todate != null) {
			if(idbank != null && idbank.longValue() > 0) {
				sqlBuilder.append(" and data.idpenerimaankasbank in (select pkb.id from m_penerimaan_kas_bank as pkb where pkb.receivedate >= '"+fromdate+"'  and pkb.receivedate <= '"+todate+"' and pkb.isactive = true and pkb.isdelete = false and pkb.idbank = "+idbank+" ) ");
			}else {
				sqlBuilder.append(" and data.idpenerimaankasbank in (select pkb.id from m_penerimaan_kas_bank as pkb where pkb.receivedate >= '"+fromdate+"'  and pkb.receivedate <= '"+todate+"' and pkb.isactive = true and pkb.isdelete = false ) ");
			}
			
		}
		if(idpenerimaan != null) {
			sqlBuilder.append(" and data.idpenerimaankasbank = "+idpenerimaan+"  ");
		}
		
		
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetTotalAmount(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return 0.0;
	}

	@Override
	public List<PenerimaanPengeluaranData> getPenerimaanPengeluaranData(Long idcompany, Long idbranch,
			java.sql.Date fromdate, java.sql.Date todate, Long idbank) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPenerimaanPengeluaranData().schema());
		sqlBuilder.append(" where ");
		sqlBuilder.append(" (data.idcompany = ? and data.idbranch = ?  and data.isactive = true and data.isdelete = false and data.idbank = "+idbank+" and data.receivedate >= '"+fromdate+"'  and data.receivedate <= '"+todate+"' ) ");
		sqlBuilder.append(" or ");
		sqlBuilder.append(" (pengeluaran.idcompany = "+idcompany+" and pengeluaran.idbranch = "+idbranch+"  and pengeluaran.isactive = true and pengeluaran.isdelete = false and pengeluaran.idbank = "+idbank+" and pengeluaran.paymentdate >= '"+fromdate+"'  and pengeluaran.paymentdate <= '"+todate+"' ) ");
		sqlBuilder.append(" order by data.receivedate , pengeluaran.paymentdate asc ");
		
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPenerimaanPengeluaranData(), queryParameters);
	}

	@Override
	public List<DetailPenerimaanKasBankData> getListDetailByIdReportKasBank(Long idcompany, Long idbranch,
			Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPenerimaanKasBankJoinTable().schema());
		sqlBuilder.append(" where data.idpenerimaankasbank = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPenerimaanKasBankJoinTable(), queryParameters);
	}
	
	private boolean compareDateDocWithParameter(Long idcompany,Long idbranch,Date ReceiveDate) {
		java.sql.Date dtParamTutupBuku = parameterManggalaService.getValueByParamName(idcompany,idbranch,"BULANTUTUPBUKU","DATE").getDateValue();
		boolean disableBtn = false;
		if(dtParamTutupBuku != null) {
			Timestamp tsDocument = new Timestamp(ReceiveDate.getTime());
			Timestamp tsTutupBuku = new Timestamp(dtParamTutupBuku.getTime());
			int compare = tsDocument.compareTo(tsTutupBuku);
			if(compare <= 0) {
				disableBtn = true;
			}
			
		}
		return disableBtn;
	}

	@Override
	public Double summaryAmountPenerimaanByIdWO(Long idcompany, Long idbranch, java.sql.Date fromdate,
			java.sql.Date todate, Long idwo, Long idbank,String invoiceType) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetTotalAmount().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? ");
		if(fromdate != null && todate != null) {
			if(idbank.longValue() > 0) {
				sqlBuilder.append(" and data.idpenerimaankasbank in (select pkb.id from m_penerimaan_kas_bank as pkb where pkb.receivedate >= '"+fromdate+"'  and pkb.receivedate <= '"+todate+"' and pkb.isactive = true and pkb.isdelete = false and pkb.idbank = "+idbank+" ) ");
			}else {
				sqlBuilder.append(" and data.idpenerimaankasbank in (select pkb.id from m_penerimaan_kas_bank as pkb where pkb.receivedate >= '"+fromdate+"'  and pkb.receivedate <= '"+todate+"' and pkb.isactive = true and pkb.isdelete = false ) ");
			}
			
		}
		
		sqlBuilder.append(" and data.idworkorder = "+idwo+"  ");
		if(!invoiceType.equals("")) {
			sqlBuilder.append(" and data.idinvoice in (select id from m_invoice as inv where inv.isactive = true and inv.isdelete = false and inv.idinvoicetype = '"+invoiceType+"' )  ");
		}
		
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetTotalAmount(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return 0.0;
	}

	@Override
	public Double getSummaryDetailDPByIdWO(Long idcompany, Long idbranch, Long idWO) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetTotalAmount().schema());
		sqlBuilder.append(" where data.idworkorder = ? and data.idcompany = ? and data.idbranch = ? and data.isdownpayment = 'Y' and data.idinvoice notnull and data.idinvoice > 0 ");
		final Object[] queryParameters = new Object[] {idWO,idcompany,idbranch};
		List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetTotalAmount(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return 0.0;
	}

	@Override
	public List<EntityHelperKasBank> getDataReportKasBankPenerimaan(Long idcompany, Long idbranch, java.sql.Date fromdate,
			java.sql.Date todate, Long idbank) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataReportKasBankMapper().schema());
		sqlBuilder.append(" where ");
		sqlBuilder.append(" (mpenerimaan.idcompany = ? and mpenerimaan.idbranch = ?  and mpenerimaan.isactive = true and mpenerimaan.isdelete = false and mpenerimaan.idbank = "+idbank+" and mpenerimaan.receivedate >= '"+fromdate+"'  and mpenerimaan.receivedate <= '"+todate+"' ) ");
//		sqlBuilder.append(" or ");
//		sqlBuilder.append(" (mpengeluaran.idcompany = "+idcompany+" and mpengeluaran.idbranch = "+idbranch+"  and mpengeluaran.isactive = true and mpengeluaran.isdelete = false and mpengeluaran.idbank = "+idbank+" and mpengeluaran.paymentdate >= '"+fromdate+"'  and mpengeluaran.paymentdate <= '"+todate+"' ) ");
//		sqlBuilder.append(" order by mpenerimaan.receivedate , mpengeluaran.paymentdate asc ");
		
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataReportKasBankMapper(), queryParameters);
	}
	
	
	private boolean checkFinanceJunior(Long iduser) {
		boolean flagpermission = false;
		List<UserPermissionData> listPermission =  new ArrayList<UserPermissionData>(userAppsService.getListUserPermission(iduser));
		if(listPermission != null && listPermission.size() > 0) {
			for(UserPermissionData permissiondata : listPermission) {
				if(permissiondata.getPermissioncode().equals("SUPERUSER")) {
//					flagpermission = true;
					break;
				}else if(permissiondata.getPermissioncode().equals(ConstansPermission.READ_FINANCING_JUNIOR)) {
					flagpermission = true;
					break;
				}
			}
		}
		return flagpermission;
	}

}
