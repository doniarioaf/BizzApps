package com.servlet.admin.customerproject.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servlet.admin.customerproject.entity.CustomerProject;
import com.servlet.admin.customerproject.repo.CustomerProjectRepo;
import com.servlet.admin.customerproject.service.CustomerProjectService;

@Service
public class CustomerProjectHandler implements CustomerProjectService{
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

}
