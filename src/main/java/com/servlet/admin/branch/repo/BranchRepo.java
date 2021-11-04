package com.servlet.admin.branch.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servlet.admin.branch.entity.Branch;


@Repository("BranchRepo")
public interface BranchRepo extends JpaRepository<Branch, Long>{
	
	@Query(value =" select * from m_branch "
			+ " where isactive = true and isdelete = false ",nativeQuery = true)
	public List<Branch> getListBranchActive();
}
