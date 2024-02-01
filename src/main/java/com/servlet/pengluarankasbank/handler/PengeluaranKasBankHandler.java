package com.servlet.pengluarankasbank.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.asset.entity.Asset;
import com.servlet.asset.entity.AssetMappingData;
import com.servlet.asset.entity.HistoryAssetMapping;
import com.servlet.asset.entity.HistoryAssetMappingData;
import com.servlet.asset.repo.AssetRepo;
import com.servlet.asset.repo.HistoryAssetMappingRepo;
import com.servlet.asset.service.AssetService;
import com.servlet.bankaccount.entity.BankAccount;
import com.servlet.bankaccount.service.BankAccountService;
import com.servlet.coa.service.CoaService;
import com.servlet.employeemanggala.entity.EmployeManggalaData;
import com.servlet.employeemanggala.service.EmployeeManggalaService;
import com.servlet.invoicetype.entity.ParamInvTypeDropDown;
import com.servlet.invoicetype.service.InvoiceTypeService;
import com.servlet.parameter.service.ParameterService;
import com.servlet.parametermanggala.service.ParameterManggalaService;
import com.servlet.paymenttype.service.PaymentTypeService;
import com.servlet.pengluarankasbank.mapper.GetDataReportKasBankMapper;
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
import com.servlet.pengluarankasbank.mapper.GetListPengeluaranKasBank;
import com.servlet.pengluarankasbank.mapper.GetListPengeluaranKasBankData;
import com.servlet.pengluarankasbank.mapper.GetPengeluaranKasBankData;
import com.servlet.pengluarankasbank.mapper.GetPengeluaranKasBankJoinTable;
import com.servlet.pengluarankasbank.mapper.GetTotalAmount;
import com.servlet.pengluarankasbank.repo.DetailPengeluaranKasBankRepo;
import com.servlet.pengluarankasbank.repo.PengeluaranKasBankRepo;
import com.servlet.pengluarankasbank.service.PengeluaranKasBankService;
import com.servlet.report.entity.EntityHelperKasBank;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.user.entity.UserPermissionData;
import com.servlet.user.service.UserAppsService;
import com.servlet.vendor.entity.DetailVendorBankData;
import com.servlet.vendor.service.VendorService;
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
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private PaymentTypeService paymentTypeService;
	@Autowired
	private HistoryAssetMappingRepo historyAssetMappingRepo;
	@Autowired
	private AssetRepo assetRepo;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private EmployeeManggalaService employeeManggalaService;
	@Autowired
	private UserAppsService userAppsService;
	
	private final String PAYMENTTO_EMPLOYEE = "EMPLOYEE";
	private final String PAYMENTTO_CUSTOMER = "CUSTOMER";
	private final String PAYMENTTO_VENDOR = "VENDOR";
	
	
	@Override
	public List<PengeluaranKasBankData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false order by data.nodocument desc ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPengeluaranKasBankData(), queryParameters);
	}

	@Override
	public List<PengeluaranKasBankData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetListPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false order by data.nodocument desc ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetListPengeluaranKasBankData(), queryParameters);
	}
	
	@Override
	public List<PengeluaranKasBankData> getListActiveCheckBank(Long idcompany, Long idbranch,Long iduser) {
		// TODO Auto-generated method stub
		boolean checkFinanceJunior = checkFinanceJunior(iduser);
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetListPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		if(checkFinanceJunior) {
			sqlBuilder.append(" and bank.showfinancejunior = true ");
		}
		sqlBuilder.append(" order by data.nodocument desc ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetListPengeluaranKasBankData(), queryParameters);
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
			if(val.getPaymentto().equals(PAYMENTTO_VENDOR)) {
				val.setListBank(getListBankVendor(val.getIdvendor(),idcompany,idbranch));
			}else if(val.getPaymentto().equals(PAYMENTTO_EMPLOYEE)) {
				val.setListBank(getEmpAccBankById(idcompany,idbranch,val.getIdemployee()));
			}
			return val;
		}
		return null;
	}
	
	@Override
	public PengeluaranKasBankData getByIdCheckBank(Long idcompany, Long idbranch, Long id, Long iduser) {
		// TODO Auto-generated method stub
		boolean checkFinanceJunior = checkFinanceJunior(iduser);
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPengeluaranKasBankJoinTable().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		if(checkFinanceJunior) {
			sqlBuilder.append(" and bank.showfinancejunior = true ");
		}
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PengeluaranKasBankData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPengeluaranKasBankJoinTable(), queryParameters);
		if(list != null && list.size() > 0) {
			PengeluaranKasBankData val = list.get(0);
			val.setDetails(getDetails(idcompany,idbranch,id));
			val.setDisablededitordelete(compareDateDocWithParameter(idcompany,idbranch,val.getPaymentdate()));
			if(val.getPaymentto().equals(PAYMENTTO_VENDOR)) {
				val.setListBank(getListBankVendor(val.getIdvendor(),idcompany,idbranch));
			}else if(val.getPaymentto().equals(PAYMENTTO_EMPLOYEE)) {
				val.setListBank(getEmpAccBankById(idcompany,idbranch,val.getIdemployee()));
			}
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
		
		String docNumber = "";
		if(validations.size() == 0) {
			docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PENGELUARANKASBANK, ts);
			if(docNumber.equals("")) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
				validations.add(msg);
			}
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
				
				if(body.getPaymentto().equals(PAYMENTTO_CUSTOMER)) {
					table.setIdcustomer(body.getIdcustomer());
					table.setIdemployee(null);
					table.setIdvendor(null);
				}else if(body.getPaymentto().equals(PAYMENTTO_EMPLOYEE)) {
					table.setIdcustomer(null);
					table.setIdemployee(body.getIdemployee());
					table.setIdvendor(null);
				}else if(body.getPaymentto().equals(PAYMENTTO_VENDOR)) {
					table.setIdcustomer(null);
					table.setIdemployee(null);
					table.setIdvendor(body.getIdvendor());
				}
				table.setIdpaymenttype(body.getIdpaymenttype());
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
					putDetail(body.getDetails(), idcompany, idbranch, idsave, "ADD",iduser);
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
			
			if(validations.size() == 0 ) {
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
					
					if(body.getPaymentto().equals(PAYMENTTO_CUSTOMER)) {
						table.setIdcustomer(body.getIdcustomer());
						table.setIdemployee(null);
						table.setIdvendor(null);
					}else if(body.getPaymentto().equals(PAYMENTTO_EMPLOYEE)) {
						table.setIdcustomer(null);
						table.setIdemployee(body.getIdemployee());
						table.setIdvendor(null);
					}else if(body.getPaymentto().equals(PAYMENTTO_VENDOR)) {
						table.setIdcustomer(null);
						table.setIdemployee(null);
						table.setIdvendor(body.getIdvendor());
					}
					table.setIdpaymenttype(body.getIdpaymenttype());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					
					idsave = repository.saveAndFlush(table).getId();
					
					putDetail(body.getDetails(), idcompany, idbranch, idsave, "EDIT",iduser);
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
					
					List<HistoryAssetMappingData> listHistory = assetService.getListHistoryMappingForPengeluaranKasBank(idcompany,idbranch,id,idsave);
					if(listHistory != null && listHistory.size() > 0) {
						HistoryAssetMappingData history = listHistory.get(0);
						HistoryAssetMapping tablehis = historyAssetMappingRepo.getById(history.getId());
						tablehis.setIsdelete(true);
						historyAssetMappingRepo.saveAndFlush(tablehis);
						
					}
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
	public PengeluaranKasBankTemplate getTemplate(Long idcompany, Long idbranch, Long iduser) {
		// TODO Auto-generated method stub
		return setTemplate(idcompany,idbranch, iduser);
	}

	@Override
	public PengeluaranKasBankData getByIdWithTemplate(Long idcompany, Long idbranch, Long id, Long iduser) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PengeluaranKasBankData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPengeluaranKasBankData(), queryParameters);
		if(list != null && list.size() > 0) {
			PengeluaranKasBankData val = list.get(0);
			val.setDetails(getDetails(idcompany,idbranch,id));
			val.setTemplate(setTemplate(idcompany,idbranch, iduser));
						
			if(val.getPaymentto().equals(PAYMENTTO_VENDOR)) {
				val.setListBank(getListBankVendor(val.getIdvendor(),idcompany,idbranch));
			}else if(val.getPaymentto().equals(PAYMENTTO_EMPLOYEE)) {
				val.setListBank(getEmpAccBankById(idcompany,idbranch,val.getIdemployee()));
			}
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
	
	private PengeluaranKasBankTemplate setTemplate(Long idcompany, Long idbranch, Long iduser) {
		boolean checkFinanceJunior = checkFinanceJunior(iduser);
		
		ParamDropDownWO paramwo = new ParamDropDownWO();
		paramwo.setStatus("OPEN");
		paramwo.setMenu("PENGELUARAN_KAS_BANK");
		
		ParamInvTypeDropDown paramInvType = new ParamInvTypeDropDown();
		paramInvType.setInvoicetype("REIMBURSEMENT");
		paramInvType.setMenu("PENGELUARAN_KAS_BANK");
		
		PengeluaranKasBankTemplate template = new PengeluaranKasBankTemplate();
		template.setBankOptions(bankAccountService.getListActiveBankAccountCheckFinanceJunior(idcompany, idbranch, checkFinanceJunior));
		template.setCoaOptions(coaService.getListActiveCOA(idcompany, idbranch));
		template.setWoOptions(workOrderService.getListDropDownByParam(idcompany, idbranch, paramwo));
		template.setInvoiceItemOptions(invoiceTypeService.getListDropDownInvoiceType(idcompany, idbranch, paramInvType));
		template.setAssetOptions(assetService.getListAssetForPengeluaran(idcompany, idbranch));
		template.setPaymenttypeOptions(parameterService.getListParameterByGrup("PAYMENTITEM_TYPE"));
		template.setAssetSparePartOptions(assetService.getListAssetSparePartForPengeluaran(idcompany, idbranch));
		template.setSpareparttypeOptions(parameterService.getListParameterByGrup("JENIS_SPAREPART"));
		template.setPaymentItemOptions(paymentTypeService.getListActive(idcompany, idbranch));
		return template;
	}
	
	private String putDetail(BodyDetailPengeluaranKasBank[] details,Long idcompany, Long idbranch,long idsave,String action,long iduser) {
		//detailPengeluaranKasBankRepo
//		if(action.equals("EDIT")) {
//			detailPengeluaranKasBankRepo.deleteAllDetail(idsave, idcompany, idbranch);
//		}
		Timestamp ts = new Timestamp(new Date().getTime());
		if(details != null) {
			if(details.length > 0) {
				long count = 1;
//				for(int i=0; i < details.length; i++) {
					BodyDetailPengeluaranKasBank detail = details[0];//details[i];
					
					DetailPengeluaranKasBankPK pk = new DetailPengeluaranKasBankPK();
					pk.setCounter(count);
					pk.setIdcompany(idcompany);
					pk.setIdbranch(idbranch);
					pk.setIdpengeluarankasbank(idsave);
					
					DetailPengeluaranKasBank detailPengeluaranKasBank = new DetailPengeluaranKasBank();
					List<HistoryAssetMappingData> listHistory = new ArrayList<HistoryAssetMappingData>();
					if(action.equals("EDIT")) {
						detailPengeluaranKasBank = detailPengeluaranKasBankRepo.getById(pk);
						Long idasset = null;
						if(detailPengeluaranKasBank != null ) {
							if(detailPengeluaranKasBank.getIdasset() != null && detailPengeluaranKasBank.getIdasset() != 0) {
								idasset = detailPengeluaranKasBank.getIdasset();
							}
						}
						listHistory = assetService.getListHistoryMappingForPengeluaranKasBank(idcompany,idbranch,idasset,idsave);
					}
					
					detailPengeluaranKasBank.setDetailPengeluaranKasBankPK(pk);
					detailPengeluaranKasBank.setIdcoa(detail.getIdcoa());
					detailPengeluaranKasBank.setCatatan(detail.getCatatan());
					detailPengeluaranKasBank.setAmount(detail.getAmount());
					detailPengeluaranKasBank.setIdasset(detail.getIdasset());
					detailPengeluaranKasBank.setIdinvoiceitem(detail.getIdinvoiceitem());
					detailPengeluaranKasBank.setIdpaymentitem(detail.getIdpaymentitem());
					detailPengeluaranKasBank.setSparepartassettype(detail.getSparepartassettype());
					detailPengeluaranKasBank.setIdassetsparepart(detail.getIdassetsparepart());
					
					detailPengeluaranKasBankRepo.saveAndFlush(detailPengeluaranKasBank);
					
					Long idassetSparepart = null;
					if(detail.getIdassetsparepart() == null) {
						idassetSparepart = 0L;
					}else {
						idassetSparepart = detail.getIdassetsparepart();
					}
					String type = "ARMADA";
					if(idassetSparepart != null && idassetSparepart != 0) {
						type = "SPAREPART";
					}
					
					boolean isDelete = false;
					if(detail.getIdasset() == null) {
						isDelete = true;
					}
					
					if(listHistory != null && listHistory.size() > 0) {
						HistoryAssetMappingData history = listHistory.get(0);
						if(isDelete) {
							historyAssetMappingRepo.deleteById(history.getId());
						}else {
							
							HistoryAssetMapping table = historyAssetMappingRepo.getById(history.getId());
							
							table.setIdasset(detail.getIdasset());
							if(idassetSparepart == 0L) {
								table.setAfter(detail.getIdasset());
							}else {
								table.setAfter(idassetSparepart);
							}
							
							table.setType(type);
							if(history.getIdasset() != detail.getIdasset() || history.getAfter() != idassetSparepart) {
								table.setTanggal(ts);
							}
							table.setIduser(iduser);
							
							Optional<Asset> optAsset = assetRepo.findById(detail.getIdasset());
							Long Idassetkepala = null;
							if(optAsset.isPresent()) {
								Asset asset = optAsset.get();
								if(asset.getAssettype().equals("KEPALA")) {
									Idassetkepala = detail.getIdasset();
//									table.setIdassetkepala(detail.getIdasset());
								}else if(asset.getAssettype().equals("BUNTUT")) {
								List<AssetMappingData>	list = assetService.getListAssetMappingByIdAsset(idcompany,idbranch,detail.getIdasset());
									if(list != null && list.size() > 0) {
										AssetMappingData detList = list.get(0);
										Idassetkepala = detList.getIdasset();
//										table.setIdassetkepala(detList.getIdasset());
									}
								}
							}
							
							table.setIdassetkepala(Idassetkepala);
							historyAssetMappingRepo.saveAndFlush(table);
						}
						
						
					}else {
						if(!isDelete) {
							HistoryAssetMapping table = new HistoryAssetMapping();
							table.setIdasset(detail.getIdasset());
							table.setIdcompany(idcompany);
							table.setIdbranch(idbranch);
							table.setIduser(iduser);
							table.setBefore(0L);
							table.setAfter(idassetSparepart);
							table.setType(type);
							table.setTanggal(ts);
							table.setIdpengeluarankasbank(idsave);
							table.setIsdelete(false);
							
							Optional<Asset> optAsset = assetRepo.findById(detail.getIdasset());
							Long Idassetkepala = null;
							if(optAsset.isPresent()) {
								Asset asset = optAsset.get();
								if(asset.getAssettype().equals("KEPALA")) {
									Idassetkepala = detail.getIdasset();
//									table.setIdassetkepala(detail.getIdasset());
								}else if(asset.getAssettype().equals("BUNTUT")) {
								List<AssetMappingData>	list = assetService.getListAssetMappingByIdAsset(idcompany,idbranch,detail.getIdasset());
									if(list != null && list.size() > 0) {
										AssetMappingData detList = list.get(0);
										Idassetkepala = detList.getIdasset();
//										table.setIdassetkepala(detList.getIdasset());
									}
								}
							}
							
							table.setIdassetkepala(Idassetkepala);
							
							historyAssetMappingRepo.saveAndFlush(table);
						}
						
					}
					count++;
//				}
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
	
	private List<DetailPengeluaranKasBankData> getListDetailByIdWONotJoin(Long idcompany, Long idbranch, Long idWO, boolean isReimbursement) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? ");
		sqlBuilder.append(" and data.idpengeluarankasbank in (select m.id from m_pengeluaran_kas_bank as m where m.isactive = true  and m.isdelete = false and m.idwo = "+idWO+" ) ");
		if(isReimbursement){
			sqlBuilder.append(" and (data.idinvoiceitem != 0 or data.idinvoiceitem notnull) and (data.idinvoice isnull or data.idinvoice > 0) ");
		}
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPengeluaranKasBankData(), queryParameters);
	}

	@Override
	public PengeluaranHeaderAndDetail getListByIdWo(Long idcompany, Long idbranch, Long idWO, boolean isReimbursement) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.idwo = ? and data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		if(isReimbursement){
			sqlBuilder.append(" and data.id in (select idpengeluarankasbank from detail_pengeluaran_kas_bank as detail where (detail.idinvoiceitem != 0 or detail.idinvoiceitem notnull) and (detail.idinvoice isnull or detail.idinvoice > 0) ) ");
		}
		final Object[] queryParameters = new Object[] {idWO, idcompany,idbranch};
		List<PengeluaranKasBankData> headers = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPengeluaranKasBankData(), queryParameters);
		
		List<DetailPengeluaranKasBankData> details = getListDetailByIdWONotJoin(idcompany, idbranch, idWO,isReimbursement);
		
		PengeluaranHeaderAndDetail data = new PengeluaranHeaderAndDetail();
		data.setHeaders(headers);
		data.setDetails(details);
		
		return data;
	}

	@Override
	public Double summaryAmountPengeluaranForSummaryKegiatanTruck(Long idcompany, Long idbranch, Long idwo, Long idcustomer, Long idemployee, Long idinvoiceitem, Long idpaymentitem,
			Long idasset) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetTotalAmount().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? ");
			final StringBuilder sqlBuilderMaster = new StringBuilder(" and data.idpengeluarankasbank in (select pkb.id from m_pengeluaran_kas_bank as pkb where pkb.isactive = true and pkb.isdelete = false and pkb.idwo = "+idwo+" ");
			sqlBuilderMaster.append(" and pkb.idemployee = "+idemployee+" ");
