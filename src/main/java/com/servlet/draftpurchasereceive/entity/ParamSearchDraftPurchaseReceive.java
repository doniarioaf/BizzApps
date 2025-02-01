package com.servlet.draftpurchasereceive.entity;

public class ParamSearchDraftPurchaseReceive {
    private Long from;
    private Long to;
    private Boolean onlyShowNotInLinkedPR;

    public Boolean getOnlyShowNotInLinkedPR() {
        return onlyShowNotInLinkedPR;
    }

    public void setOnlyShowNotInLinkedPR(Boolean onlyShowNotInLinkedPR) {
        this.onlyShowNotInLinkedPR = onlyShowNotInLinkedPR;
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
