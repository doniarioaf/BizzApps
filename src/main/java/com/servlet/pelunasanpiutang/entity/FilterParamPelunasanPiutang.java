package com.servlet.pelunasanpiutang.entity;

public class FilterParamPelunasanPiutang {
    private Long from;
    private Long to;
    private String namaCust;
    private String status;//LUNAS,BELUMLUNAS,ALL
    private String customergrup;

    public String getNamaCust() {
        return namaCust;
    }

    public void setNamaCust(String namaCust) {
        this.namaCust = namaCust;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomergrup() {
        return customergrup;
    }

    public void setCustomergrup(String customergrup) {
        this.customergrup = customergrup;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }
}
