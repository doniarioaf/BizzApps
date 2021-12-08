package com.servlet.admin.customertype.service;
import java.util.List;
import com.servlet.admin.customertype.entity.BodyCustomerType;
import com.servlet.admin.customertype.entity.CustomerTypeData;
import com.servlet.shared.ReturnData;

public interface CustomerTypeService {
	ReturnData saveCustomerType(BodyCustomerType customertype,long idcompany,long idbranch);
	ReturnData updateCustomerType(long id,BodyCustomerType customertype,long idcompany,long idbranch);
	List<CustomerTypeData> getAllListCustomerType(long idcompany,long idbranch);
	CustomerTypeData getCustomerTypeById(long id,long idcompany,long idbranch);
	ReturnData deleteCustomerType(long id);
}
