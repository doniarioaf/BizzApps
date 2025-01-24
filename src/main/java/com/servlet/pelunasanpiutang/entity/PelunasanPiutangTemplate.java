package com.servlet.pelunasanpiutang.entity;

import com.servlet.customer.entity.CustomerGrup;

import java.util.List;

public class PelunasanPiutangTemplate {
    private List<CustomerGrup> customerGrupOpt;

    public List<CustomerGrup> getCustomerGrupOpt() {
        return customerGrupOpt;
    }

    public void setCustomerGrupOpt(List<CustomerGrup> customerGrupOpt) {
        this.customerGrupOpt = customerGrupOpt;
    }
}
