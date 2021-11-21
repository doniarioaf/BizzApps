package com.servlet.admin.customer.service;

import java.util.List;

import com.servlet.admin.customer.entity.BodyCustomer;
import com.servlet.admin.customer.entity.CustomerDetailData;
import com.servlet.admin.customer.entity.CustomerListData;
import com.servlet.admin.customer.entity.CustomerTemplate;
import com.servlet.shared.ReturnData;

public interface CustomerService {
	ReturnData saveCustomer(BodyCustomer customer,long idcompany,long idbranch);
	ReturnData updateCustomer(long id,BodyCustomer customer,long idcompany,long idbranch);
	List<CustomerListData> getAllListCustomer(long idcompany,long idbranch);
	CustomerDetailData getCustomerById(long id,long idcompany,long idbranch);
	CustomerTemplate customerTemplate(long idcompany,long idbranch);
}
