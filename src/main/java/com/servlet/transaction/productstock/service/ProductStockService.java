package com.servlet.transaction.productstock.service;

import com.servlet.shared.ReturnData;
import com.servlet.transaction.productstock.entity.ProductStockData;

public interface ProductStockService {
	ProductStockData getProductStock(long idproduct,long idcompany,long idbranch);
	ReturnData addStock(long idproduct,long stock,long idcompany,long idbranch,long userid);
	ReturnData substractStock(long idproduct,long stock,long idcompany,long idbranch,long userid);
	ReturnData rejectStock(long idproduct,long stock,long idcompany,long idbranch,long userid,String reason);
}
