package com.servlet.admin.customertype.handler;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.customertype.entity.BodyCustomerType;
import com.servlet.admin.customertype.entity.CustomerType;
import com.servlet.admin.customertype.entity.CustomerTypeData;
import com.servlet.admin.customertype.mapper.GetCustomerType;
import com.servlet.admin.customertype.repo.CustomerTypeRepo;
import com.servlet.admin.customertype.service.CustomerTypeService;
import com.servlet.shared.ReturnData;

@Service
public class CustomerTypeHandler implements CustomerTypeService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CustomerTypeRepo repository;
	
	@Override
	public ReturnData saveCustomerType(BodyCustomerType customertype, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
//		Timestamp ts = new Timestamp(new Date().getTime());
		CustomerType table = new CustomerType();
		table.setNama(customertype.getNama());
		table.setDescription(customertype.getDescription());
		table.setIsdelete(false);
		table.setIdcompany(idcompany);
		table.setIdbranch(idbranch);
		CustomerType returntable = repository.saveAndFlush(table);
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		
		return data;
	}

	@Override
	public ReturnData updateCustomerType(long id, BodyCustomerType customertype, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		CustomerTypeData customerTypeData = getCustomerTypeById(id,idcompany,idbranch);
		if(customerTypeData != null) {
			CustomerType table = repository.getById(id);
			table.setNama(customertype.getNama());
			table.setDescription(customertype.getDescription());
			table.setIsdelete(false);
			table.setIdcompany(idcompany);
			table.setIdbranch(idbranch);
			CustomerType returntable = repository.saveAndFlush(table);
			ReturnData data = new ReturnData();
			data.setId(returntable.getId());
			return data;
		}
		return null;
	}

	@Override
	public List<CustomerTypeData> getAllListCustomerType(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCustomerType().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] { idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCustomerType(), queryParameters);
	}

	@Override
	public CustomerTypeData getCustomerTypeById(long id, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCustomerType().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] { id,idcompany,idbranch};
		List<CustomerTypeData> listcustomertype = this.jdbcTemplate.query(sqlBuilder.toString(), new GetCustomerType(), queryParameters);
		if(listcustomertype != null && listcustomertype.size() > 0) {
			return listcustomertype.get(0);
		}
		return null;
	}
	
}
