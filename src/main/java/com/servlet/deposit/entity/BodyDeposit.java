package com.servlet.deposit.entity;

public class BodyDeposit {
    private Long idvendor;
    private Double amount;
    private Long depositdate;

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getDepositdate() {
        return depositdate;
    }

    public void setDepositdate(Long depositdate) {
        this.depositdate = depositdate;
    }
}
