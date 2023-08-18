package com.servlet.admin.usermobile.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.role.service.RoleService;
import com.servlet.admin.usermobile.entity.BodyUserMobile;
import com.servlet.admin.usermobile.entity.ReturnLoginMobile;
import com.servlet.admin.usermobile.entity.TemplateUserMobile;
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
import com.servlet.security.entity.AuthorizationData;
import com.servlet.security.entity.SecurityLicenseData;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.AESEncryptionDecryption;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConvertJson;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;


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
	RoleService roleService;
	
	
	
	@Override
	public Collection<UserMobilePermission> getListUserMobilePermission(long id) {
		// TODO Auto-generated method stub
		UserMobile mobile = repository.getById(id);
		if(!mobile.isIsdelete()) {
			final StringBuilder sqlBuilder = new StringBuilder("select " + new GetUserMobilePermission().schema());
			sqlBuilder.append(" where mur.idusermobile = ? and mr.isdelete = false and mur.isdelete = false ");
			final Object[] queryParameters = new Object[] { id };
			return this.jdbcTemplate.query(sqlBuilder.toString(), new GetUserMobilePermission(), queryParameters);
		}
		return new ArrayList<UserMobilePermission>();
	}

	@Override
	public ReturnLoginMobile actionLogin(String username, String password) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		UserMobileData userdata = null;
		ReturnData returndata = null;
//		List<UserMobile> list = repository.getUserLoginByUsername(username);
		List<UserMobileDataAuth> list = getUserLoginByUserNameV2(username);
		String tempusername = "";
		long idcompany = 0;
		long idbranch = 0;
		for(UserMobileDataAuth user : list) {
			String passwordDB = aesEncryptionDecryption.decrypt(user.getPassword());
			if(passwordDB.equals(password)) {
				tempusername = username;
				idcompany = user.getIdcompany();
				idbranch = user.getIdbranch();
				
				SecurityLicenseData license = securityService.checkLicense(user.getIdcompany(), null, null);
				returndata = new ReturnData();
				returndata = license.getReturnData();
				if(returndata.isSuccess()) {
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
//					userdata.setPermissions(setPermissions(user.getId()));
					userdata.setToken(encryptedString);
					
					UserMobile usermobil = repository.getById(user.getId());
					usermobil.setToken(encryptedString);
					repository.saveAndFlush(usermobil);
				}
				break;
			}
		}
		ReturnLoginMobile data = new ReturnLoginMobile();
		data.setUserMobileData(userdata);
		data.setReturnData(returndata);
		
		data.setUsername(tempusername);
		data.setIdcompany(idcompany);
		data.setIdbranch(idbranch);
		return data;
	}
	
	private List<UserMobileDataAuth> getUserLoginByUserNameV2(String username) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataUserMobileAuth().schema());
		sqlBuilder.append(" where mua.username = ? and mua.isdelete = false ");
		final Object[] queryParameters = new Object[] { username};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataUserMobileAuth(), queryParameters);
	}
	
	private List<UserMobileDataAuth> getListUserMobileByIdCompany(long idcompany) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataUserMobileAuth().schema());
		sqlBuilder.append(" where mua.idcompany = ? and mua.isdelete = false ");
		final Object[] queryParameters = new Object[] { idcompany};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataUserMobileAuth(), queryParameters);
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
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
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
		
		List<UserMobileDataAuth> listusermobileByCompany = getListUserMobileByIdCompany(idcompany);
		List<UserMobileDataAuth> checkUsername = getUserLoginByUserNameV2(usermobile.getUsername());
		long idreturn = 0;
		
		SecurityLicenseData checkLicense = securityService.checkLicense(idcompany, null,new Integer(listusermobileByCompany.size() + 1).longValue());
		if(!checkLicense.getReturnData().isSuccess()) {
			List<ValidationDataMessage> listvalidLicense = checkLicense.getReturnData().getValidations();
			if(listvalidLicense.size() > 0) {
				for(ValidationDataMessage licenseMsg : listvalidLicense) {
					if(!licenseMsg.getMessageCode().equals(ConstansCodeMessage.COMPANY_LICENSE_ALERT_EXPIRED)) {
						ValidationDataMessage msg = new ValidationDataMessage(licenseMsg.getMessageCode(),licenseMsg.getMessage());
						validations.add(msg);
					}
				}
			}
		}else if(checkUsername != null && checkUsername.size() > 0) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.USERNAME_IS_EXIST,"Username Sudah Terpakai");
			validations.add(msg);
		}else {
		
			UserMobile user = repository.saveAndFlush(table);
			idreturn = user.getId();
			
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
		}
		
		ReturnData data = new ReturnData();
		data.setId(idreturn);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData editUserMobile(long id, BodyUserMobile usermobile) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		Timestamp ts = new Timestamp(new Date().getTime());
		UserMobile table = repository.getById(id);
		table.setNama(usermobile.getNama());
		table.setContactnumber(usermobile.getNotelepon());
		table.setIsactive(usermobile.isIsactive());
		table.setEmail(usermobile.getEmail());
		table.setAddress(usermobile.getAddress());
		table.setModified(ts);
		
		boolean flagValidasiUserName = false;
		if(!table.getUsername().equals(usermobile.getUsername())) {
			List<UserMobileDataAuth> checkUsername = getUserLoginByUserNameV2(usermobile.getUsername());
			if(checkUsername != null && checkUsername.size() > 0) {
				flagValidasiUserName = true;
			}else {
				table.setUsername(usermobile.getUsername());
			}
		}
		if(flagValidasiUserName) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.USERNAME_IS_EXIST,"Username Sudah Terpakai");
			validations.add(msg);
		}else {
			
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
	}
		
		
		ReturnData data = new ReturnData();
		data.setId(id);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
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
	public List<UserMobileDataAuth> getUserLoginByUserNameMapper(String username,long idcompany,long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataUserMobileAuth().schema());
		sqlBuilder.append(" where mua.username = ? and mua.idcompany = ? and mua.idbranch = ? and mua.isdelete = false ");
		final Object[] queryParameters = new Object[] { username,idcompany,idbranch };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataUserMobileAuth(), queryParameters);
	}

	@Override
	public List<UserMobileListData> getListAllUserMobileForMonitoring(String listid, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		if(listid.equals("ALL")) {
			return getListAllUserMobile(idcompany,idbranch);
		}else {
			String valListId = "("+listid+")";
			final StringBuilder sqlBuilder = new StringBuilder("select " + new GetListAllUserMobile().schema());
			sqlBuilder.append(" where mua.id in "+valListId+" and mua.idcompany = ? and mua.idbranch = ? and mua.isdelete = false ");
			final Object[] queryParameters = new Object[] { idcompany , idbranch};
			return this.jdbcTemplate.query(sqlBuilder.toString(), new GetListAllUserMobile(), queryParameters);
		}
//		return null;
	}

	@Override
	public ReturnData deleteUserMobile(long id) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		UserMobile table = repository.getById(id);
		table.setIsdelete(true);
		table.setModified(ts);
		UserMobile returntable = repository.saveAndFlush(table);
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		return data;
	}

	@Override
	public TemplateUserMobile getTemplateUserMobile(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		TemplateUserMobile data = new TemplateUserMobile();
		data.setRoleoptions(roleService.getAllListRole(idcompany, idbranch));
		return data;
	}

}
