package com.servlet.vendor.service;

import com.servlet.shared.ReturnData;
import com.servlet.vendor.entity.BodyVendor;
import com.servlet.vendor.entity.ListVendorData;
import com.servlet.vendor.entity.VendorData;
import com.servlet.vendor.entity.VendorTemplate;

import java.util.List;

public interface VendorService {
    List<ListVendorData> getListAll(Long idcompany, Long idbranch);
    VendorData getDetail(Long id, Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyVendor body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyVendor body);
    ReturnData delete(Long id,Long iduser);
    VendorTemplate getTemplate(Long idcompany, Long idbranch);
}
