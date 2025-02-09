package com.servlet.purchasereceive.entity;

import java.sql.Date;

public class PurchaseReceiveDepositData {
    private Long iddeposit;
    private String noDocumentDeposit;
    private Date date;
    private Double amount;

    public Long getIddeposit() {
        return iddeposit;
    }

    public void setIddeposit(Long iddeposit) {
        this.iddeposit = iddeposit;
    }

    public String getNoDocumentDeposit() {
        return noDocumentDeposit;
    }

    public void setNoDocumentDeposit(String noDocumentDeposit) {
        this.noDocumentDeposit = noDocumentDeposit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
