package com.servlet.vendorcategory.service;

import java.util.List;

import com.servlet.shared.ReturnData;
import com.servlet.vendorcategory.entity.BodyVendorCategory;
import com.servlet.vendorcategory.entity.VendorCategoryData;

public interface VendorCategoryService {
	List<VendorCategoryData> getListAll(Long idcompany,Long idbranch);
	List<VendorCategoryData> getListActive(Long idcompany,Long idbranch);
	VendorCategoryData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveVendorCategory(Long idcompany,Long idbranch,Long iduser,BodyVendorCategory body);
	ReturnData updateVendorCategory(Long idcompany,Long idbranch,Long iduser,Long id,BodyVendorCategory body);
	ReturnData deleteVendorCategory(Long idcompany,Long idbranch,Long iduser,Long id);
}
