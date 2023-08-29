package com.servlet.product.service;

import java.util.List;

import com.servlet.product.entity.BodyProduct;
import com.servlet.product.entity.ProductData;
import com.servlet.shared.ReturnData;

public interface ProductService {
	List<ProductData> getListProduct(long idcompany, long idbranch);
	ProductData getById(long idcompany, long idbranch,long id);
	ReturnData saveProduct(BodyProduct body,long idcompany,long idbranch,long iduser);
	ReturnData updateProduct(long id,BodyProduct body,long idcompany,long idbranch,long iduser);
	ReturnData deleteProduct(long id,long idcompany,long idbranch,long iduser);
}
