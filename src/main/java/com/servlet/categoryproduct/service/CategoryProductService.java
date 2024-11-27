package com.servlet.categoryproduct.service;

import com.servlet.categoryproduct.entity.BodyCategoryProduct;
import com.servlet.categoryproduct.entity.CategoryProductDetail;
import com.servlet.categoryproduct.entity.CategoryProductList;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface CategoryProductService {
    List<CategoryProductList> getListAll(Long idcompany, Long idbranch);
    CategoryProductDetail getDetail(Long id, Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyCategoryProduct body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyCategoryProduct body);
    ReturnData delete(Long id,Long iduser);
}
