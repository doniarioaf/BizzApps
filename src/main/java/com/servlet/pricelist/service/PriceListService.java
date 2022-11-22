package com.servlet.pricelist.service;

import java.util.List;

import com.servlet.pricelist.entity.BodyPriceList;
import com.servlet.pricelist.entity.BodySearchPriceList;
import com.servlet.pricelist.entity.PriceListData;
import com.servlet.pricelist.entity.PriceListTemplate;
import com.servlet.shared.ReturnData;

public interface PriceListService {
	List<PriceListData> getListAll(Long idcompany,Long idbranch);
	List<PriceListData> getListActive(Long idcompany,Long idbranch);
	PriceListData getById(Long idcompany,Long idbranch,Long id);
	PriceListData getDataWithTemplateById(Long idcompany,Long idbranch,Long id);
	PriceListTemplate getTemplate(Long idcompany,Long idbranch);
	ReturnData savePriceList(Long idcompany,Long idbranch,Long iduser,BodyPriceList body);
	ReturnData updatePriceList(Long idcompany,Long idbranch,Long iduser,Long id,BodyPriceList body);
	ReturnData deletePriceList(Long idcompany,Long idbranch,Long iduser,Long id);
	List<PriceListData> getListSearch(Long idcompany,Long idbranch,BodySearchPriceList body);
	List<PriceListData> getListPriceListByIdCustomer(Long idcompany,Long idbranch,Long idcustomer,Long idwarehouse,Long idinvoicetype,String jalur);
}
