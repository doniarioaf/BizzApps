package com.servlet.suratjalan.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.entity.City;
import com.servlet.address.entity.DistrictData;
import com.servlet.address.entity.PostalCodeData;
import com.servlet.address.entity.Province;
import com.servlet.address.service.CityService;
import com.servlet.address.service.DistrictService;
import com.servlet.address.service.PostalCodeService;
import com.servlet.address.service.ProvinceService;
import com.servlet.parameter.service.ParameterService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.suratjalan.entity.BodyStatusSuratJalan;
import com.servlet.suratjalan.entity.BodySuratJalan;
import com.servlet.suratjalan.entity.HistorySuratJalan;
import com.servlet.suratjalan.entity.HistorySuratJalanData;
import com.servlet.suratjalan.entity.PrintData;
import com.servlet.suratjalan.entity.SuratJalan;
import com.servlet.suratjalan.entity.SuratJalanData;
import com.servlet.suratjalan.entity.SuratJalanTemplate;
import com.servlet.suratjalan.mapper.GetDataFullSuratJalan;
import com.servlet.suratjalan.mapper.GetHistorySuratJalan;
import com.servlet.suratjalan.mapper.GetPrintDataSuratJalan;
import com.servlet.suratjalan.mapper.GetSuratJalanList;
import com.servlet.suratjalan.mapper.GetSuratJalanNotJoin;
import com.servlet.suratjalan.repo.HistorySuratJalanRepo;
import com.servlet.suratjalan.repo.SuratJalanRepo;
import com.servlet.suratjalan.service.SuratJalanService;
import com.servlet.workorder.service.WorkOrderService;

