package com.servlet.vendor.service;

import java.util.List;

import com.servlet.shared.ReturnData;
import com.servlet.vendor.entity.BodyVendor;
import com.servlet.vendor.entity.VendorData;
import com.servlet.vendor.entity.VendorListData;
import com.servlet.vendor.entity.VendorTemplate;

public interface VendorService {
	List<VendorListData> getListAll(Long idcompany,Long idbranch);
	List<VendorListData> getListActive(Long idcompany,Long idbranch);
	VendorTemplate getTemplate(Long idcompany,Long idbranch);
	VendorData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveVendor(Long idcompany,Long idbranch,Long iduser,BodyVendor body);
	ReturnData updateVendor(Long idcompany,Long idbranch,Long iduser,Long id,BodyVendor body);
	ReturnData deleteVendor(Long idcompany,Long idbranch,Long iduser,Long id);
}
