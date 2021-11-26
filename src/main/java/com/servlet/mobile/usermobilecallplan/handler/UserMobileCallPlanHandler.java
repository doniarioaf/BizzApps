package com.servlet.mobile.usermobilecallplan.handler;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;
import com.servlet.mobile.customercallplan.service.CustomerCallPlanService;
import com.servlet.mobile.usermobilecallplan.entity.DownloadUserMobileCallPlan;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlan;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanData;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanDataMobile;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanPK;
import com.servlet.mobile.usermobilecallplan.mapper.GetCountSizeListUserMobileCallPlanByIdUserMobile;
import com.servlet.mobile.usermobilecallplan.mapper.GetUserMobileCallPlanByIdUser;
import com.servlet.mobile.usermobilecallplan.repo.UserMobileCallPlanRepo;
import com.servlet.mobile.usermobilecallplan.service.UserMobileCallPlanService;
import com.servlet.shared.ReturnData;

@Service
public class UserMobileCallPlanHandler implements UserMobileCallPlanService{
	@Autowired
	private UserMobileCallPlanRepo repository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CustomerCallPlanService customerCallPlanService;
	
	@Override
	public Object saveUserMobileCallPlan(UserMobileCallPlanPK userMobileCallPlanPK) {
		// TODO Auto-generated method stub
		UserMobileCallPlan table = new UserMobileCallPlan();
		table.setUserMobileCallPlanPK(userMobileCallPlanPK);
		UserMobileCallPlan returntable = repository.saveAndFlush(table);
		ReturnData data = new ReturnData();
		data.setId(returntable.getUserMobileCallPlanPK().getIdusermobile());
		return data;
	}
	@Override
	public Object saveUserMobileCallPlanList(List<UserMobileCallPlan> list) {
		// TODO Auto-generated method stub
		if(list.size() > 0) {
			repository.saveAllAndFlush(list);
		}
		return null;
	}
	@Override
	public Collection<UserMobileCallPlanData> getListUserMobileCallPlan(long idusermobile) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetUserMobileCallPlanByIdUser().schema());
		sqlBuilder.append(" where mccp.idusermobile = ? ");
		final Object[] queryParameters = new Object[] { idusermobile };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetUserMobileCallPlanByIdUser(), queryParameters);
	}
	
	private List<UserMobileCallPlanData> getListUserMobileCallPlanV2(long idusermobile,long idcompany,long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetUserMobileCallPlanByIdUser().schema());
		sqlBuilder.append(" where mccp.idusermobile = ? and mccp.idcompany = ? and mccp.idbranch = ? ");
		final Object[] queryParameters = new Object[] { idusermobile,idcompany,idbranch };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetUserMobileCallPlanByIdUser(), queryParameters);
	}
	
	private List<Long> getSizeMobileCallPlanByIdUserMobile(long idusermobile,long idcompany,long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCountSizeListUserMobileCallPlanByIdUserMobile().schema());
		sqlBuilder.append(" where mccp.idusermobile = ? and mccp.idcompany = ? and mccp.idbranch = ? ");
		final Object[] queryParameters = new Object[] { idusermobile,idcompany,idbranch };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCountSizeListUserMobileCallPlanByIdUserMobile(), queryParameters);
	}
	
	@Override
	public Object deleteAllUserMobileCallPlanByListPK(List<UserMobileCallPlanPK> listPK) {
		// TODO Auto-generated method stub
		if(listPK.size() > 0) {
			repository.deleteAllById(listPK);
		}
		return null;
	}
	@Override
	public UserMobileCallPlanDataMobile getDataForMobile(long idusermobile, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		List<UserMobileCallPlanData> listcallplan = getListUserMobileCallPlanV2(idusermobile,idcompany,idbranch);
		List<CustomerCallPlanData> listcustcallplan = customerCallPlanService.getListCustomerCallPlanByIdUser(idusermobile, idcompany, idbranch);
		UserMobileCallPlanDataMobile data = new UserMobileCallPlanDataMobile();
		data.setCallplans(listcallplan);
		data.setCustomercallplans(listcustcallplan);
		return data;
	}
	@Override
	public DownloadUserMobileCallPlan downloadUserMobileCallPlanByIdUserMobile(long idusermobile, long idcompany,
			long idbranch, long limit, long offset) {
		// TODO Auto-generated method stub
		long size = getSizeMobileCallPlanByIdUserMobile(idusermobile,idcompany,idbranch).size();
		List<UserMobileCallPlanData> list = getListUserMobileCallPlanPaging(idusermobile,idcompany,idbranch,limit,offset);
		DownloadUserMobileCallPlan data = new DownloadUserMobileCallPlan();
		data.setSize(size);
		data.setCallplans(list);
		
		return data;
	}
	private List<UserMobileCallPlanData> getListUserMobileCallPlanPaging(long idusermobile,long idcompany,long idbranch,long limit, long offset) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetUserMobileCallPlanByIdUser().schema());
		sqlBuilder.append(" where mccp.idusermobile = ? and mccp.idcompany = ? and mccp.idbranch = ? ");
		sqlBuilder.append(" limit "+limit+" offset "+offset);
		final Object[] queryParameters = new Object[] { idusermobile,idcompany,idbranch };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetUserMobileCallPlanByIdUser(), queryParameters);
	}
	

}
