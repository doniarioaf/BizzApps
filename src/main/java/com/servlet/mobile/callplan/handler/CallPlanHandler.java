package com.servlet.mobile.callplan.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.admin.customer.service.CustomerService;
import com.servlet.mobile.callplan.entity.BodyCallPlan;
import com.servlet.mobile.callplan.entity.CallPlan;
import com.servlet.mobile.callplan.entity.CallPlanDetailData;
import com.servlet.mobile.callplan.entity.CallPlanListData;
import com.servlet.mobile.callplan.entity.TemplateDataCallPlan;
import com.servlet.mobile.callplan.mapper.GetCallPlanList;
import com.servlet.mobile.callplan.repo.CallPlanRepo;
import com.servlet.mobile.callplan.service.CallPlanService;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlan;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanPK;
import com.servlet.mobile.customercallplan.service.CustomerCallPlanService;
import com.servlet.mobile.project.entity.ProjectData;
import com.servlet.mobile.project.service.ProjectService;
import com.servlet.shared.ReturnData;

@Service
public class CallPlanHandler implements CallPlanService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CallPlanRepo repository;
	@Autowired
	private CustomerCallPlanService customerCallPlanService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProjectService projectService;
	
	@Override
	public ReturnData saveCallPlan(BodyCallPlan callplan, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		CallPlan table = new CallPlan();
		table.setIdcompany(idcompany);
		table.setIdbranch(idbranch);
		table.setIsdelete(false);
		table.setCreated(ts);
		table.setModified(ts);
		table.setNama(callplan.getNama());
		table.setDescription(callplan.getDescription());
		table.setIdproject(callplan.getIdproject());
		
		CallPlan returntable = repository.saveAndFlush(table);
		List<CustomerCallPlan> listcustcallplan = new ArrayList<CustomerCallPlan>();
		if(callplan.getCustomers().length > 0) {
			for (int i = 0; i < callplan.getCustomers().length; i++) {
				CustomerCallPlanPK pk = new CustomerCallPlanPK();
				pk.setIdcallplan(returntable.getId());
				pk.setIdcompany(idcompany);
				pk.setIdbranch(idbranch);
				pk.setIdcustomer(callplan.getCustomers()[i]);
				CustomerCallPlan custcallplan = new CustomerCallPlan();
				custcallplan.setCustomerCallPlanPK(pk);
				listcustcallplan.add(custcallplan);
			}
			customerCallPlanService.saveCustomerCallPlanList(listcustcallplan);
		}
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		return data;
	}
	@Override
	public ReturnData updateCallPlan(long id, BodyCallPlan callplan, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		CallPlanListData check = checkCallPlanById(id,idcompany,idbranch);
		if(check != null) {
			CallPlan table = repository.getById(id);
			table.setModified(ts);
			table.setNama(callplan.getNama());
			table.setDescription(callplan.getDescription());
			table.setIdproject(callplan.getIdproject());
			CallPlan returntable = repository.saveAndFlush(table);
			
			List<CustomerCallPlanPK> listdelete = new ArrayList<CustomerCallPlanPK>();
			List<CustomerCallPlanData> listcustcallplan = new ArrayList<CustomerCallPlanData>(customerCallPlanService.getListCustomerCallPlan(id));
			if(listcustcallplan != null && listcustcallplan.size() > 0) {
				for(CustomerCallPlanData customerCallPlanData : listcustcallplan) {
					CustomerCallPlanPK custcallplanPK = new CustomerCallPlanPK();
					custcallplanPK.setIdcallplan(id);
					custcallplanPK.setIdcompany(idcompany);
					custcallplanPK.setIdbranch(idbranch);
					custcallplanPK.setIdcustomer(customerCallPlanData.getId());
					listdelete.add(custcallplanPK);
				}
				customerCallPlanService.deleteAllCustomeCallPlanByListPK(listdelete);
			}
			
			List<CustomerCallPlan> listsave = new ArrayList<CustomerCallPlan>();
			if(callplan.getCustomers().length > 0) {
				for (int i = 0; i < callplan.getCustomers().length; i++) {
					CustomerCallPlanPK pk = new CustomerCallPlanPK();
					pk.setIdcallplan(returntable.getId());
					pk.setIdcompany(idcompany);
					pk.setIdbranch(idbranch);
					pk.setIdcustomer(callplan.getCustomers()[i]);
					CustomerCallPlan custcallplan = new CustomerCallPlan();
					custcallplan.setCustomerCallPlanPK(pk);
					listsave.add(custcallplan);
				}
				customerCallPlanService.saveCustomerCallPlanList(listsave);
			}
			
			
			ReturnData data = new ReturnData();
			data.setId(returntable.getId());
			return data;
		}
		
		return null;
	}
	
	public CallPlanListData checkCallPlanById(long id,long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCallPlanList().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id, idcompany,idbranch};
		List<CallPlanListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetCallPlanList(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<CallPlanListData> getAllListCallPlan(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCallPlanList().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCallPlanList(), queryParameters);
	}
	@Override
	public CallPlanDetailData getCallPlanById(long id, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCallPlanList().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id, idcompany,idbranch};
		List<CallPlanListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetCallPlanList(), queryParameters);
		if(list != null && list.size() > 0) {
			List<CustomerCallPlanData> listcustcallplan = new ArrayList<CustomerCallPlanData>(customerCallPlanService.getListCustomerCallPlan(id));
			CallPlanListData callplan = list.get(0);
			
			String projectName = "";
			ProjectData project = projectService.getProjectById(callplan.getIdproject(), idcompany, idbranch);
			if(project != null) {
				projectName = project.getNama();
			}
			CallPlanDetailData datacallplan = new CallPlanDetailData();
			datacallplan.setId(id);
			datacallplan.setNama(callplan.getNama());
			datacallplan.setDescription(callplan.getDescription());
			datacallplan.setCustomers(listcustcallplan);
			datacallplan.setProjectname(projectName);
			datacallplan.setIdproject(callplan.getIdproject());
			return datacallplan;
		}
		return null;
	}
	@Override
	public TemplateDataCallPlan getTemplate(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		TemplateDataCallPlan template = new TemplateDataCallPlan();
		template.setCustomerOptions(customerService.getAllListCustomer(idcompany, idbranch));
		template.setProjectoptions(projectService.getAllListProject(idcompany, idbranch));
		return template;
	}
	@Override
	public ReturnData deleteCallPlan(long id) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		CallPlan table = repository.getById(id);
		table.setIsdelete(true);
		table.setModified(ts);
		CallPlan returntable = repository.saveAndFlush(table);
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		return data;
	}
	@Override
	public CallPlanListData getCallByName(String nama, long idcompany, long idbranch) {
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCallPlanList().schema());
		sqlBuilder.append(" where data.nama = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {nama, idcompany,idbranch};
		List<CallPlanListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetCallPlanList(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