@Service
public class SuratJalanHandler implements SuratJalanService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SuratJalanRepo repository;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private RunningNumberService runningNumberService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private HistorySuratJalanRepo historySuratJalanRepo;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private PostalCodeService postalCodeService;
	@Autowired
	private DistrictService districtService;
	
	@Override
	public SuratJalanTemplate suratJalanTemplate(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		return getTemplate(idcompany,idbranch);
	}

	@Override
	public List<SuratJalanData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetSuratJalanList().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false order by data.tanggal ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetSuratJalanList(), queryParameters);
	}

	@Override
	public List<SuratJalanData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetSuratJalanList().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false order by data.tanggal ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetSuratJalanList(), queryParameters);
	}

	@Override
	public SuratJalanData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataFullSuratJalan().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<SuratJalanData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataFullSuratJalan(), queryParameters);
		if(list != null && list.size() > 0) {
			SuratJalanTemplate datatempalte = new SuratJalanTemplate();
			datatempalte.setStatusSJOptions(parameterService.getListParameterByGrup("STATUS_SURATJALAN"));
			
			SuratJalanData val = list.get(0);
			val.setHistory(getListHistory(idcompany,idbranch,id));
			val.setTemplate(datatempalte);
			return val;
		}
		return null;
	}

	@Override
	public SuratJalanData getTemplateWithDataById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataFullSuratJalan().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<SuratJalanData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataFullSuratJalan(), queryParameters);
		if(list != null && list.size() > 0) {
			SuratJalanData val = list.get(0);
			val.setTemplate(getTemplate(idcompany, idbranch));
			return val;
		}
		return null;
	}

	@Override
	public ReturnData saveObject(Long idcompany, Long idbranch, Long iduser, BodySuratJalan body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		Timestamp ts = new Timestamp(new Date().getTime());
		String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_SURATJALAN, ts);
		if(docNumber.equals("")) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
			validations.add(msg);
		}
		if(validations.size() == 0) {
			try {
				SuratJalan table = new SuratJalan();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setNodocument(docNumber);
				table.setStatus("OPEN_SJ");
				table.setTanggal(new Timestamp(body.getTanggal()));
				table.setIdworkorder(body.getIdworkorder());
				table.setIdcustomer(body.getIdcustomer());
				table.setKeterangan(body.getKeterangan());
				table.setIdwarehouse(body.getIdwarehouse());
				table.setCatatan(body.getCatatan());
				table.setNocantainer(body.getNocantainer());
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				
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

	@Override
	public ReturnData updateObject(Long idcompany, Long idbranch, Long iduser, Long id, BodySuratJalan body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		SuratJalanData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					SuratJalan table = repository.getById(id);
					table.setIdworkorder(body.getIdworkorder());
					table.setIdcustomer(body.getIdcustomer());
					table.setKeterangan(body.getKeterangan());
					table.setIdwarehouse(body.getIdwarehouse());
					table.setCatatan(body.getCatatan());
					table.setNocantainer(body.getNocantainer());
					table.setIsactive(body.isIsactive());
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
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData deleteObject(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		SuratJalanData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					SuratJalan table = repository.getById(id);
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
	
	private SuratJalanTemplate setTemplate(long idcompany, long idbranch) {
		SuratJalanTemplate data = new SuratJalanTemplate();
		data.setWoOptions(workOrderService.getListDropDown(idcompany, idbranch));
		data.setStatusSJOptions(parameterService.getListParameterByGrup("STATUS_SURATJALAN"));
		return data;	
	}

	@Override
	public ReturnData updateStatus(Long idcompany, Long idbranch, Long iduser, Long id, BodyStatusSuratJalan body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		SuratJalanData value = checkById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					SuratJalan table = repository.getById(id);
					
					BodyStatusSuratJalan bodytable = new BodyStatusSuratJalan();
					bodytable.setStatus(table.getStatus());
					
					table.setStatus(body.getStatus());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
					
					putHistory(bodytable,body,idcompany,idbranch,iduser,id,ts);
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
	
	private SuratJalanData checkById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetSuratJalanNotJoin().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<SuratJalanData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetSuratJalanNotJoin(), queryParameters);
		if(list != null && list.size() > 0) {
			SuratJalanData val = list.get(0);
			return val;
		}
		return null;
	}

	@Override
	public SuratJalanTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return setTemplate(idcompany,idbranch);
	}
	
	private String putHistory(BodyStatusSuratJalan table,BodyStatusSuratJalan bodyemp,Long idcompany, Long idbranch, Long iduser, Long id,Timestamp ts) {
		boolean flag = false;
		String status = table.getStatus();
		
		if(!table.getStatus().equals(bodyemp.getStatus())) {
			flag = true;
			status = table.getStatus()+"|"+bodyemp.getStatus();
		}
		if(flag) {
			HistorySuratJalan tablehistory = new HistorySuratJalan();
			tablehistory.setIdcompany(idcompany);
			tablehistory.setIdbranch(idbranch);
			tablehistory.setIdsuratjalan(id);
			tablehistory.setIduser(iduser);
			tablehistory.setStatus(status);
			tablehistory.setTanggal(ts);
			historySuratJalanRepo.saveAndFlush(tablehistory);
		}
		return "";
	}
	
	
	private List<HistorySuratJalanData> getListHistory(Long idcompany, Long idbranch,Long idsuratjalan) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetHistorySuratJalan().schema());
		sqlBuilder.append(" where data.idsuratjalan = ? and data.idcompany = ? and data.idbranch = ? order by data.tanggal desc ");
		final Object[] queryParameters = new Object[] {idsuratjalan,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetHistorySuratJalan(), queryParameters);
	}

	@Override
	public PrintData printById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPrintDataSuratJalan().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<PrintData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetPrintDataSuratJalan(), queryParameters);
		if(list != null && list.size() > 0) {
			PrintData val = list.get(0);
			String provName = "";
			if(val.getCustomerProvince() != null) {
				Province prov = provinceService.getById(new Long(val.getCustomerProvince()).longValue());
				if(prov != null) {
					provName = prov.getProv_name();
				}
			}
			val.setCustomerProvince(provName);
			
			String cityName = "";
			if(val.getCustomerCity() != null) {
				City city = cityService.getById(new Long(val.getCustomerCity()).longValue());
				if(city != null) {
					cityName = city.getCity_name();
				}
			}
			val.setCustomerCity(cityName);
			
			String districtName = "";
			if(val.getCustomerKodePos() != null) {
				List<DistrictData> listDistrict = districtService.getListDistrictByPostalCode(new Long(val.getCustomerKodePos()).longValue());
				if(listDistrict.size() > 0) {
					districtName = listDistrict.get(0).getDis_name();
				}
			}
			val.setCustomerDistrict(districtName);
			
			
			return val;
		}
		return null;
	}

}
