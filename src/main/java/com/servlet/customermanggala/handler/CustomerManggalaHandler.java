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
import com.servlet.customermanggala.entity.CustomerManggala;
import com.servlet.customermanggala.entity.CustomerManggalaData;
import com.servlet.customermanggala.entity.CustomerManggalaTemplate;
import com.servlet.customermanggala.mapper.GetDataCustomerManggala;
import com.servlet.customermanggala.repo.CustomerManggalaRepo;
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
	
	@Override
	public CustomerManggalaTemplate customerManggalaTemplate(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		CustomerManggalaTemplate data = new CustomerManggalaTemplate();
		data.setCityOptions(cityService.getListCity());
		data.setProvinceOptions(provinceService.getListProvince());
		data.setPanggilanOptions(parameterService.getListParameterByGrup("PANGGILAN"));
		data.setCustomertypeOptions(parameterService.getListParameterByGrup("LEVEL_PERUSAHAAN"));
		return data;
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
			PostalCode postalcode = postalCodeService.getById(new Long(val.getKodepos()).longValue());
			if(prov != null) {
				val.setProvinsiname(prov.getProv_name());
			}
			if(city != null) {
				val.setKotaname(city.getCity_name());
			}
			if(postalcode != null) {
				val.setKodeposname(postalcode.getPostal_code().toString());
			}
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
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				idsave = repository.saveAndFlush(table).getId();
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
					table.setIsactive(body.isIsactive());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
				}catch (Exception e) {
					// TODO: handle exception
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
					validations.add(msg);
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
	
}