//			if(idemployee != null) {
//				sqlBuilderMaster.append(" and pkb.idemployee = "+idemployee+" ");
//			}else {
//				sqlBuilderMaster.append(" and pkb.idcustomer = "+idcustomer+" ");
//			}
			sqlBuilderMaster.append(" ) ");
		
			if(idasset != null) {
				sqlBuilder.append(" and data.idasset = "+idasset+" ");
			}
			if(idinvoiceitem != null) {
				sqlBuilder.append(" and data.idinvoiceitem = "+idinvoiceitem+" ");
			}
			if(idpaymentitem != null) {
				sqlBuilder.append(" and data.idpaymentitem = "+idpaymentitem+" ");
			}
			sqlBuilder.append(sqlBuilderMaster.toString());
			
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetTotalAmount(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return 0.0;
	}

	@Override
	public List<PengeluaranKasBankData> getListAllJoin(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetListPengeluaranKasBank().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false order by data.nodocument desc ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetListPengeluaranKasBank(), queryParameters);
	}

	@Override
	public List<DetailVendorBankData> getListBankVendor(Long id, Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return vendorService.getListBankVendor(id, idcompany, idbranch);
	}

	@Override
	public List<DetailVendorBankData> getEmpAccBankById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		List<DetailVendorBankData> list = new ArrayList<DetailVendorBankData>();
		EmployeManggalaData emp = employeeManggalaService.getAccBankById(idcompany, idbranch, id);
		if(emp != null) {
			DetailVendorBankData bank = new DetailVendorBankData();
			bank.setAtasnama(emp.getAtasnama());
			bank.setBank(emp.getNamabank());
			bank.setNorek(emp.getNorekening());
			list.add(bank);
		}
		return list;
	}

	@Override
	public List<EntityHelperKasBank> getDataReportKasBankPengeluaran(Long idcompany, Long idbranch, java.sql.Date fromdate,
			java.sql.Date todate, Long idbank) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataReportKasBankMapper().schema());
		sqlBuilder.append(" where ");
