package com.servlet.workorder.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.servlet.customermanggala.entity.CustomerManggalaData;
import com.servlet.customermanggala.service.CustomerManggalaService;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.parameter.entity.ParameterData;
import com.servlet.parameter.service.ParameterService;
import com.servlet.partai.service.PartaiService;
import com.servlet.port.service.PortService;
import com.servlet.report.entity.ParamReportManggala;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ConstantReportName;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.upload.image.FileStorageService;
import com.servlet.upload.image.InfoFile;
import com.servlet.vendor.service.VendorService;
import com.servlet.workorder.entity.BodyDetailWorkOrder;
import com.servlet.workorder.entity.BodySearch;
import com.servlet.workorder.entity.BodyWorkOrder;
import com.servlet.workorder.entity.DetailWorkOrder;
import com.servlet.workorder.entity.DetailWorkOrderData;
import com.servlet.workorder.entity.DetailWorkOrderPK;
import com.servlet.workorder.entity.ListDocumentWorkOrder;
import com.servlet.workorder.entity.ListDocumentWorkOrderData;
import com.servlet.workorder.entity.ParamDropDownWO;
import com.servlet.workorder.entity.ParamWoReport;
import com.servlet.workorder.entity.WorkOrder;
import com.servlet.workorder.entity.WorkOrderData;
import com.servlet.workorder.entity.WorkOrderDropDownData;
import com.servlet.workorder.entity.WorkOrderTemplate;
import com.servlet.workorder.mapper.GetDetailWorkOrderForReportStatusInvoice;
import com.servlet.workorder.mapper.GetDetailWorkOrderJoinTable;
import com.servlet.workorder.mapper.GetDetailWorkOrderJoinTableWithSuratJalan;
import com.servlet.workorder.mapper.GetListDocumentWorkOrderData;
import com.servlet.workorder.mapper.GetWorkOrderDropdownData;
import com.servlet.workorder.mapper.GetWorkOrderJoinCustomerData;
import com.servlet.workorder.mapper.GetWorkOrderJoinTableData;
import com.servlet.workorder.mapper.GetWorkOrderNotJoinTableData;
import com.servlet.workorder.repo.DetailWorkOrderRepo;
import com.servlet.workorder.repo.DocumentWorkOrderRepo;
import com.servlet.workorder.repo.WorkOrderRepo;
import com.servlet.workorder.service.WorkOrderService;

