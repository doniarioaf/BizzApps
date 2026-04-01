package com.servlet.chartofaccount;

//untuk sementara karena ini untuk kebutuhan hitung saldo deposit dan pinjaman saja
//jika lebih kompleks, bisa dibuatkan service dll nya.
//data ini harus sama dengan table chartoffaccount
public enum AccountCOAEnum {
    DEPOSITVENDOR_ASSET("1-1101"),
    PINJAMANVENDOR_LIABILITY("2-2201");

    private final String accCode;

    // Constructor (implicitly private)
    AccountCOAEnum(String accCode) {
        this.accCode = accCode;
    }

    public String getAccCode() {
        return this.accCode;
    }
}
