package com.servlet.admin.usermobile.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.admin.usermobile.entity.BodyUserMobile;
import com.servlet.admin.usermobile.entity.UserDetailMobile;
import com.servlet.admin.usermobile.entity.UserMobile;
import com.servlet.admin.usermobile.entity.UserMobileData;
import com.servlet.admin.usermobile.entity.UserMobileDataAuth;
import com.servlet.admin.usermobile.entity.UserMobileListData;
import com.servlet.admin.usermobile.entity.UserMobilePermission;
import com.servlet.admin.usermobile.mapper.GetDataUserMobileAuth;
import com.servlet.admin.usermobile.mapper.GetListAllUserMobile;
import com.servlet.admin.usermobile.mapper.GetUserMobilePermission;
import com.servlet.admin.usermobile.repo.UserMobileRepo;
import com.servlet.admin.usermobile.service.UserMobileService;
import com.servlet.admin.usermobilerole.entity.UserMobileRole;
import com.servlet.admin.usermobilerole.entity.UserMobileRoleData;
import com.servlet.admin.usermobilerole.entity.UserMobileRolePK;
import com.servlet.admin.usermobilerole.service.UserMobileRoleService;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlan;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanData;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanPK;
import com.servlet.mobile.usermobilecallplan.service.UserMobileCallPlanService;
import com.servlet.security.entity.AuthorizationData;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.AESEncryptionDecryption;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConvertJson;
import com.servlet.shared.ReturnData;


@Service
public class UserMobileHandler implements UserMobileService{
	@Autowired
	private UserMobileRepo repository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	SecurityService securityService;
	@Autowired
	UserMobileRoleService userMobileRoleService;
	@Autowired
	UserMobileCallPlanService userMobileCallPlanService;
	
