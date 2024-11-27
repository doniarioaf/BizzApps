package com.servlet.product.service;

import com.servlet.product.entity.BodyProduct;
import com.servlet.product.entity.ListProductData;
import com.servlet.product.entity.ProductDataDetail;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface ProductService {
    List<ListProductData> getListAll(Long idcompany, Long idbranch);
    ProductDataDetail getDetail(Long id, Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyProduct body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyProduct body);
    ReturnData delete(Long id,Long iduser);
}
