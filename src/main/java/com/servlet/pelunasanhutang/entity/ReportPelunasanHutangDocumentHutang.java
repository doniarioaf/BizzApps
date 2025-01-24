package com.servlet.pelunasanhutang.entity;

import java.sql.Date;

public class ReportPelunasanHutangDocumentHutang implements Comparable<ReportPelunasanHutangDocumentHutang>{
    private Long iddoc;
    private Date date;
    private String nodocument;
    private Double amountInvoice;

    private String docType;// CARGO or PURCHASERECEIVE

    public Long getIddoc() {
        return iddoc;
    }

    public void setIddoc(Long iddoc) {
        this.iddoc = iddoc;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public Double getAmountInvoice() {
        return amountInvoice;
    }

    public void setAmountInvoice(Double amountInvoice) {
        this.amountInvoice = amountInvoice;
    }

    @Override
    public int compareTo(ReportPelunasanHutangDocumentHutang o) {
        return this.date.compareTo(o.getDate());
    }

}
