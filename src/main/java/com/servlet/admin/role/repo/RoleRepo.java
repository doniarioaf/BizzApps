package com.servlet.admin.role.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servlet.admin.role.entity.Role;
import com.servlet.user.entity.UserApps;

@Repository("RoleRepo")
public interface RoleRepo extends JpaRepository<Role, Long>{
	
	@Query(value =" select * from m_role "
			+ " where id = :idrole and idcompany = :companyid and idbranch = :branchid and isdelete = false ",nativeQuery = true)
	public List<Role> getRoleById(@Param("idrole") long idrole,@Param("companyid") long companyid,@Param("branchid") long branchid);
	
	@Query(value =" select * from m_role "
			+ " where idcompany = :companyid and idbranch = :branchid and isdelete = false ",nativeQuery = true)
	public List<Role> getAllRole(@Param("companyid") long companyid,@Param("branchid") long branchid);
}
