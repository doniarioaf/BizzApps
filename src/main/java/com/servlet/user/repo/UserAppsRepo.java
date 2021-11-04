package com.servlet.user.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servlet.user.entity.UserApps;
import com.servlet.user.parameter.ParamUser;


@Repository("UserAppsRepo")
public interface UserAppsRepo extends JpaRepository<UserApps, Long>{
	@Query(value =" select * from m_user_apps "
			+ " where username = :#{#params.username} and password = :#{#params.password} and isdelete = false  ",nativeQuery = true)
	public List<UserApps> getUserLogin(@Param("params") ParamUser params);
	
	@Query(value =" select * from m_user_apps "
			+ " where username = :user and isdelete = false ",nativeQuery = true)
	public List<UserApps> getUserLoginByUsername(@Param("user") String user);
	
	@Query(value =" select * from m_user_apps "
			+ " where id = :iduser and idcompany = :companyid and idbranch = :branchid and isdelete = false ",nativeQuery = true)
	public List<UserApps> getUserById(@Param("iduser") long user,@Param("companyid") long companyid,@Param("branchid") long branchid);
}
