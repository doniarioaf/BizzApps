package com.servlet.purchasereceive.service;

import com.servlet.purchasereceive.entity.*;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface PurchaseReceiveService {
    List<PurchaseReceiveDataList> getListAll(Long idcompany, Long idbranch, Long from, Long to);
    PurchaseReceiveDataDetail getDetail(Long idcompany, Long idbranch, Long id);
    PurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPurchaseReceive body);
    SearchDataTemplateByVendor searchDataByVendor(Long idcompany, Long idbranch,Long idvendor);
    Double calculateSetorByIdVendor(Long idcompany, Long idbranch, Long idvendor);
    PrintDataPurchaseReceive printNotaPurchaseReceive(Long idcompany, Long idbranch,Long iduser, Long id);
    Double calculateSetorByIdVendorAndCreatedDate(Long idcompany, Long idbranch, Long idvendor, Long date);
}
