package com.servlet.purchasereceive.entity;

import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveList;

import java.util.List;

public class PurchaseReceiveAllTabData {
    private List<PurchaseReceiveDataList> listPr;
    private List<DraftPurchaseReceiveList> listDpr;

    public List<PurchaseReceiveDataList> getListPr() {
        return listPr;
    }

    public void setListPr(List<PurchaseReceiveDataList> listPr) {
        this.listPr = listPr;
    }

    public List<DraftPurchaseReceiveList> getListDpr() {
        return listDpr;
    }

    public void setListDpr(List<DraftPurchaseReceiveList> listDpr) {
        this.listDpr = listDpr;
    }
}
