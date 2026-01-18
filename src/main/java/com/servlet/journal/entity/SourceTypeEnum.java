package com.servlet.journal.entity;

public enum SourceTypeEnum {
    TOPUP_DEPOSIT("TOPUP-DEPOSIT"),
    TOPUP_PINJAMAN("TOPUP-PINJAMAN"),
    TRANSAKSI_PRC("TRANSAKSI-PRC");

    private final String sourceType;

    // Constructor (implicitly private)
    SourceTypeEnum(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceType() {
        return this.sourceType;
    }
}
