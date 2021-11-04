package com.servlet.admin.companybranch.handler;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.service.BranchService;
import com.servlet.admin.companybranch.entity.CompanyBranch;
import com.servlet.admin.companybranch.entity.CompanyBranchData;
import com.servlet.admin.companybranch.entity.CompanyBranchPK;
import com.servlet.admin.companybranch.mapper.GetCompanyBranchByIdCompany;
import com.servlet.admin.companybranch.repo.CompanyBranchRepo;
import com.servlet.admin.companybranch.service.CompanyBranchService;

@Service
public class CompanyBranchHandler implements CompanyBranchService{
	@Autowired
	private CompanyBranchRepo repository;
	@Autowired
	BranchService branchservice;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Object saveCompanyBranch(CompanyBranchPK companybranch) {
		// TODO Auto-generated method stub
		
		CompanyBranch table = new CompanyBranch();
		Branch branch = branchservice.getBranchByID(companybranch.getIdbranch());
		if(branch != null) {
			table.setCompanyBranchPK(companybranch);
			repository.saveAndFlush(table);
		}
		
		
		return null;
	}

	@Override
	public Collection<CompanyBranchData> getListCompanyBranch(long idcompany) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCompanyBranchByIdCompany().schema());
		sqlBuilder.append(" where mcb.idcompany = ? ");
		final Object[] queryParameters = new Object[] { idcompany };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCompanyBranchByIdCompany(), queryParameters);
	}

	@Override
	public Object deleteAllCompanyBranchByListPK(List<CompanyBranchPK> listPK) {
		// TODO Auto-generated method stub
		if(listPK.size() > 0) {
			repository.deleteAllById(listPK);
		}
		return null;
	}

	@Override
	public Object saveCompanyBranchList(List<CompanyBranch> listcompanybranch) {
		// TODO Auto-generated method stub
		if(listcompanybranch.size() > 0) {
			repository.saveAllAndFlush(listcompanybranch);
		}
		return null;
	}

	
}
