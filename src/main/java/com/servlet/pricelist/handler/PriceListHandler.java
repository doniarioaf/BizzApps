package com.servlet.pricelist.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.customermanggala.service.CustomerManggalaService;
import com.servlet.invoicetype.service.InvoiceTypeService;
import com.servlet.pricelist.entity.BodyDetailPriceList;
import com.servlet.pricelist.entity.BodyPriceList;
import com.servlet.pricelist.entity.BodySearchPriceList;
import com.servlet.pricelist.entity.DetailPriceList;
import com.servlet.pricelist.entity.DetailPriceListData;
import com.servlet.pricelist.entity.DetailPriceListPK;
import com.servlet.pricelist.entity.PriceList;
import com.servlet.pricelist.entity.PriceListData;
import com.servlet.pricelist.entity.PriceListTemplate;
import com.servlet.pricelist.mapper.GetDetailPriceListDataJoinTable;
import com.servlet.pricelist.mapper.GetPriceListData;
import com.servlet.pricelist.mapper.GetPriceListNotJoinTable;
import com.servlet.pricelist.mapper.GetPriceListSearchData;
import com.servlet.pricelist.repo.DetailPriceListRepo;
import com.servlet.pricelist.repo.PriceListRepo;
import com.servlet.pricelist.service.PriceListService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class PriceListHandler implements PriceListService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PriceListRepo repository;
	@Autowired
	private CustomerManggalaService customerManggalaService;
	@Autowired
	private RunningNumberService runningNumberService;
	@Autowired
	private DetailPriceListRepo detailPriceListRepo;
	@Autowired
	private InvoiceTypeService invoiceTypeService;
	
	@Override
	public List<PriceListData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPriceListData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPriceListData(), queryParameters);
	}

	@Override
	public List<PriceListData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPriceListData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPriceListData(), queryParameters);
	}

	@Override
	public PriceListData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPriceListData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PriceListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPriceListData(), queryParameters);
		if(list != null && list.size() > 0) {
			PriceListData val = list.get(0);
			val.setDetails(getDetails(idcompany, idbranch, id));
			return list.get(0);
		}
		return null;
	}

	@Override
	public ReturnData savePriceList(Long idcompany, Long idbranch, Long iduser, BodyPriceList body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		Timestamp ts = new Timestamp(new Date().getTime());
		String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PRICELIST, ts);
		if(docNumber.equals("")) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
			validations.add(msg);
		}else {
			PriceListData data = checkByIdCust(idcompany, idbranch, body.getIdcustomer());
			if(data != null) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PRICELIST_CUSTOMER_EXISTS,"Customer Sudah Ada");
				validations.add(msg);
			}
			
		}
		
		if(validations.size() == 0) {
			try {
				
				PriceList table = new PriceList();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setIdcustomer(body.getIdcustomer());
				table.setNodocument(docNumber);
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				
				idsave = repository.saveAndFlush(table).getId();
				
				putDetail(body.getDetailpricelist(), idcompany, idbranch, idsave, "ADD");
				
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
	public ReturnData updatePriceList(Long idcompany, Long idbranch, Long iduser, Long id, BodyPriceList body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		PriceListData value = checkById(idcompany, idbranch, id);
		if(value != null) {
			if(!body.getIdcustomer().equals(value.getIdcustomer())) {
				PriceListData data = checkByIdCust(idcompany, idbranch, body.getIdcustomer());
				if(data != null) {
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_PRICELIST_CUSTOMER_EXISTS,"Customer Sudah Ada");
					validations.add(msg);
				}
			}
			
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					PriceList table = repository.getById(id);
					table.setIdcustomer(body.getIdcustomer());
					table.setIsactive(body.isIsactive());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
					
					putDetail(body.getDetailpricelist(), idcompany, idbranch, idsave, "EDIT");
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
	public ReturnData deletePriceList(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		PriceListData value = checkById(idcompany, idbranch, id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			PriceList table = repository.getById(id);
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

	@Override
	public PriceListTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return setTemplate(idcompany, idbranch,0L);
	}
	
	private PriceListTemplate setTemplate(Long idcompany, Long idbranch, Long idcustomer) {
		PriceListTemplate data = new PriceListTemplate();
		data.setCustomerOptions(customerManggalaService.getListCustomerForPriceList(idcompany, idbranch,idcustomer));
		data.setBiayaJasaOptions(invoiceTypeService.getListAllByInvoiceType(idcompany, idbranch, "JASA"));
		
		return data;
	}

	@Override
	public PriceListData getDataWithTemplateById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		PriceListData val = getById(idcompany,idbranch,id);
		if(val != null) {
			val.setTemplate(setTemplate(idcompany, idbranch,val.getIdcustomer()));
		}
		return val;
	}
	
	private PriceListData checkById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPriceListData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PriceListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPriceListData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	private PriceListData checkByIdCust(Long idcompany, Long idbranch, Long idCust) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPriceListNotJoinTable().schema());
		sqlBuilder.append(" where data.idcustomer = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idCust,idcompany,idbranch};
		List<PriceListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPriceListNotJoinTable(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	private String putDetail(BodyDetailPriceList[] details,Long idcompany, Long idbranch,long idsave,String action) {
		if(action.equals("EDIT")) {
			detailPriceListRepo.deleteAllDetail(idsave, idcompany, idbranch);
		}
		if(details != null) {
			if(details.length > 0) {
				for(int i=0; i < details.length; i++) {
					BodyDetailPriceList detail = details[i];
					
					DetailPriceListPK detailPriceListPK = new DetailPriceListPK();
					detailPriceListPK.setIdpricelist(idsave);
					detailPriceListPK.setIdcompany(idcompany);
					detailPriceListPK.setIdbranch(idbranch);
					detailPriceListPK.setIdwarehouse(detail.getIdwarehouse());
					detailPriceListPK.setIdinvoicetype(detail.getIdinvoicetype());
					detailPriceListPK.setJalur(detail.getJalur());
					
					DetailPriceList detailPriceList = new DetailPriceList();
					detailPriceList.setDetailPriceListPK(detailPriceListPK);
					detailPriceList.setIsmandatory(detail.getIsmandatory());
					detailPriceList.setPrice(detail.getPrice());
					detailPriceListRepo.saveAndFlush(detailPriceList);
				}
			}
		}
		return "";
	}
	
	private List<DetailPriceListData> getDetails(Long idcompany, Long idbranch,Long idpricelist) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailPriceListDataJoinTable().schema());
		sqlBuilder.append(" where data.idpricelist = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idpricelist,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailPriceListDataJoinTable(), queryParameters);
	}

	@Override
	public List<PriceListData> getListSearch(Long idcompany, Long idbranch, BodySearchPriceList body) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPriceListSearchData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		sqlBuilder.append(" and lower(data.nodocument) = '"+body.getNodocument().toLowerCase()+"' or lower(cust.customername) like '%"+body.getNamacustomer().toLowerCase()+"%' ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<PriceListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPriceListSearchData(), queryParameters);
		return list;
	}
	

}
