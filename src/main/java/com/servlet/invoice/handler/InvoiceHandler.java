package com.servlet.invoice.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.customermanggala.service.CustomerManggalaService;
import com.servlet.invoice.entity.BodyDetailInvoicePrice;
import com.servlet.invoice.entity.BodyInvoice;
import com.servlet.invoice.entity.DetailInvoicePrice;
import com.servlet.invoice.entity.DetailInvoicePriceData;
import com.servlet.invoice.entity.DetailInvoicePricePK;
import com.servlet.invoice.entity.Invoice;
import com.servlet.invoice.entity.InvoiceData;
import com.servlet.invoice.entity.InvoiceTemplate;
import com.servlet.invoice.mapper.GetDetailInvoicePriceJoinTableData;
import com.servlet.invoice.mapper.GetInvoiceData;
import com.servlet.invoice.mapper.GetInvoiceDataJoinTable;
import com.servlet.invoice.mapper.GetInvoiceJoinCustomerData;
import com.servlet.invoice.repo.DetailInvoicePriceRepo;
import com.servlet.invoice.repo.InvoiceRepo;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.invoicetype.service.InvoiceTypeService;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankData;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankPK;
import com.servlet.penerimaankasbank.repo.DetailPenerimaanKasBankRepo;
import com.servlet.penerimaankasbank.service.PenerimaanKasBankService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.suratjalan.service.SuratJalanService;
import com.servlet.workorder.service.WorkOrderService;

