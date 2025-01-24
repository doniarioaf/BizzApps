package com.servlet.customer.service;

import com.servlet.customer.entity.*;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface CustomerService {
    List<ListCustomerData> getListAll(Long idcompany, Long idbranch);
    CustomerData getDetail(Long id,Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyCustomer body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyCustomer body);
    ReturnData delete(Long id,Long iduser);
    List<CustomerGrup> getListCustomerGrup(Long idcompany, Long idbranch);
    List<CustomerForReport> getListCustomerForReport(Long idcompany, Long idbranch,String listId);
}
