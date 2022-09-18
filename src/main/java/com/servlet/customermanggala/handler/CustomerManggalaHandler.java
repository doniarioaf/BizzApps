package com.servlet.customermanggala.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.entity.City;
import com.servlet.address.entity.PostalCode;
import com.servlet.address.entity.Province;
import com.servlet.address.service.CityService;
import com.servlet.address.service.PostalCodeService;
import com.servlet.address.service.ProvinceService;
import com.servlet.customermanggala.entity.BodyCustomerManggala;
import com.servlet.customermanggala.entity.BodyCustomerManggalaInfoContact;
import com.servlet.customermanggala.entity.BodyCustomerManggalaInfoKementerian;
import com.servlet.customermanggala.entity.CustomerManggala;
import com.servlet.customermanggala.entity.CustomerManggalaData;
import com.servlet.customermanggala.entity.CustomerManggalaTemplate;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoContact;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoContactData;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoContactPK;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoKementerian;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoKementerianData;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoKementerianPK;
import com.servlet.customermanggala.mapper.GetDataCustomerManggala;
import com.servlet.customermanggala.mapper.GetDataDetailCustomerManggalaInfoContact;
import com.servlet.customermanggala.mapper.GetDataDetailCustomerManggalaKementerian;
import com.servlet.customermanggala.repo.CustomerManggalaRepo;
import com.servlet.customermanggala.repo.DetailCustomerManggalaInfoContactRepo;
import com.servlet.customermanggala.repo.DetailCustomerManggalaInfoKementerianRepo;
import com.servlet.customermanggala.service.CustomerManggalaService;
import com.servlet.parameter.service.ParameterService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class CustomerManggalaHandler implements CustomerManggalaService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CityService cityService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private PostalCodeService postalCodeService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private CustomerManggalaRepo repository;
	@Autowired
	private DetailCustomerManggalaInfoKementerianRepo detailCustomerManggalaInfoKementerianRepo;
	@Autowired
	private DetailCustomerManggalaInfoContactRepo detailCustomerManggalaInfoContactRepo;
	
	@Override
	public CustomerManggalaTemplate customerManggalaTemplate(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		return getCustomerTemplate(idcompany,idbranch);
	}

	@Override
	public List<CustomerManggalaData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataCustomerManggala().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataCustomerManggala(), queryParameters);
	}

	@Override
	public List<CustomerManggalaData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataCustomerManggala().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataCustomerManggala(), queryParameters);
	}

	@Override
	public CustomerManggalaData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataCustomerManggala().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<CustomerManggalaData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataCustomerManggala(), queryParameters);
		if(list != null && list.size() > 0) {
			CustomerManggalaData val = list.get(0);
			Province prov = provinceService.getById(new Long(val.getProvinsi()).longValue());
			City city = cityService.getById(new Long(val.getKota()).longValue());
//			PostalCode postalcode = postalCodeService.getById(new Long(val.getKodepos()).longValue());
			if(prov != null) {
				val.setProvinsiname(prov.getProv_name());
			}
			if(city != null) {
				val.setKotaname(city.getCity_name());
			}
			val.setTemplate(getCustomerTemplate(idcompany,idbranch));
			val.setDetailsInfoKementerian(getListDetailInfoKementerian(idcompany, idbranch, id));
			val.setDetailsInfoContact(getListDetailInfoContact(idcompany, idbranch, id));
//			if(postalcode != null) {
//				val.setKodeposname(postalcode.getPostal_code().toString());
//			}
			return val;
		}
		return null;
	}

	@Override
	public ReturnData saveCustomerManggala(Long idcompany, Long idbranch, Long iduser, BodyCustomerManggala body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				CustomerManggala table = new CustomerManggala();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setCustomertype(body.getCustomertype());
				table.setCustomername(body.getCustomername());
				table.setAlias(body.getAlias());
				table.setAlamat(body.getAlamat());
				table.setProvinsi(body.getProvinsi());
				table.setKota(body.getKota());
				table.setKodepos(body.getKodepos());
				table.setNpwp(body.getNpwp());
				table.setNib(body.getNib());
				table.setTelpkantor(body.getTelpkantor());
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				idsave = repository.saveAndFlush(table).getId();
				
				putDetailInforKementrian(body.getDetailsinfokementerian(),idcompany,idbranch,idsave,"ADD");
				putDetailInforContact(body.getDetailsinfocontact(),idcompany,idbranch,idsave,"ADD");
				
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
	
	private String putDetailInforContact(BodyCustomerManggalaInfoContact[] detailsinfocontact,Long idcompany, Long idbranch,long idsave,String action) {
		if(action.equals("EDIT")) {
			detailCustomerManggalaInfoContactRepo.deleteAllInfoContact(idsave, idcompany, idbranch);
		}
		if(detailsinfocontact != null) {
			if(detailsinfocontact.length > 0) {
				int counting = 1;
				for(int i=0; i < detailsinfocontact.length; i++) {
					BodyCustomerManggalaInfoContact detail = detailsinfocontact[i];
					DetailCustomerManggalaInfoContactPK detailCustomerManggalaInfoContactPK = new DetailCustomerManggalaInfoContactPK();
					detailCustomerManggalaInfoContactPK.setIdcustomermanggala(idsave);
					detailCustomerManggalaInfoContactPK.setCountdetail(counting);
					detailCustomerManggalaInfoContactPK.setIdcompany(idcompany);
					detailCustomerManggalaInfoContactPK.setIdbranch(idbranch);
					
					DetailCustomerManggalaInfoContact detailCustomerManggalaInfoContact = new DetailCustomerManggalaInfoContact();
					detailCustomerManggalaInfoContact.setDetailCustomerManggalaInfoContactPK(detailCustomerManggalaInfoContactPK);
					detailCustomerManggalaInfoContact.setPanggilan(detail.getPanggilan());
					detailCustomerManggalaInfoContact.setNamakontak(detail.getNamakontak());
					detailCustomerManggalaInfoContact.setNotelepon(detail.getNotelepon());
					detailCustomerManggalaInfoContact.setEmail(detail.getEmail());
					detailCustomerManggalaInfoContact.setNoext(detail.getNoext());
					detailCustomerManggalaInfoContactRepo.saveAndFlush(detailCustomerManggalaInfoContact);
					counting++;
				}
			}
			
		}
		return "";
		
	}
	
	private String putDetailInforKementrian(BodyCustomerManggalaInfoKementerian[] detailsinfokementerian,Long idcompany, Long idbranch,long idsave,String action) {
		if(action.equals("EDIT")) {
			detailCustomerManggalaInfoKementerianRepo.deleteAllInfoKementerian(idsave, idcompany, idbranch);
		}
		if(detailsinfokementerian != null) {
			if(detailsinfokementerian.length > 0) {
				int counting = 1;
				for(int i=0; i < detailsinfokementerian.length; i++) {
					BodyCustomerManggalaInfoKementerian detail = detailsinfokementerian[i]; 
					DetailCustomerManggalaInfoKementerianPK detailCustomerManggalaInfoKementerianPK = new DetailCustomerManggalaInfoKementerianPK();
					detailCustomerManggalaInfoKementerianPK.setIdcustomermanggala(idsave);
					detailCustomerManggalaInfoKementerianPK.setCountdetail(counting);
					detailCustomerManggalaInfoKementerianPK.setIdcompany(idcompany);
					detailCustomerManggalaInfoKementerianPK.setIdbranch(idbranch);
					
					DetailCustomerManggalaInfoKementerian detailCustomerManggalaInfoKementerian = new DetailCustomerManggalaInfoKementerian();
					detailCustomerManggalaInfoKementerian.setDetailCustomerManggalaInfoKementerianPK(detailCustomerManggalaInfoKementerianPK);
					detailCustomerManggalaInfoKementerian.setKementerian(detail.getKementerian());
					detailCustomerManggalaInfoKementerian.setAlamat_email(detail.getAlamat_email());
					detailCustomerManggalaInfoKementerian.setPassword_email(detail.getPassword_email());
					detailCustomerManggalaInfoKementerianRepo.saveAndFlush(detailCustomerManggalaInfoKementerian);
					counting++;
				}
			}
		}
		return "";
	}

	@Override
	public ReturnData updateCustomerManggala(Long idcompany, Long idbranch, Long iduser, Long id,
			BodyCustomerManggala body) {
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		CustomerManggalaData value = getById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					CustomerManggala table = repository.getById(id);
					table.setCustomertype(body.getCustomertype());
					table.setCustomername(body.getCustomername());
					table.setAlias(body.getAlias());
					table.setAlamat(body.getAlamat());
					table.setProvinsi(body.getProvinsi());
					table.setKota(body.getKota());
					table.setKodepos(body.getKodepos());
					table.setNpwp(body.getNpwp());
					table.setNib(body.getNib());
					table.setTelpkantor(body.getTelpkantor());
					table.setIsactive(body.isIsactive());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
					
					putDetailInforKementrian(body.getDetailsinfokementerian(),idcompany,idbranch,idsave,"EDIT");
					putDetailInforContact(body.getDetailsinfocontact(),idcompany,idbranch,idsave,"EDIT");
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
	public ReturnData deleteCustomerManggala(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		CustomerManggalaData value = getById(idcompany,idbranch,id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			CustomerManggala table = repository.getById(id);
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
	
	private CustomerManggalaTemplate getCustomerTemplate(long idcompany, long idbranch) {
		CustomerManggalaTemplate data = new CustomerManggalaTemplate();
		data.setCityOptions(cityService.getListCity());
		data.setProvinceOptions(provinceService.getListProvince());
		data.setPanggilanOptions(parameterService.getListParameterByGrup("PANGGILAN"));
		data.setCustomertypeOptions(parameterService.getListParameterByGrup("LEVEL_PERUSAHAAN"));
		return data;
	}

	@Override
	public List<DetailCustomerManggalaInfoKementerianData> getListDetailInfoKementerian(Long idcompany, Long idbranch,
			Long idcust) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataDetailCustomerManggalaKementerian().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.idcustomermanggala = ? ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch,idcust};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataDetailCustomerManggalaKementerian(), queryParameters);
	}

	@Override
	public List<DetailCustomerManggalaInfoContactData> getListDetailInfoContact(Long idcompany, Long idbranch,
			Long idcust) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataDetailCustomerManggalaInfoContact().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.idcustomermanggala = ? ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch,idcust};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataDetailCustomerManggalaInfoContact(), queryParameters);
	}
	
}
