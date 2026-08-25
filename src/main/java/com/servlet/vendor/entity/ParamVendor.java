package com.servlet.vendor.entity;

public class ParamVendor {
    private String listIdVendor;
    private String vendorTypes;
    private String onlyParent; //Y or N
    private String forPinjaman; //Y or N
    private String forDeposit; //Y or N

    public String getForPinjaman() {
        return forPinjaman;
    }

    public void setForPinjaman(String forPinjaman) {
        this.forPinjaman = forPinjaman;
    }

    public String getForDeposit() {
        return forDeposit;
    }

    public void setForDeposit(String forDeposit) {
        this.forDeposit = forDeposit;
    }

    public String getOnlyParent() {
        return onlyParent;
    }

    public void setOnlyParent(String onlyParent) {
        this.onlyParent = onlyParent;
    }

    public String getVendorTypes() {
        return vendorTypes;
    }

    public void setVendorTypes(String vendorTypes) {
        this.vendorTypes = vendorTypes;
    }

    public String getListIdVendor() {
        return listIdVendor;
    }

    public void setListIdVendor(String listIdVendor) {
        this.listIdVendor = listIdVendor;
    }
}
