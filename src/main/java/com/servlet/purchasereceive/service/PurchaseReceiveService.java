package com.servlet.purchasereceive.service;

import com.servlet.purchasereceive.entity.BodyPurchaseReceive;
import com.servlet.purchasereceive.entity.PurchaseReceiveDataList;
import com.servlet.purchasereceive.entity.PurchaseReceiveTemplate;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface PurchaseReceiveService {
    List<PurchaseReceiveDataList> getListAll(Long idcompany, Long idbranch, Long from, Long to);
    PurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch,Long pricedate);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPurchaseReceive body);
}
