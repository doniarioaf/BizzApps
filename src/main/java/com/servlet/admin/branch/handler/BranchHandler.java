package com.servlet.admin.branch.handler;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.branch.entity.BodyBranch;
import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.entity.BranchData;
import com.servlet.admin.branch.mapper.BranchMapper;
import com.servlet.admin.branch.mapper.GetListBranchData;
import com.servlet.admin.branch.repo.BranchRepo;
import com.servlet.admin.branch.service.BranchService;
import com.servlet.shared.ReturnData;

@Service
public class BranchHandler implements BranchService{
	private static final Logger LOGGER = LoggerFactory.getLogger(BranchHandler.class);
	@Autowired
	private BranchRepo repository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Branch> getListBranchActive() {
		// TODO Auto-generated method stub
		return repository.getListBranchActive();
	}

	@Override
	public List<Branch> getAllListBranch() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Branch getBranchByID(long id) {
		// TODO Auto-generated method stub
		Optional<Branch> value = repository.findById(id);
		if(value.isPresent()) {
			return value.get();
		}
		return null;
	}

	@Override
	public BranchData saveBranch(BodyBranch branch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Branch table = new Branch();
		table.setNama(branch.getNama());
		table.setCode(branch.getCode());
		table.setContactnumber(branch.getContactnumber());
		table.setDisplayname(branch.getDisplayname());
		table.setAddress(branch.getAddress());
		table.setIsactive(branch.isIsactive());
		table.setEmail(branch.getEmail());
		table.setCreated(ts);
		table.setModified(ts);
		table.setIsdelete(false);
		Branch returntable = repository.saveAndFlush(table);
		BranchData data = new BranchData();
		data.setId(returntable.getId());
		data.setName(branch.getNama());
		return data;
	}

	@Override
	public BranchData updateBranch(long id, BodyBranch branch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Branch table = repository.getById(id);
		table.setNama(branch.getNama());
		table.setContactnumber(branch.getContactnumber());
		table.setDisplayname(branch.getDisplayname());
		table.setAddress(branch.getAddress());
		table.setIsactive(branch.isIsactive());
		table.setEmail(branch.getEmail());
		table.setModified(ts);
		Branch returntable = repository.saveAndFlush(table);
		BranchData data = new BranchData();
		data.setId(returntable.getId());
		data.setName(branch.getNama());
		return data;
	}

	@Override
	public Collection<Branch> getListBranchActiveJdbc() {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new BranchMapper().schema());
		final Object[] queryParameters = new Object[] {  };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new BranchMapper(), queryParameters);
	}

	@Override
	public List<BranchData> getAllListBranchNotExistInCompany() {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetListBranchData().schema());
		sqlBuilder.append(" where mb.id not in(select mcb.idbranch from m_company_branch as mcb) and mb.isdelete = false ");
		final Object[] queryParameters = new Object[] {  };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetListBranchData(), queryParameters);
	}

	@Override
	public ReturnData deleteBranch(long id) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Branch table = repository.getById(id);
		table.setIsdelete(true);
		table.setModified(ts);
		Branch returntable = repository.saveAndFlush(table);
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		return data;
	}

	
}
