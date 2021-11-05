package com.servlet.admin.producttype.service;

import java.util.List;
import com.servlet.admin.producttype.entity.BodyProductType;
import com.servlet.admin.producttype.entity.ProductTypeData;
import com.servlet.shared.ReturnData;

public interface ProductTypeService {
	ReturnData saveProductType(BodyProductType producttype,long idcompany,long idbranch);
	ReturnData updateProductType(long id,BodyProductType producttype,long idcompany,long idbranch);
	List<ProductTypeData> getAllListProductType(long idcompany,long idbranch);
	ProductTypeData getProductTypeById(long id,long idcompany,long idbranch);
}
