package com.servlet.mobile.customercallplan.handler;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlan;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanPK;
import com.servlet.mobile.customercallplan.mapper.GetCustomerCallPlanByIdCustomer;
import com.servlet.mobile.customercallplan.repo.CustomerCallPlanRepo;
import com.servlet.mobile.customercallplan.service.CustomerCallPlanService;
import com.servlet.shared.ReturnData;

@Service
public class CustomerCallPlanHandler implements CustomerCallPlanService{
	@Autowired
	private CustomerCallPlanRepo repository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Object saveCustomerCallPlan(CustomerCallPlanPK customerCallPlanPK) {
		// TODO Auto-generated method stub
		CustomerCallPlan table = new CustomerCallPlan();
		table.setCustomerCallPlanPK(customerCallPlanPK);
		CustomerCallPlan returntable = repository.saveAndFlush(table);
		ReturnData data = new ReturnData();
		data.setId(returntable.getCustomerCallPlanPK().getIdcustomer());
		return data;
	}

	@Override
	public Object saveCustomerCallPlanList(List<CustomerCallPlan> list) {
		// TODO Auto-generated method stub
		if(list.size() > 0) {
			repository.saveAllAndFlush(list);
		}
		return null;
	}

	@Override
	public Collection<CustomerCallPlanData> getListCustomerCallPlan(long idcallplan) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCustomerCallPlanByIdCustomer().schema());
		sqlBuilder.append(" where mccp.idcallplan = ? ");
		final Object[] queryParameters = new Object[] { idcallplan };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCustomerCallPlanByIdCustomer(), queryParameters);
	}

	@Override
	public Object deleteAllCustomeCallPlanByListPK(List<CustomerCallPlanPK> listPK) {
		// TODO Auto-generated method stub
		if(listPK.size() > 0) {
			repository.deleteAllById(listPK);
		}
		return null;
	}

}