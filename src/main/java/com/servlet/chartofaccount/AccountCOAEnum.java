package com.servlet.chartofaccount;

//untuk sementara karena ini untuk kebutuhan hitung saldo deposit dan pinjaman saja
//jika lebih kompleks, bisa dibuatkan service dll nya.
//data ini harus sama dengan table chartoffaccount
public enum AccountCOAEnum {
    KAS_ASSET("1-1001"),
    DEPOSITVENDOR_ASSET("1-1001"),
    HUTANGUSAHA_LIABILITY("2-2101"),
    HUTANGUSAHA_DEPOSIT_LIABILITY("2-2101-D"),
    HUTANGUSAHA_PINJAMAN_LIABILITY("2-2101-P"),
    PINJAMANVENDOR_LIABILITY("2-2201"),
    PERSEDIAAN_ASSET("3-3001"),
    BEBANPEMBELIAN_EXPENSE("5-5001"),
    SUSPENSE_OR_ADJUSMENT_EQUITY("9-9001");

    private final String accCode;

    // Constructor (implicitly private)
    AccountCOAEnum(String accCode) {
        this.accCode = accCode;
    }

    public String getAccCode() {
        return this.accCode;
    }
}
