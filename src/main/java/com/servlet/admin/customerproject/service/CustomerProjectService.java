package com.servlet.admin.customerproject.service;

import java.util.List;

import com.servlet.admin.customerproject.entity.BodyCustomerProject;
import com.servlet.admin.customerproject.entity.CustomerProject;
import com.servlet.admin.rolepermissions.entity.RolePermissions;

public interface CustomerProjectService {
	Object saveCustomerProject(CustomerProject customerProject);
	Object saveCustomerProjectList(List<CustomerProject> listCustomerProject);
}
