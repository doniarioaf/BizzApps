package com.servlet.admin.usermobile.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servlet.admin.usermobile.entity.UserMobile;

@Repository("UserMobileRepo")
public interface UserMobileRepo extends JpaRepository<UserMobile, Long>{
	
	@Query(value =" select * from m_user_mobile "
			+ " where username = :user and isdelete = false ",nativeQuery = true)
	public List<UserMobile> getUserLoginByUsername(@Param("user") String user);
	
	@Query(value =" select * from m_user_mobile "
			+ " where id = :iduser and idcompany = :companyid and idbranch = :branchid and isdelete = false ",nativeQuery = true)
	public List<UserMobile> getUserById(@Param("iduser") long user,@Param("companyid") long companyid,@Param("branchid") long branchid);
}
