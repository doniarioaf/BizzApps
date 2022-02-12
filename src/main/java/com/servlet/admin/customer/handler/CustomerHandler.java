package com.servlet.admin.customer.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.customer.entity.BodyCustomer;
import com.servlet.admin.customer.entity.Customer;
import com.servlet.admin.customer.entity.CustomerDetailData;
import com.servlet.admin.customer.entity.CustomerListData;
import com.servlet.admin.customer.entity.CustomerTemplate;
import com.servlet.admin.customer.mapper.GetCustomerList;
import com.servlet.admin.customer.mapper.GetDetailCustomer;
import com.servlet.admin.customer.repo.CustomerRepo;
import com.servlet.admin.customer.service.CustomerService;
import com.servlet.admin.customertype.service.CustomerTypeService;
import com.servlet.mobile.project.entity.ProjectData;
import com.servlet.mobile.project.service.ProjectService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class CustomerHandler implements CustomerService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CustomerRepo repository;
	@Autowired
	private CustomerTypeService customerTypeService;
	@Autowired
	private ProjectService projectService;
	
	@Override
	public ReturnData saveCustomer(BodyCustomer customer, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idreturn = 0;
		if(!customer.getCustomercode().equals("")) {
			CustomerListData checkCustCode = getCustomerByCustomerCode(customer.getCustomercode(),idcompany,idbranch);
			if(checkCustCode != null) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CUSTOMERCODE_IS_EXIST,"Customer Code Sudah Terpakai");
				validations.add(msg);
			}
		}
		
		if(validations.size() == 0) {
			Timestamp ts = new Timestamp(new Date().getTime());
			Customer table = new Customer();
			table.setIdcompany(idcompany);
			table.setIdbranch(idbranch);
			table.setIdcustomertype(customer.getIdcustomertype());
			table.setNama(customer.getNama());
			table.setAddress(customer.getAddress());
			table.setProvinsi(customer.getProvinsi());
			table.setCity(customer.getCity());
			table.setAreaname(customer.getAreaname());
			table.setSubarename(customer.getSubarename());
			table.setPhone(customer.getPhone());
			table.setLatitude(customer.getLatitude());
			table.setLongitude(customer.getLongitude());
			table.setIsdelete(false);
			table.setCreated(ts);
			table.setModified(ts);
			table.setCustomercode(customer.getCustomercode());
			table.setContactperson(customer.getContactperson());
			Customer returntable = repository.saveAndFlush(table);
			idreturn = returntable.getId();
		}
		ReturnData data = new ReturnData();
		data.setId(idreturn);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData updateCustomer(long id, BodyCustomer customer, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		CustomerListData check = checkCustomerById(id,idcompany,idbranch);
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idreturn = 0;
		if(check != null) {
			if(!customer.getCustomercode().equals("") && !customer.getCustomercode().equals(check.getCustomercode())) {
				CustomerListData checkCustCode = getCustomerByCustomerCode(customer.getCustomercode(),idcompany,idbranch);
				if(checkCustCode != null) {
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CUSTOMERCODE_IS_EXIST,"Customer Code Sudah Terpakai");
					validations.add(msg);
				}
			}
			if(validations.size() == 0) {
				Customer table = repository.getById(id);
				table.setIdcustomertype(customer.getIdcustomertype());
				table.setNama(customer.getNama());
				table.setAddress(customer.getAddress());
				table.setProvinsi(customer.getProvinsi());
				table.setCity(customer.getCity());
				table.setAreaname(customer.getAreaname());
				table.setSubarename(customer.getSubarename());
				table.setPhone(customer.getPhone());
				table.setLatitude(customer.getLatitude());
				table.setLongitude(customer.getLongitude());
				table.setModified(ts);
				table.setCustomercode(customer.getCustomercode());
				table.setContactperson(customer.getContactperson());
				Customer returntable = repository.saveAndFlush(table);
				idreturn = returntable.getId();
			}
			ReturnData data = new ReturnData();
			data.setId(idreturn);
			data.setSuccess(validations.size() > 0?false:true);
			data.setValidations(validations);
			return data;
			
		}
		return null;
	}
	
	public CustomerListData checkCustomerById(long id,long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCustomerList().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id, idcompany,idbranch};
		List<CustomerListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetCustomerList(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<CustomerListData> getAllListCustomer(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCustomerList().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCustomerList(), queryParameters);
	}

	@Override
	public CustomerDetailData getCustomerById(long id, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailCustomer().schema());
		sqlBuilder.append(" where mc.id = ? and mc.idcompany = ? and mc.idbranch = ? and mc.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<CustomerDetailData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailCustomer(), queryParameters);
		if(list != null && list.size() > 0) {
			List<ProjectData> listproject = projectService.getListProjectByIdCustomer(id, idcompany, idbranch);
			CustomerDetailData datadetail = list.get(0);
			datadetail.setProjects(listproject);
			return datadetail;
		}
		return null;
	}

	@Override
	public CustomerTemplate customerTemplate(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		CustomerTemplate data = new CustomerTemplate();
		data.setCustomertypeoptions(customerTypeService.getAllListCustomerType(idcompany, idbranch));
		return data;
	}

	@Override
	public ReturnData deleteCustomer(long id) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Customer table = repository.getById(id);
		table.setIsdelete(true);
		table.setModified(ts);
		Customer returntable = repository.saveAndFlush(table);
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		return data;
	}


	@Override
	public ReturnData updateLatLong(long id,String latitude,String longitude) {
		// TODO Auto-generated method stub
		Optional<Customer> value = repository.findById(id);
		if(value.isPresent()) {
			Customer table = value.get();
			if(table.getLatitude() == null) {
				table.setLatitude(latitude);
			}else if(table.getLatitude().equals("")) {
				table.setLatitude(latitude);
			}
			
			if(table.getLongitude() == null) {
				table.setLongitude(longitude);
			}else if(table.getLongitude().equals("")) {
				table.setLongitude(longitude);
			}
			
			Customer returntable = repository.saveAndFlush(table);
			
			ReturnData data = new ReturnData();
			data.setId(returntable.getId());
			return data;
		}
		
		return null;
	}

	@Override
	public CustomerListData getCustomerByCustomerCode(String customerCode, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCustomerList().schema());
		sqlBuilder.append(" where data.customercode = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {customerCode, idcompany,idbranch};
		List<CustomerListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetCustomerList(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
