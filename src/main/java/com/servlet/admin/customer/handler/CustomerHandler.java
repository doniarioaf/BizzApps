package com.servlet.admin.customer.handler;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
import com.servlet.admin.customertype.entity.CustomerType;
import com.servlet.admin.customertype.entity.CustomerTypeData;
import com.servlet.admin.customertype.mapper.GetCustomerType;
import com.servlet.admin.customertype.service.CustomerTypeService;
import com.servlet.shared.ReturnData;

@Service
public class CustomerHandler implements CustomerService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CustomerRepo repository;
	@Autowired
	private CustomerTypeService customerTypeService;
	
	@Override
	public ReturnData saveCustomer(BodyCustomer customer, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
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
		Customer returntable = repository.saveAndFlush(table);
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		
		return data;
	}

	@Override
	public ReturnData updateCustomer(long id, BodyCustomer customer, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		CustomerListData check = checkCustomerById(id,idcompany,idbranch);
		if(check != null) {
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
			Customer returntable = repository.saveAndFlush(table);
			ReturnData data = new ReturnData();
			data.setId(returntable.getId());
			
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
			return list.get(0);
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

}
