package com.servlet.invoice.entity;

import com.servlet.packinglist.entity.PackingListDropDown;

import java.util.List;

public class InvoiceTemplate {
    private List<PackingListDropDown> packingListOpt;

    public List<PackingListDropDown> getPackingListOpt() {
        return packingListOpt;
    }

    public void setPackingListOpt(List<PackingListDropDown> packingListOpt) {
        this.packingListOpt = packingListOpt;
    }
}
