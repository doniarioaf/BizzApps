package com.servlet.admin.role.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servlet.admin.role.entity.BodyRole;
import com.servlet.admin.role.entity.Role;
import com.servlet.admin.role.entity.RoleDetail;
import com.servlet.admin.role.entity.RolePermissionData;
import com.servlet.admin.role.repo.RoleRepo;
import com.servlet.admin.role.service.RoleService;
import com.servlet.admin.rolepermissions.entity.RolePermissions;
import com.servlet.admin.rolepermissions.entity.RolePermissionsPK;
import com.servlet.admin.rolepermissions.service.RolePermissionService;
import com.servlet.shared.ReturnData;

@Service
public class RoleHandler implements RoleService{
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleHandler.class);
	@Autowired
	private RoleRepo repository;
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@Override
	public List<Role> getAllListRole(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		return repository.getAllRole(idcompany, idbranch);
	}

	@Override
	public ReturnData saveRole(BodyRole role,long idcompany,long idbranch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Role table = new Role();
		table.setNama(role.getNama());
		table.setDescriptions(role.getDescriptions());
		table.setIdcompany(idcompany);
		table.setIdbranch(idbranch);
		table.setIsdelete(false);
		table.setCreated(ts);
		table.setModified(ts);
		
		Role returntable = repository.saveAndFlush(table);
		
		List<RolePermissions> listrolepermissions = new ArrayList<RolePermissions>();
		if(role.getPermissions().length > 0) {
			for (int i = 0; i < role.getPermissions().length; i++) {
				RolePermissionsPK rolePermissionPK = new RolePermissionsPK();
				rolePermissionPK.setIdrole(returntable.getId());
				rolePermissionPK.setIdpermissions(role.getPermissions()[i]);
				rolePermissionPK.setIdbranch(returntable.getIdbranch());
				rolePermissionPK.setIdcompany(returntable.getIdcompany());
				
				RolePermissions rolePermission = new RolePermissions();
				rolePermission.setRolePermissionsPK(rolePermissionPK);
				
				listrolepermissions.add(rolePermission);
			}
			 rolePermissionService.saveRolePermissionsList(listrolepermissions);
		}
		
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		return data;
	}

	@Override
	public ReturnData updateRole(long id, BodyRole role) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Role table = repository.getById(id);
		table.setNama(role.getNama());
		table.setDescriptions(role.getDescriptions());
//		table.setIdcompany(1);
//		table.setIdbranch(1);
//		table.setIsdelete(false);
//		table.setCreated(ts);
		table.setModified(ts);
		
		Role returntable = repository.saveAndFlush(table);
		
		List<RolePermissionsPK> listdelete = new ArrayList<RolePermissionsPK>();
		List<RolePermissionData> listrolepermission = new ArrayList<RolePermissionData>(rolePermissionService.getListRolePermissions(id));
		if(listrolepermission.size() > 0) {
			for(RolePermissionData rolePermissionData : listrolepermission) {
				RolePermissionsPK rolepermissionPK = new RolePermissionsPK();
				rolepermissionPK.setIdcompany(returntable.getIdcompany());
				rolepermissionPK.setIdbranch(returntable.getIdbranch());
				rolepermissionPK.setIdpermissions(rolePermissionData.getId());
				rolepermissionPK.setIdrole(returntable.getId());
				listdelete.add(rolepermissionPK);
			}
			rolePermissionService.deleteAllrolePermissionsByListPK(listdelete);
		}
		
		List<RolePermissions> listrolepermissions = new ArrayList<RolePermissions>();
		if(role.getPermissions().length > 0) {
			for (int i = 0; i < role.getPermissions().length; i++) {
				RolePermissionsPK rolePermissionPK = new RolePermissionsPK();
				rolePermissionPK.setIdrole(returntable.getId());
				rolePermissionPK.setIdpermissions(role.getPermissions()[i]);
				rolePermissionPK.setIdbranch(returntable.getIdbranch());
				rolePermissionPK.setIdcompany(returntable.getIdcompany());
				
				RolePermissions rolePermission = new RolePermissions();
				rolePermission.setRolePermissionsPK(rolePermissionPK);
				
				listrolepermissions.add(rolePermission);
			}
			 rolePermissionService.saveRolePermissionsList(listrolepermissions);
		}
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		
		return data;
	}

	@Override
	public RoleDetail getRoleDetail(long id,long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		RoleDetail data = new RoleDetail();
		List<Role> value = repository.getRoleById(id, idcompany, idbranch);
		if(value != null && value.size() > 0) {
			List<RolePermissionData> listrolepermission = new ArrayList<RolePermissionData>(rolePermissionService.getListRolePermissions(id));
			data.setRole(value.get(0));
			data.setPermissions(listrolepermission);
		}else {
			data.setRole(null);
			data.setPermissions(null);
		}
		return data;
	}

	

}
