package com.servlet.report.entity;

public class ParamReportKartuDeposit {
    private Long from;
    private Long to;
    private String idvendors;
    private String showNol; //YES,NO

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

    public String getIdvendors() {
        return idvendors;
    }

    public void setIdvendors(String idvendors) {
        this.idvendors = idvendors;
    }

    public String getShowNol() {
        return showNol;
    }

    public void setShowNol(String showNol) {
        this.showNol = showNol;
    }
}
