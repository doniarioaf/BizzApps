package com.servlet.admin.customerproject.service;

import java.util.List;

import com.servlet.admin.customerproject.entity.CustomerProject;
import com.servlet.admin.customerproject.entity.CustomerProjectData;
import com.servlet.admin.customerproject.entity.CustomerProjectPK;

public interface CustomerProjectService {
	Object saveCustomerProject(CustomerProject customerProject);
	Object saveCustomerProjectList(List<CustomerProject> listCustomerProject);
	List<CustomerProjectData> getListCustomerProject(long idproject);
	Object deleteAllCustomeProjectByListPK(List<CustomerProjectPK> listPK);
	boolean getCustProjectByPK(CustomerProjectPK customerProjectPK);
}
