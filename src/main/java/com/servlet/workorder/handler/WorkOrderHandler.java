package com.servlet.workorder.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.customermanggala.entity.CustomerManggalaData;
import com.servlet.customermanggala.service.CustomerManggalaService;
import com.servlet.parameter.service.ParameterService;
import com.servlet.partai.service.PartaiService;
import com.servlet.port.service.PortService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendor.service.VendorService;
import com.servlet.workorder.entity.BodyDetailWorkOrder;
import com.servlet.workorder.entity.BodyWorkOrder;
import com.servlet.workorder.entity.DetailWorkOrder;
import com.servlet.workorder.entity.DetailWorkOrderData;
import com.servlet.workorder.entity.DetailWorkOrderPK;
import com.servlet.workorder.entity.WorkOrder;
import com.servlet.workorder.entity.WorkOrderData;
import com.servlet.workorder.entity.WorkOrderDropDownData;
import com.servlet.workorder.entity.WorkOrderTemplate;
import com.servlet.workorder.mapper.GetDetailWorkOrderJoinTable;
import com.servlet.workorder.mapper.GetWorkOrderDropdownData;
import com.servlet.workorder.mapper.GetWorkOrderJoinCustomerData;
import com.servlet.workorder.mapper.GetWorkOrderJoinTableData;
import com.servlet.workorder.mapper.GetWorkOrderNotJoinTableData;
import com.servlet.workorder.repo.DetailWorkOrderRepo;
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
				WorkOrder table = new WorkOrder();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setNodocument(docNumber);
				table.setTanggal(new java.sql.Date(body.getTanggal()));
				table.setIdcustomer(body.getIdcustomer());
				table.setNamacargo(body.getNamacargo());
				table.setStatus(body.getStatus());
				table.setJeniswo(body.getJeniswo());
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
				table.setDepo(body.getDepo());
				table.setInvoiceno("");
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
				Timestamp ts = new Timestamp(new Date().getTime());
				WorkOrder table = repository.getById(id);
				table.setNamacargo(body.getNamacargo());
				table.setStatus(body.getStatus());
				table.setJeniswo(body.getJeniswo());
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
				table.setDepo(body.getDepo());
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
					
					DetailWorkOrder detailWorkOrder = new DetailWorkOrder();
					detailWorkOrder.setDetailWorkOrderPK(detailWorkOrderPK);
					detailWorkOrder.setBarang(detail.getBarang());
					detailWorkOrder.setJumlahkg(detail.getJumlahkg());
					detailWorkOrder.setJumlahkoli(detail.getJumlahkoli());
					detailWorkOrder.setNocontainer(detail.getNocontainer());
					detailWorkOrder.setNoseal(detail.getNoseal());
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
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false and data.jeniswo != 'JS' and data.status = 'OPEN' order by cust.customername ");
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

}