//		sqlBuilder.append(" (mpenerimaan.idcompany = ? and mpenerimaan.idbranch = ?  and mpenerimaan.isactive = true and mpenerimaan.isdelete = false and mpenerimaan.idbank = "+idbank+" and mpenerimaan.receivedate >= '"+fromdate+"'  and mpenerimaan.receivedate <= '"+todate+"' ) ");
//		sqlBuilder.append(" or ");
		sqlBuilder.append(" (mpengeluaran.idcompany = ? and mpengeluaran.idbranch = ?  and mpengeluaran.isactive = true and mpengeluaran.isdelete = false and mpengeluaran.idbank = "+idbank+" and mpengeluaran.paymentdate >= '"+fromdate+"'  and mpengeluaran.paymentdate <= '"+todate+"' ) ");
//		sqlBuilder.append(" order by mpenerimaan.receivedate , mpengeluaran.paymentdate asc ");
		
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataReportKasBankMapper(), queryParameters);
	}

	@Override
	public List<DetailPengeluaranKasBankData> getListDetailByIdInvoice(Long idcompany, Long idbranch, Long idinvoice) {
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPengeluaranKasBankData().schema());
		sqlBuilder.append(" where data.idinvoice = ? ");
		final Object[] queryParameters = new Object[] {idinvoice};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPengeluaranKasBankData(), queryParameters);
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