	@Override
	public Collection<UserMobilePermission> getListUserMobilePermission(long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetUserMobilePermission().schema());
		sqlBuilder.append(" where mur.idusermobile = ? ");
		final Object[] queryParameters = new Object[] { id };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetUserMobilePermission(), queryParameters);
	}

	@Override
	public UserMobileData actionLogin(String username, String password) {
		// TODO Auto-generated method stub
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		UserMobileData userdata = null;
		List<UserMobile> list = repository.getUserLoginByUsername(username);
		for(UserMobile user : list) {
			String passwordDB = aesEncryptionDecryption.decrypt(user.getPassword());
			if(passwordDB.equals(password)) {
				AuthorizationData dataauth = new AuthorizationData();
				String passwordtoken = securityService.passwordToken(username, password);
				String encryptedPassToken = aesEncryptionDecryption.encrypt(passwordtoken);
				Timestamp ts = new Timestamp(new Date().getTime());
				dataauth.setId(user.getId());
				dataauth.setUsername(username);
				dataauth.setPassword(password);
				dataauth.setDatelogin(ts);
				dataauth.setPasswordtoken(encryptedPassToken);
				dataauth.setIdcompany(user.getIdcompany());
				dataauth.setIdbranch(user.getIdbranch());
				dataauth.setTypelogin(ConstansKey.TYPE_MOBILE);
				String encryptedString = aesEncryptionDecryption.encrypt(new ConvertJson().toJsonString(dataauth));
				
				userdata = new UserMobileData();
				userdata.setPermissions(setPermissions(user.getId()));
				userdata.setToken(encryptedString);
				
				UserMobile usermobil = repository.getById(user.getId());
				usermobil.setToken(encryptedString);
				repository.saveAndFlush(usermobil);
				break;
				
			}
		}
		return userdata;
	}
	
	private List<String> setPermissions(long id){
		List<String> list = new ArrayList<String>();
		List<UserMobilePermission> listp = new ArrayList<UserMobilePermission>(getListUserMobilePermission(id));
		for(UserMobilePermission permission : listp) {
			list.add(permission.getPermissioncode());
		}
		return list;
	}

	@Override
	public List<UserMobile> getUserLoginByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnData saveUserMobile(BodyUserMobile usermobile, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		String passwordDB = aesEncryptionDecryption.encrypt(usermobile.getPassword());
		Timestamp ts = new Timestamp(new Date().getTime());
		UserMobile table = new UserMobile();
		table.setUsername(usermobile.getUsername());
		table.setPassword(passwordDB);
		table.setNama(usermobile.getNama());
		table.setContactnumber(usermobile.getNotelepon());
		table.setIsactive(true);
		table.setIdcompany(idcompany);
		table.setIdbranch(idbranch);
		table.setEmail(usermobile.getEmail());
		table.setAddress(usermobile.getAddress());
		table.setIsdelete(false);
		table.setCreated(ts);
		table.setModified(ts);
		table.setImei(usermobile.getImei());
		
		UserMobile user = repository.saveAndFlush(table);
		
		List<UserMobileRole> listsave = new ArrayList<UserMobileRole>();
		if(usermobile.getRoles().length > 0) {
			for (int i = 0; i < usermobile.getRoles().length; i++) {
				UserMobileRolePK pk = new UserMobileRolePK();
				pk.setIdrole(usermobile.getRoles()[i]);
				pk.setIdusermobile(user.getId());
				UserMobileRole userMobileRole = new UserMobileRole();
				userMobileRole.setUserMobileRolePK(pk);
				listsave.add(userMobileRole);
			}
			userMobileRoleService.saveUserMobileRoleList(listsave);
		}
		
		List<UserMobileCallPlan> listcallplansave = new ArrayList<UserMobileCallPlan>();
		if(usermobile.getCallplans().length > 0) {
			for (int i = 0; i < usermobile.getCallplans().length; i++) {
				UserMobileCallPlanPK pk = new UserMobileCallPlanPK();
				pk.setIdcompany(idcompany);
				pk.setIdbranch(idbranch);
				pk.setIdusermobile(user.getId());
				pk.setIdcallplan(usermobile.getCallplans()[i]);
				UserMobileCallPlan usermobilecallplan = new UserMobileCallPlan();
				usermobilecallplan.setUserMobileCallPlanPK(pk);
				listcallplansave.add(usermobilecallplan);
			}
			userMobileCallPlanService.saveUserMobileCallPlanList(listcallplansave);
		}
		
		ReturnData data = new ReturnData();
		data.setId(user.getId());
		
		return data;
	}

	@Override
	public ReturnData editUserMobile(long id, BodyUserMobile usermobile) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		UserMobile table = repository.getById(id);
		table.setUsername(usermobile.getUsername());
		table.setNama(usermobile.getNama());
		table.setContactnumber(usermobile.getNotelepon());
		table.setIsactive(usermobile.isIsactive());
		table.setEmail(usermobile.getEmail());
		table.setAddress(usermobile.getAddress());
		table.setModified(ts);
		repository.saveAndFlush(table);
		
		List<UserMobileRolePK> listdelete = new ArrayList<UserMobileRolePK>();
		List<UserMobileRoleData> listroleuser = new ArrayList<UserMobileRoleData>(userMobileRoleService.getListUserMobileRole(id));
		if(listroleuser.size() > 0) {
			for(UserMobileRoleData data : listroleuser) {
				UserMobileRolePK pk = new UserMobileRolePK();
				pk.setIdrole(data.getId());
				pk.setIdusermobile(id);
				listdelete.add(pk);
			}
			userMobileRoleService.deleteAllUserMobileRolePKPK(listdelete);
		}
		
		List<UserMobileCallPlanPK> listcallplandelete = new ArrayList<UserMobileCallPlanPK>();
		List<UserMobileCallPlanData> listcallplanuser = new ArrayList<UserMobileCallPlanData>(userMobileCallPlanService.getListUserMobileCallPlan(id));
		if(listcallplanuser.size() > 0) {
			for(UserMobileCallPlanData userMobileCallPlanData : listcallplanuser) {
				UserMobileCallPlanPK pk = new UserMobileCallPlanPK();
				pk.setIdcompany(table.getIdcompany());
				pk.setIdbranch(table.getIdbranch());
				pk.setIdcallplan(userMobileCallPlanData.getIdcallplan());
				pk.setIdusermobile(id);
				listcallplandelete.add(pk);
			}
			userMobileCallPlanService.deleteAllUserMobileCallPlanByListPK(listcallplandelete);
		}
		
		List<UserMobileRole> listsave = new ArrayList<UserMobileRole>();
		if(usermobile.getRoles().length > 0) {
			for (int i = 0; i < usermobile.getRoles().length; i++) {
				UserMobileRolePK pk = new UserMobileRolePK();
				pk.setIdrole(usermobile.getRoles()[i]);
				pk.setIdusermobile(id);
				UserMobileRole userMobileRole = new UserMobileRole();
				userMobileRole.setUserMobileRolePK(pk);
				listsave.add(userMobileRole);
			}
			userMobileRoleService.saveUserMobileRoleList(listsave);
		}
		
		List<UserMobileCallPlan> listuserMobileCallPlansave = new ArrayList<UserMobileCallPlan>();
		if(usermobile.getCallplans().length > 0) {
			for (int i = 0; i < usermobile.getCallplans().length; i++) {
				UserMobileCallPlanPK pk = new UserMobileCallPlanPK();
				pk.setIdcompany(table.getIdcompany());
				pk.setIdbranch(table.getIdbranch());
				pk.setIdcallplan(usermobile.getCallplans()[i]);
				pk.setIdusermobile(id);
				UserMobileCallPlan userMobileCallPlan = new UserMobileCallPlan();
				userMobileCallPlan.setUserMobileCallPlanPK(pk);
				listuserMobileCallPlansave.add(userMobileCallPlan);
			}
			userMobileCallPlanService.saveUserMobileCallPlanList(listuserMobileCallPlansave);
		}
		
		ReturnData data = new ReturnData();
		data.setId(id);
		
		return data;
	}

	@Override
	public UserDetailMobile getDetailUserMobile(long id, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		List<UserMobile> list = repository.getUserById(id, idcompany, idbranch);
		if(list != null && list.size() > 0) {
			List<UserMobileRoleData> listroleuser = new ArrayList<UserMobileRoleData>(userMobileRoleService.getListUserMobileRole(id));
			UserMobile data = list.get(0);
			data.setToken("");
			UserDetailMobile user = new UserDetailMobile();
			user.setUser(data);
			user.setRoles(listroleuser);
			return user;
		}
		return null;
	}

	@Override
	public List<UserMobileListData> getListAllUserMobile(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetListAllUserMobile().schema());
		sqlBuilder.append(" where mua.idcompany = ? and mua.idbranch = ? and mua.isdelete = false ");
		final Object[] queryParameters = new Object[] { idcompany , idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetListAllUserMobile(), queryParameters);
	}

	@Override
	public List<UserMobileDataAuth> getUserLoginByUserNameMapper(String username) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataUserMobileAuth().schema());
		sqlBuilder.append(" where mua.username = ? ");
		final Object[] queryParameters = new Object[] { username };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataUserMobileAuth(), queryParameters);
	}

}