@Service
public class InvoiceHandler implements InvoiceService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private InvoiceRepo repository;
	@Autowired
	private CustomerManggalaService customerManggalaService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private SuratJalanService suratJalanService;
	@Autowired
	private RunningNumberService runningNumberService;
	@Autowired
	private InvoiceTypeService invoiceTypeService;
	@Autowired
	private DetailInvoicePriceRepo detailInvoicePriceRepo;
	@Autowired
	private PenerimaanKasBankService penerimaanKasBankService;
	@Autowired
	private DetailPenerimaanKasBankRepo detailPenerimaanKasBankRepo;
	
	@Override
	public List<InvoiceData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceJoinCustomerData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false order by data.id desc ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceJoinCustomerData(), queryParameters);
	}

	@Override
	public List<InvoiceData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceData(), queryParameters);
	}

	@Override
	public InvoiceData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceDataJoinTable().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<InvoiceData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceDataJoinTable(), queryParameters);
		if(list != null && list.size() > 0) {
			InvoiceData val = list.get(0);
			val.setDetailsprice(getDetailsInvoicePrice(idcompany, idbranch, id));
			val.setListpenerimaan(penerimaanKasBankService.getListByDetailIdInvoice(idcompany, idbranch, id));
			val.setDetailspenerimaan(penerimaanKasBankService.getListDetailByIdInvoice(idcompany, idbranch, id));
//			val.setTemplate(getTemplate(idcompany, idbranch));
			return val;
		}
		return null;
	}

	@Override
	public ReturnData saveInvoice(Long idcompany, Long idbranch, Long iduser, BodyInvoice body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		List<ValidationDataMessage> validationsCheckData = checkData(idcompany,idbranch,body,"ADD");
		long idsave = 0;
		validations.addAll(validationsCheckData);
		Timestamp ts = new Timestamp(new Date().getTime());
		String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_INVOICE, ts);
		if(docNumber.equals("")) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
			validations.add(msg);
		}
		if(validations.size() == 0 && validationsCheckData.size() == 0) {
			try{
				try {
					Invoice table = new Invoice();
					table.setIdcompany(idcompany);
					table.setIdbranch(idbranch);
					table.setNodocument(docNumber);
					table.setTanggal(new java.sql.Date(body.getTanggal()));
					table.setIdcustomer(body.getIdcustomer());
					table.setRefno(body.getRefno());
					table.setDeliveredto(body.getDeliveredto());
					table.setDeliverydate(new java.sql.Date(body.getDeliverydate()));
					table.setIdwo(body.getIdwo());
					table.setIdsuratjalan(body.getIdsuratjalan());
					table.setIdinvoicetype(body.getIdinvoicetype());
					table.setTotalinvoice(body.getTotalinvoice());
					table.setDiskonnota(body.getDiskonnota());
					table.setIsactive(body.isIsactive());
	
					table.setIsdelete(false);
					table.setCreatedby(iduser.toString());
					table.setCreateddate(ts);
					
					idsave = repository.saveAndFlush(table).getId();
				}catch (Exception e) {
					// TODO: handle exception
					runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_INVOICE);
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
					validations.add(msg);
				}
				if(idsave > 0) {
					putDetail(body.getDetailsprice(), idcompany, idbranch, idsave, "ADD");
					updateToPenerimaanKasBank(idcompany,idbranch,idsave,body.getIdwo(),body.getTotalinvoice(),"ADD");
				}
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
	public ReturnData updateInvoice(Long idcompany, Long idbranch, Long iduser, Long id, BodyInvoice body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		Timestamp ts = new Timestamp(new Date().getTime());
		List<ValidationDataMessage> validationsCheckData = checkData(idcompany,idbranch,body,"EDIT");
		long idsave = 0;
		validations.addAll(validationsCheckData);
		
		InvoiceData value = checkById(idcompany,idbranch,id);
		List<DetailPenerimaanKasBankData> checkAlreadyPayment = penerimaanKasBankService.getListDetailByIdInvoice(idcompany, idbranch, id);
		if(checkAlreadyPayment != null && checkAlreadyPayment.size() > 0) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_INVOICE_ALREADY_PAYMENT_EDIT,"Tidak Bisa Edit, Sudah Terjadi Pembayaran");
			validations.add(msg);
		}
		if(validations.size() == 0 && validationsCheckData.size() == 0 && value != null) {
			try{
				Invoice table = repository.getById(id);
				table.setTanggal(new java.sql.Date(body.getTanggal()));
				table.setIdcustomer(body.getIdcustomer());
				table.setRefno(body.getRefno());
				table.setDeliveredto(body.getDeliveredto());
				table.setDeliverydate(new java.sql.Date(body.getDeliverydate()));
				table.setIdwo(body.getIdwo());
				table.setIdsuratjalan(body.getIdsuratjalan());
				table.setIdinvoicetype(body.getIdinvoicetype());
				table.setDiskonnota(body.getDiskonnota());
				table.setTotalinvoice(body.getTotalinvoice());
				table.setIsactive(body.isIsactive());
				table.setUpdateby(iduser.toString());
				table.setUpdatedate(ts);
				idsave = repository.saveAndFlush(table).getId();
				
				putDetail(body.getDetailsprice(), idcompany, idbranch, idsave, "EDIT");
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
	public ReturnData deleteInvoice(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		InvoiceData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			List<DetailPenerimaanKasBankData> checkAlreadyPayment = penerimaanKasBankService.getListDetailByIdInvoice(idcompany, idbranch, id);
			if(checkAlreadyPayment != null && checkAlreadyPayment.size() > 0) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_INVOICE_ALREADY_PAYMENT_DELETE,"Tidak Bisa Delete, Sudah Terjadi Pembayaran");
				validations.add(msg);
			}
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					Invoice table = repository.getById(id);
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
	public InvoiceTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return setTemplate(idcompany,idbranch);
	}

	private List<ValidationDataMessage> checkData(Long idcompany, Long idbranch, BodyInvoice body,String action){
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		if(body.getIdcustomer() != null) {
			HashMap<String, Object> result = customerManggalaService.checkCustomerById(idcompany, idbranch, body.getIdcustomer());
			if( result.get("ISFOUND") != null && !(boolean)result.get("ISFOUND") ) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_CUSTOMER_NOT_FOUND,"Customer Not Found");
				validations.add(msg);
			}else if( result.get("ISACTIVE") != null && !(boolean)result.get("ISACTIVE") ) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_CUSTOMER_NOT_ACTIVE,"Customer Not Active");
				validations.add(msg);
			}
		}
		if(body.getIdwo() != null) {
			HashMap<String, Object> result = workOrderService.checkWO(idcompany, idbranch, body.getIdwo());
			if( result.get("ISFOUND") != null && !(boolean)result.get("ISFOUND") ) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_WO_NOT_FOUND,"WO Not Found");
				validations.add(msg);
			}else if( result.get("ISACTIVE") != null && !(boolean)result.get("ISACTIVE") ) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_WO_NOT_ACTIVE,"WO Not Active");
				validations.add(msg);
			}else if( result.get("ISSTATUSAVAILABLE") != null && !(boolean)result.get("ISSTATUSAVAILABLE") ) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_WO_NOT_AVAILABLE,"WO Status Not Available");
				validations.add(msg);
			}
		}
		
		if(body.getIdsuratjalan() != null) {
			HashMap<String, Object> result = suratJalanService.checkSuratjalan(idcompany, idbranch, body.getIdsuratjalan());
			if( result.get("ISFOUND") != null && !(boolean)result.get("ISFOUND") ) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_SJ_NOT_FOUND,"Surat Jalan Not Found");
				validations.add(msg);
			}else if( result.get("ISACTIVE") != null && !(boolean)result.get("ISACTIVE") ) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_SJ_NOT_ACTIVE,"Surat Jalan Not Active");
				validations.add(msg);
			}else if( result.get("ISSTATUSAVAILABLE") != null && !(boolean)result.get("ISSTATUSAVAILABLE") ) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_SJ_NOT_AVAILABLE,"Surat Jalan Status Not Available");
				validations.add(msg);
			}
		}
		return validations;
	}
	
	private InvoiceData checkById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<InvoiceData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceData(), queryParameters);
		if(list != null && list.size() > 0) {
			InvoiceData val = list.get(0);
			return val;
		}
		return null;
	}
	
	private InvoiceTemplate setTemplate(long idcompany, long idbranch) {
		InvoiceTemplate data = new InvoiceTemplate();
		data.setInvoiceTypeOptions(invoiceTypeService.getListActiveBankAccount(idcompany, idbranch));
		return data;
	}
	
	private String putDetail(BodyDetailInvoicePrice[] details,Long idcompany, Long idbranch,long idsave,String action) {
		if(action.equals("EDIT")) {
			detailInvoicePriceRepo.deleteAllDetail(idsave, idcompany, idbranch);
		}
		if(details != null) {
			if(details.length > 0) {
				long count = 1;
				for(int i=0; i < details.length; i++) {
					BodyDetailInvoicePrice detail = details[i];
					
					DetailInvoicePricePK pk = new DetailInvoicePricePK();
					pk.setCounter(count);
					pk.setIdinvoice(idsave);
					
					DetailInvoicePrice detailInvoicePrice = new DetailInvoicePrice();
					detailInvoicePrice.setDetailInvoicePricePK(pk);
					detailInvoicePrice.setIdcompany(idcompany);
					detailInvoicePrice.setIdbranch(idbranch);
					detailInvoicePrice.setIdwarehouse(detail.getIdwarehouse());
					detailInvoicePrice.setIdinvoicetype(detail.getIdinvoicetype());
					detailInvoicePrice.setJalur(detail.getJalur());
					detailInvoicePrice.setPrice(detail.getPrice());
					detailInvoicePrice.setIsmandatory(detail.getIsmandatory());
					detailInvoicePrice.setIdpricelist(detail.getIdpricelist());
					detailInvoicePrice.setQty(detail.getQty());
					detailInvoicePrice.setDiskon(detail.getDiskon());
					detailInvoicePrice.setSubtotal(detail.getSubtotal());
					detailInvoicePriceRepo.saveAndFlush(detailInvoicePrice);
					count++;
				}
			}
		}
		return "";
	}
	
	private String updateToPenerimaanKasBank(long idcompany,long idbranch,Long idsave,Long idwo,Double totalInvoice,String action) {
		if(idwo != null && totalInvoice != null) {
			if(idwo.intValue() > 0) {
				List<DetailPenerimaanKasBankData> list = penerimaanKasBankService.getListDetailByIdWO(idcompany, idbranch, idwo);
				if(list != null && list.size() > 0) {
					for(DetailPenerimaanKasBankData data : list) {
						if(totalInvoice.doubleValue() >= data.getAmount().doubleValue() || data.getIsdownpayment().equals("N")) {
							DetailPenerimaanKasBankPK pk = new DetailPenerimaanKasBankPK();
							pk.setIdcompany(idcompany);
							pk.setIdbranch(idbranch);
							pk.setCounter(data.getCounter());
							pk.setIdpenerimaankasbank(data.getIdpenerimaankasbank());
							
							DetailPenerimaanKasBank table = detailPenerimaanKasBankRepo.getById(pk);
							table.setIdinvoice(idsave);
							detailPenerimaanKasBankRepo.saveAndFlush(table);
							break;
						}
					}
				}
			}
		}
		return "";
	}
	
	
	private List<DetailInvoicePriceData> getDetailsInvoicePrice(Long idcompany, Long idbranch,Long idinvoice) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailInvoicePriceJoinTableData().schema());
		sqlBuilder.append(" where data.idinvoice = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idinvoice,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailInvoicePriceJoinTableData(), queryParameters);
	}

	@Override
	public InvoiceData getByIdWithTemplate(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInvoiceDataJoinTable().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<InvoiceData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetInvoiceDataJoinTable(), queryParameters);
		if(list != null && list.size() > 0) {
			InvoiceData val = list.get(0);
			val.setDetailsprice(getDetailsInvoicePrice(idcompany, idbranch, id));
			val.setTemplate(getTemplate(idcompany, idbranch));
			return val;
		}
		return null;
	}
}
