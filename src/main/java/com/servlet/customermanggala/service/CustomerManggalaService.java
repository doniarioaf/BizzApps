package com.servlet.customermanggala.service;

import java.util.List;

import com.servlet.customermanggala.entity.BodyCustomerManggala;
import com.servlet.customermanggala.entity.CustomerManggalaData;
import com.servlet.customermanggala.entity.CustomerManggalaTemplate;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoContactData;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoGudangData;
import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoKementerianData;
import com.servlet.shared.ReturnData;

public interface CustomerManggalaService {
	CustomerManggalaTemplate customerManggalaTemplate(long idcompany,long idbranch);
	List<CustomerManggalaData> getListAll(Long idcompany,Long idbranch);
	List<CustomerManggalaData> getListActive(Long idcompany,Long idbranch);
	CustomerManggalaData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveCustomerManggala(Long idcompany,Long idbranch,Long iduser,BodyCustomerManggala body);
	ReturnData updateCustomerManggala(Long idcompany,Long idbranch,Long iduser,Long id,BodyCustomerManggala body);
	ReturnData deleteCustomerManggala(Long idcompany,Long idbranch,Long iduser,Long id);
	List<DetailCustomerManggalaInfoKementerianData> getListDetailInfoKementerian(Long idcompany,Long idbranch,Long idcust);
	List<DetailCustomerManggalaInfoContactData> getListDetailInfoContact(Long idcompany,Long idbranch,Long idcust);
	List<DetailCustomerManggalaInfoGudangData> getListDetailInfoGudang(Long idcompany,Long idbranch,Long idcust);
}