@Service
public class WorkOrderHandler implements WorkOrderService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private WorkOrderRepo repository;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private PortService portService;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private PartaiService partaiService;
	@Autowired
	private RunningNumberService runningNumberService;
	@Autowired
	private DetailWorkOrderRepo detailWorkOrderRepo;
	@Autowired
	private CustomerManggalaService customerManggalaService;
	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private DocumentWorkOrderRepo documentWorkOrderRepo ;
	@Autowired
	private InvoiceService invoiceService;
	
	@Override
	public List<WorkOrderData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderJoinCustomerData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		sqlBuilder.append("order by data.id desc ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderJoinCustomerData(), queryParameters);
	}

	@Override
	public List<WorkOrderData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderNotJoinTableData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderNotJoinTableData(), queryParameters);
	}
	
	@Override
	public WorkOrderData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderJoinTableData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<WorkOrderData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderJoinTableData(), queryParameters);
		if(list != null && list.size() > 0) {
			WorkOrderData val = list.get(0);
			val.setDetails(getListDetailWorkOrder(id, idcompany, idbranch));
			val.setDocuments(getListDocument(idcompany,idbranch,id));
			return val;
		}
		return null;
	}
	
	@Override
	public WorkOrderData getByIdForEdit(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderNotJoinTableData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<WorkOrderData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderNotJoinTableData(), queryParameters);
		if(list != null && list.size() > 0) {
			WorkOrderData val = list.get(0);
			String customername = "";
			CustomerManggalaData custData = customerManggalaService.getDataCustomerNotFilter(idcompany, idbranch, val.getIdcustomer());
			if(custData != null) {
				customername = custData.getCustomername();
			}
			val.setNamaCustomer(customername);
			val.setTemplates(setTemplate(idcompany, idbranch));
			val.setDetails(getListDetailWorkOrder(id, idcompany, idbranch));
			return val;
		}
		return null;
	}
	
	

	@Override
	public ReturnData saveWorkOrder(Long idcompany, Long idbranch, Long iduser, BodyWorkOrder body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		Date date = new Date();
//		java.sql.Date datesql = new java.sql.Date(date.getTime()); 
		Timestamp ts = new Timestamp(date.getTime());
		String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_WORKINGORDER, ts);
		if(docNumber.equals("")) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
			validations.add(msg);
		}
		
		if(validations.size() == 0) {
			try {
				try {
					WorkOrder table = new WorkOrder();
					table.setIdcompany(idcompany);
					table.setIdbranch(idbranch);
					table.setNodocument(docNumber);
					table.setTanggal(new java.sql.Date(body.getTanggal()));
					table.setIdcustomer(body.getIdcustomer());
					table.setStatus(body.getStatus());
					table.setJeniswo(body.getJeniswo());
					table.setDepo(body.getDepo());
					
					if(!body.getJeniswo().equals("TR")) {
						table.setNamacargo(body.getNamacargo());
						table.setModatransportasi(body.getModatransportasi());
						table.setEtd(new java.sql.Date(body.getEtd()));
						table.setEta(new java.sql.Date(body.getEta()));
						table.setPortasal(body.getPortasal());
						table.setPorttujuan(body.getPorttujuan());
						table.setJalur(body.getJalur());
						table.setNoaju(body.getNoaju());
						table.setNopen(body.getNopen());
						table.setTanggalnopen(body.getTanggalnopen().longValue() > 0? new java.sql.Date(body.getTanggalnopen()):null);
						table.setNobl(body.getNobl());
						table.setTanggalbl(body.getTanggalbl().longValue() > 0? new java.sql.Date(body.getTanggalbl()):null);
						table.setPelayaran(body.getPelayaran());
						table.setImportir(body.getImportir());
						table.setEksportir(body.getEksportir());
						table.setQq(body.getQq());
						table.setVoyagenumber(body.getVoyagenumber());
						table.setTanggalsppb_npe(body.getTanggalsppb_npe().longValue() > 0? new java.sql.Date(body.getTanggalsppb_npe()):null);
						
						
					}
					table.setIdvendordepo(body.getIdvendordepo());
					
					table.setInvoiceno("");
					table.setIsactive(body.isIsactive());
					table.setIsdelete(false);
					table.setCreatedby(iduser.toString());
					table.setCreateddate(ts);
					
					idsave = repository.saveAndFlush(table).getId();
				}catch (Exception e) {
					// TODO: handle exception
					runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_WORKINGORDER);
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
	public ReturnData updateWorkOrder(Long idcompany, Long idbranch, Long iduser, Long id, BodyWorkOrder body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		WorkOrderData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			if(value.getInvoiceno() != null && !value.getInvoiceno().equals("")) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_WORK_ORDER_INVOICENO_NOT_EMPTY,"Sudah Ada Invoice");
				validations.add(msg);
			}
		}
		
		if(validations.size() == 0) {
			try {
				if(value != null) {
					Timestamp ts = new Timestamp(new Date().getTime());
					WorkOrder table = repository.getById(id);
					table.setStatus(body.getStatus());
					table.setJeniswo(body.getJeniswo());
					table.setDepo(body.getDepo());
					
					if(body.getJeniswo().equals("TR")) {
						table.setNamacargo(null);
						table.setModatransportasi(null);
						table.setEtd(null);
						table.setEta(null);
						table.setPortasal(null);
						table.setPorttujuan(null);
						table.setJalur(null);
						table.setNoaju(null);
						table.setNopen(null);
						table.setTanggalnopen(null);
						table.setNobl(null);
						table.setTanggalbl(null);
						table.setPelayaran(null);
						table.setImportir(null);
						table.setEksportir(null);
						table.setQq(null);
						table.setVoyagenumber(null);
						table.setTanggalsppb_npe(null);
						
//						table.setIdvendordepo(null);
					}else {
						table.setNamacargo(body.getNamacargo());
						table.setModatransportasi(body.getModatransportasi());
						table.setEtd(new java.sql.Date(body.getEtd()));
						table.setEta(new java.sql.Date(body.getEta()));
						table.setPortasal(body.getPortasal());
						table.setPorttujuan(body.getPorttujuan());
						table.setJalur(body.getJalur());
						table.setNoaju(body.getNoaju());
						table.setNopen(body.getNopen());
						table.setTanggalnopen(body.getTanggalnopen().longValue() > 0 ?new java.sql.Date(body.getTanggalnopen()):null);
						table.setNobl(body.getNobl());
						table.setTanggalbl(body.getTanggalbl().longValue() > 0? new java.sql.Date(body.getTanggalbl()):null);
						table.setPelayaran(body.getPelayaran());
						table.setImportir(body.getImportir());
						table.setEksportir(body.getEksportir());
						table.setQq(body.getQq());
						table.setVoyagenumber(body.getVoyagenumber());
						table.setTanggalsppb_npe(body.getTanggalsppb_npe().longValue() > 0? new java.sql.Date(body.getTanggalsppb_npe()):null);
						
						
					}
					table.setStatus(body.getStatus());
					table.setIdvendordepo(body.getIdvendordepo());
					table.setIsactive(body.isIsactive());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
					
					putDetail(body.getDetails(), idcompany, idbranch, idsave, "EDIT");
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
	public ReturnData deleteWorkOrder(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		WorkOrderData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			if(value.getInvoiceno() != null && !value.getInvoiceno().equals("")) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_WORK_ORDER_INVOICENO_NOT_EMPTY,"Sudah Ada Invoice");
				validations.add(msg);
			}
			
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					WorkOrder table = repository.getById(id);
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
	public WorkOrderTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return setTemplate(idcompany,idbranch);
	}

	private WorkOrderTemplate setTemplate(long idcompany, long idbranch) {
		WorkOrderTemplate template = new WorkOrderTemplate();
		template.setWoTypeOptions(parameterService.getListParameterByGrup("WO_TYPE"));
		template.setModaTransportasiOptions(parameterService.getListParameterByGrup("JALUR"));
		template.setJalurOptions(parameterService.getListParameterByGrup("WARNA_JALUR"));
		template.setPortOptions(portService.getListActive(idcompany, idbranch));
		template.setVendorOptions(vendorService.getListActive(idcompany, idbranch));
		template.setPartaiOptions(partaiService.getListActive(idcompany, idbranch));
		return template;
	}
	
	private WorkOrderData checkById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderNotJoinTableData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<WorkOrderData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderNotJoinTableData(), queryParameters);
		if(list != null && list.size() > 0) {
			WorkOrderData val = list.get(0);
			return val;
		}
		return null;
	}
	
	private String putDetail(BodyDetailWorkOrder[] details,Long idcompany, Long idbranch,long idsave,String action) {
		if(action.equals("EDIT")) {
			detailWorkOrderRepo.deleteAllDetail(idsave, idcompany, idbranch);
		}
		if(details != null) {
			if(details.length > 0) {
				for(int i=0; i < details.length; i++) {
					BodyDetailWorkOrder detail = details[i];
					
					DetailWorkOrderPK detailWorkOrderPK = new DetailWorkOrderPK();
					detailWorkOrderPK.setIdcompany(idcompany);
					detailWorkOrderPK.setIdbranch(idbranch);
					detailWorkOrderPK.setIdworkorder(idsave);
					detailWorkOrderPK.setIdpartai(detail.getIdpartai());
					String noContainer = detail.getNocontainer();
					if(detail.getNocontainer().equals("")) {
						noContainer = "NC-NODATA-"+idsave+"-"+detail.getIdpartai()+"-"+i;
					}
					detailWorkOrderPK.setNocontainer(noContainer);
					
					String noSeal = detail.getNoseal();
					if(detail.getNoseal().equals("")) {
						noSeal = "NS-NODATA-"+idsave+"-"+detail.getIdpartai()+"-"+i;
					}
					
					detailWorkOrderPK.setNoseal(noSeal);
					
					DetailWorkOrder detailWorkOrder = new DetailWorkOrder();
					detailWorkOrder.setDetailWorkOrderPK(detailWorkOrderPK);
					detailWorkOrder.setBarang(detail.getBarang());
					detailWorkOrder.setJumlahkg(detail.getJumlahkg());
					detailWorkOrder.setJumlahkoli(detail.getJumlahkoli());
					
					detailWorkOrderRepo.saveAndFlush(detailWorkOrder);
				}
			}
		}
		return "";
	}
	
	private List<DetailWorkOrderData> getListDetailWorkOrder(Long idworkorder,Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailWorkOrderJoinTable().schema());
		sqlBuilder.append(" where data.idworkorder = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idworkorder,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailWorkOrderJoinTable(), queryParameters);
	}

	@Override
	public List<WorkOrderDropDownData> getListDropDown(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderDropdownData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false and data.jeniswo != 'JS' and data.status = 'OPEN' order by data.nodocument ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderDropdownData(), queryParameters);
	}

	@Override
	public List<DetailWorkOrderData> getListContainerByIdWorkOrder(Long idcompany, Long idbranch, Long idworkorder,String nocaontainer) {
		// TODO Auto-generated method stub
		WorkOrderData data = checkById(idcompany, idbranch, idworkorder);
		if(data != null) {
			if(data.isIsactive()) {
				return getListDetailWorkOrderForSuratJalan(idcompany,idbranch,idworkorder,nocaontainer);
			}
		}
		return new ArrayList<DetailWorkOrderData>();
	}
	
	private List<DetailWorkOrderData> getListDetailWorkOrderForSuratJalan(Long idcompany, Long idbranch,Long idworkorder,String nocaontainer) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailWorkOrderJoinTable().schema());
		sqlBuilder.append(" where data.idworkorder = ? and data.idcompany = ? and data.idbranch = ? ");
		sqlBuilder.append(" and data.nocontainer not in (select sj.nocantainer from t_surat_jalan as sj where sj.status in ('OPEN_SJ') and sj.isdelete = false and sj.nocantainer != '"+nocaontainer+"' ) ");
		
		final Object[] queryParameters = new Object[] {idworkorder,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailWorkOrderJoinTable(), queryParameters);
	}
	
	private List<ListDocumentWorkOrderData> getListDocument(Long idcompany, Long idbranch,Long idworkorder) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetListDocumentWorkOrderData().schema());
		sqlBuilder.append(" where data.idworkorder = ? and data.idcompany = ? and data.idbranch = ?  ");
		sqlBuilder.append("order by data.id desc ");
		final Object[] queryParameters = new Object[] {idworkorder,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetListDocumentWorkOrderData(), queryParameters);
	}
	
	private ListDocumentWorkOrderData getDocumentById(Long idcompany, Long idbranch,Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetListDocumentWorkOrderData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ?  ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<ListDocumentWorkOrderData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetListDocumentWorkOrderData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ReturnData uploadDocumentWorkOrder(Long idcompany, Long idbranch, Long iduser, Long idworkorder, MultipartFile file) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		WorkOrderData value = checkById(idcompany,idbranch,idworkorder);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					byte[] fileencode = Base64.encodeBase64(file.getBytes());
			        String result = new String(fileencode);
			        InfoFile infofile = fileStorageService.getInfoFile(file);
			        
			        String fileName = infofile.getNamaFile();//fileStorageService.storeFile(file);
			        String contentType = infofile.getContectType();//fileStorageService.getContentType(file);
			        
			        double sizeInKb = infofile.getSizeFile().longValue() / 1024;
			        double sizeInMb =  sizeInKb / 1024;
			        System.out.println("infofile.getSizeFile() "+infofile.getSizeFile().longValue());
			        System.out.println("sizeInMb "+sizeInMb);
			        List<ParameterData> arrmaxSize = parameterService.getListParameterByGrup("MAX_SIZE_DOCUMENT_IN_MB");
			        if(arrmaxSize != null && arrmaxSize.size() > 0) {
			        	double maxSize = new Double(arrmaxSize.get(0).getCode()).doubleValue();
			        	if(sizeInMb > maxSize) {
			        		ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_DOCUMENT_MAX_SIZE_OVER_LIMIT,"Ukuran Melebihi Batas, Max= "+maxSize+" MB");
							validations.add(msg);
			        	}
			        }
			        
			        if(contentType.equals("application/pdf") || contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png")) {
			        	
			        }else {
			        	ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_DOCUMENT_INCORRECT_FORMAT,"Format Tidak Sesuai");
						validations.add(msg);
			        }
			        if(validations.size() == 0) {
				        Timestamp ts = new Timestamp(new Date().getTime());
				        
				        ListDocumentWorkOrder table = new ListDocumentWorkOrder();
						table.setIdcompany(idcompany);
						table.setIdbranch(idbranch);
						table.setIdworkorder(idworkorder);
						table.setFilename(fileName);
						table.setFiledocument(result);
						table.setContenttype(contentType);
						table.setTanggal(ts);
						idsave = documentWorkOrderRepo.saveAndFlush(table).getId();
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
	public ReturnData deleteDocumentWorkOrder(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		ListDocumentWorkOrderData value = getDocumentById(idcompany, idbranch, id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					documentWorkOrderRepo.deleteById(id);
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
	public ListDocumentWorkOrderData getDocumentWorkOrder(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		ListDocumentWorkOrderData value = getDocumentById(idcompany,idbranch,id);
		if(value != null) {
			ListDocumentWorkOrder table = documentWorkOrderRepo.getById(id);
			
			value.setFiledocument(table.getFiledocument());
		}
		return value;
	}

	@Override
	public List<WorkOrderData> getListDataWoForReport(ParamWoReport param) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderJoinTableData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		if(param.getReportName().equals(ConstantReportName.BONGKARMUATDEPO)) {
			sqlBuilder.append(" and data.tanggalsppb_npe >= '"+new java.sql.Date(param.getFromDate())+"'  and data.tanggalsppb_npe <= '"+new java.sql.Date(param.getToDate())+"' order by data.id asc ");
		}else if(param.getReportName().equals(ConstantReportName.STATUSINVOICE)) {
			sqlBuilder.append(" and data.tanggal >= '"+new java.sql.Date(param.getFromDate())+"'  and data.tanggal <= '"+new java.sql.Date(param.getToDate())+"' ");
			if(!param.getStatus().equals("")) {
				sqlBuilder.append(" and data.status = '"+param.getStatus()+"' ");
			}
		}
		final Object[] queryParameters = new Object[] {param.getIdcompany(),param.getIdbranch()};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderJoinTableData(), queryParameters);
	}

	@Override
	public List<DetailWorkOrderData> getListContainerByIdWorkOrderForReport(Long idcompany, Long idbranch,
			Long idworkorder) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailWorkOrderJoinTableWithSuratJalan().schema());
		sqlBuilder.append(" where data.idworkorder = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idworkorder,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailWorkOrderJoinTableWithSuratJalan(), queryParameters);
	}

	@Override
	public List<WorkOrderDropDownData> getListWOByStatus(Long idcompany, Long idbranch, String status, Object param) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderDropdownData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.status = ? and data.isactive = true  and data.isdelete = false ");
		if(param != null) {
			HashMap<String, Object> mapParam = (HashMap<String, Object>) param;
			String type = (String) mapParam.get("type");
			if(type.equals("PENERIMAANKASBANK")) {
				Long idpenerimaankasbank = (Long) mapParam.get("idpenerimaankasbank");
				sqlBuilder.append(" or data.id in (select idworkorder from detail_penerimaan_kas_bank as p where p.idpenerimaankasbank = "+idpenerimaankasbank+" ) ");
			}else if(type.equals("INVOICE")) {
				Long idcustomer = (Long) mapParam.get("idcustomer");
				sqlBuilder.append(" and data.idcustomer = "+idcustomer+" ");
			}
		}
		sqlBuilder.append(" order by data.nodocument ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch,status};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderDropdownData(), queryParameters);
	}

	@Override
	public WorkOrderData getByIdNotJoin(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderNotJoinTableData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<WorkOrderData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderNotJoinTableData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public HashMap<String, Object> checkWO(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		HashMap<String, Object> result = new HashMap<String, Object>();
		Optional<WorkOrder> opt = repository.findById(id.longValue());
		boolean isFound = true;
		boolean isActive = true;
		boolean isStatusAvailable = true;
		if(opt.isPresent()) {
			WorkOrder obj = opt.get();
			if(obj.isIsdelete()) {
				isFound = false;
			}else if(!obj.isIsactive()) {
				isActive = false;
			}
			
			if(obj.getStatus().equals("CLOSE") || obj.getStatus().equals("CANCEL")) {
				isStatusAvailable = false;
			}
		}
		result.put("ISFOUND", isFound);
		result.put("ISACTIVE", isActive);
		result.put("ISSTATUSAVAILABLE", isStatusAvailable);
		return result;
	}

	@Override
	public List<WorkOrderData> getListSearchWO(Long idcompany, Long idbranch, BodySearch body) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderJoinCustomerData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		sqlBuilder.append(" and ");
		if(body.getIdwo().intValue() == 0) {
			sqlBuilder.append(" ( ");
			sqlBuilder.append(" lower(data.nodocument) like '%"+body.getNodocument().toLowerCase()+"%' ");
			sqlBuilder.append(" or lower(data.namacargo) like '%"+body.getNamacargo().toLowerCase()+"%' ");
			sqlBuilder.append(" or data.idcustomer in (select id from m_customer_manggala as cust where lower(cust.customername) like '%"+body.getNamacustomer().toLowerCase()+"%' )  ");
			sqlBuilder.append(" ) ");
		}else {
			sqlBuilder.append(" data.id = "+body.getIdwo()+" ");
		}
		
		if(body.getIdcustomer() != null) {
			sqlBuilder.append(" and data.idcustomer = "+body.getIdcustomer());
		}
		
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderJoinCustomerData(), queryParameters);
	}

	@Override
	public ReturnData changeStatusWO(Long idcompany, Long idbranch, Long id,String status) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		WorkOrderData value = checkById(idcompany,idbranch,id);
		
		if(validations.size() == 0) {
			try {
				if(value != null) {
					Timestamp ts = new Timestamp(new Date().getTime());
					WorkOrder table = repository.getById(id);
					table.setStatus(status);
					idsave = repository.saveAndFlush(table).getId();					
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
	public List<DetailWorkOrderData> getListContainerByIdWorkOrderForReportStatusInvoice(Long idcompany, Long idbranch,
			Long idworkorder) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailWorkOrderForReportStatusInvoice().schema());
		sqlBuilder.append(" where data.idworkorder = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idworkorder,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailWorkOrderForReportStatusInvoice(), queryParameters);
	}

	@Override
	public List<WorkOrderData> getListDataWoForReportLabaRugi(Long idcompany,Long idbranch,ParamReportManggala param) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderJoinTableData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		sqlBuilder.append(" and data.status = 'CLOSED' and data.tanggal >= '"+new java.sql.Date(param.getFromDate())+"'  and data.tanggal <= '"+new java.sql.Date(param.getToDate())+"' ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderJoinTableData(), queryParameters);
	}

	@Override
	public List<WorkOrderDropDownData> getListDropDownByParam(Long idcompany, Long idbranch, ParamDropDownWO param) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWorkOrderDropdownData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		if(param.getMenu().equals("PENGELUARAN_KAS_BANK")) {
			sqlBuilder.append(" and data.status = '"+param.getStatus()+"' ");
		}
		sqlBuilder.append(" order by cust.customername ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWorkOrderDropdownData(), queryParameters);
	}

	@Override
	public List<DetailWorkOrderData> getListContainerByIdWorkOrderForSuratJalan(Long idcompany, Long idbranch,
			Long idworkorder) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailWorkOrderJoinTable().schema());
		sqlBuilder.append(" where data.idworkorder = ? and data.idcompany = ? and data.idbranch = ? ");
		sqlBuilder.append(" and data.nocontainer in (select sj.nocantainer from t_surat_jalan as sj where sj.status in ('OPEN_SJ') and sj.isdelete = false and sj.idworkorder = "+idworkorder+" ) ");
		
		final Object[] queryParameters = new Object[] {idworkorder,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailWorkOrderJoinTable(), queryParameters);
	}

	@Override
	public ReturnData updateWorkOrderStatus(Long idcompany, Long idbranch,Long iduser, Long id, BodyWorkOrder body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		if(validations.size() == 0) {
			try {
					Timestamp ts = new Timestamp(new Date().getTime());
					WorkOrder table = repository.getById(id);
					table.setStatus(body.getStatus());
					
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
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

}
