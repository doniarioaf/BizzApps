package com.servlet.mobile.customercallplan.handler;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.company.entity.Company;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlan;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanPK;
import com.servlet.mobile.customercallplan.entity.DownloadCustomerCallPlan;
import com.servlet.mobile.customercallplan.mapper.GetCountCustomerCallPlan;
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
		sqlBuilder.append(" where mccp.idcallplan = ? and mc.isdelete = false ");
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
	
	private List<Long> getCountListCustomerCallPlanByIdUser(long idusermobile,long idcompany,long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCountCustomerCallPlan().schema());
		sqlBuilder.append(" where mccp.idcallplan in (select idcallplan from m_user_mobile_call_plan as mumcp where mumcp.idusermobile = ? and mumcp.idcompany = ? and mumcp.idbranch = ?) ");
		sqlBuilder.append(" and mc.isdelete = false and mcp.isdelete = false ");
		final Object[] queryParameters = new Object[] { idusermobile,idcompany,idbranch };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCountCustomerCallPlan(), queryParameters);
	}

	@Override
	public List<CustomerCallPlanData> getListCustomerCallPlanByIdUser(long idusermobile,long idcompany,long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCustomerCallPlanByIdCustomer().schema());
		sqlBuilder.append(" where mccp.idcallplan in (select idcallplan from m_user_mobile_call_plan as mumcp where mumcp.idusermobile = ? and mumcp.idcompany = ? and mumcp.idbranch = ?) ");
		sqlBuilder.append(" and mc.isdelete = false and mcp.isdelete = false ");
		final Object[] queryParameters = new Object[] { idusermobile,idcompany,idbranch };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCustomerCallPlanByIdCustomer(), queryParameters);
	}

	public List<CustomerCallPlanData> getListCustomerCallPlanPaging(long idusermobile,long idcompany,long idbranch,long limit, long offset) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCustomerCallPlanByIdCustomer().schema());
		sqlBuilder.append(" where mccp.idcallplan in (select idcallplan from m_user_mobile_call_plan as mumcp where mumcp.idusermobile = ? and mumcp.idcompany = ? and mumcp.idbranch = ?) ");
		sqlBuilder.append(" and mc.isdelete = false and mcp.isdelete = false ");
		sqlBuilder.append("order by mccp.idcallplan limit "+limit+" offset "+offset);
		final Object[] queryParameters = new Object[] { idusermobile,idcompany,idbranch };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCustomerCallPlanByIdCustomer(), queryParameters);
	}
	
	@Override
	public DownloadCustomerCallPlan getListCustomerCallPlanByIdUserPaging(long idusermobile, long idcompany,
			long idbranch, long limit, long offset) {
		// TODO Auto-generated method stub
		long size = getCountListCustomerCallPlanByIdUser(idusermobile,idcompany,idbranch).size();
		List<CustomerCallPlanData> list = getListCustomerCallPlanPaging(idusermobile,idcompany,idbranch,limit,offset);
		DownloadCustomerCallPlan data = new DownloadCustomerCallPlan();
		data.setSize(size);
		data.setCustomercallplan(list);
		
		return data;
	}

	@Override
	public boolean getCustCallPlanByPK(CustomerCallPlanPK custcallplanPK) {
		// TODO Auto-generated method stub
		Optional<CustomerCallPlan> value = repository.findById(custcallplanPK);
		if(value.isPresent()) {
			return true;
		}
		return false;
	}

}
