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
    List<VendorDataForTemplate> getListDropdown(Long idcompany, Long idbranch, ParamVendor param);
    String queryIdVendorCategoryProductNotInclud(Long idcompany, Long idbranch,Long idvendor);
    ListVendorData checkVendorIsParent(Long idcompany, Long idbranch,Long idvendor);
    Long getIdParent(Long idcompany, Long idbranch,Long idvendor);
    List<Long> getListSubIdParent(Long idcompany, Long idbranch,Long idvendor);
    List<Long> getListSubIdParentByListIdParent(Long idcompany, Long idbranch,String listidvendorparents);
    List<Long> checkIdCP(Long idcompany, Long idbranch, Long idcategoryProduct);
}
