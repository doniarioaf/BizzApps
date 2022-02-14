package com.servlet.admin.customerproject.handler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.customerproject.entity.CustomerProject;
import com.servlet.admin.customerproject.entity.CustomerProjectData;
import com.servlet.admin.customerproject.entity.CustomerProjectPK;
import com.servlet.admin.customerproject.mapper.GetCustomerProjectJoinTable;
import com.servlet.admin.customerproject.repo.CustomerProjectRepo;
import com.servlet.admin.customerproject.service.CustomerProjectService;

@Service
public class CustomerProjectHandler implements CustomerProjectService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CustomerProjectRepo repository;

	@Override
	public Object saveCustomerProject(CustomerProject customerProject) {
		// TODO Auto-generated method stub
		return repository.save(customerProject);
	}

	@Override
	public Object saveCustomerProjectList(List<CustomerProject> listCustomerProject) {
		// TODO Auto-generated method stub
		return repository.saveAllAndFlush(listCustomerProject);
	}

	@Override
	public List<CustomerProjectData> getListCustomerProject(long idproject) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCustomerProjectJoinTable().schema());
		sqlBuilder.append(" where mccp.idproject = ? and mc.isdelete = false ");
		final Object[] queryParameters = new Object[] { idproject };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCustomerProjectJoinTable(), queryParameters);
	}

	@Override
	public Object deleteAllCustomeProjectByListPK(List<CustomerProjectPK> listPK) {
		// TODO Auto-generated method stub
		if(listPK.size() > 0) {
			repository.deleteAllById(listPK);
		}
		return null;
	}

	@Override
	public boolean getCustProjectByPK(CustomerProjectPK customerProjectPK) {
		// TODO Auto-generated method stub
		Optional<CustomerProject> value = repository.findById(customerProjectPK);
		if(value.isPresent()) {
			return true;
		}
		return false;
	}

}
