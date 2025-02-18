package com.servlet.report.entity;

import com.servlet.customermanggala.entity.CustomerManggalaData;

import java.util.List;

public class ReportInvoiceTemplate {
    private List<CustomerManggalaData> customerOpt;

    public List<CustomerManggalaData> getCustomerOpt() {
        return customerOpt;
    }

    public void setCustomerOpt(List<CustomerManggalaData> customerOpt) {
        this.customerOpt = customerOpt;
    }
}
