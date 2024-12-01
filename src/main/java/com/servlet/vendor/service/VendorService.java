package com.servlet.vendor.service;

import com.servlet.shared.ReturnData;
import com.servlet.vendor.entity.*;

import java.util.List;

public interface VendorService {
    List<ListVendorData> getListAll(Long idcompany, Long idbranch);
    VendorData getDetail(Long id, Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyVendor body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyVendor body);
    ReturnData delete(Long id,Long iduser);
    VendorTemplate getTemplate(Long idcompany, Long idbranch);
    List<VendorDataForTemplate> getListDropdown(Long idcompany, Long idbranch);
}
