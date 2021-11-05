package com.servlet.admin.product.service;

import java.util.List;

import com.servlet.admin.product.entity.BodyProduct;
import com.servlet.admin.product.entity.ProductDetailData;
import com.servlet.admin.product.entity.ProductListData;
import com.servlet.shared.ReturnData;

public interface ProductService {
	ReturnData saveProduct(BodyProduct product,long idcompany,long idbranch);
	ReturnData updateProduct(long id,BodyProduct product,long idcompany,long idbranch);
	List<ProductListData> getAllListProduct(long idcompany,long idbranch);
	ProductDetailData getProductById(long id,long idcompany,long idbranch);
}
