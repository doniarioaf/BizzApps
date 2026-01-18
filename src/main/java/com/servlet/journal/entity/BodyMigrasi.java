package com.servlet.journal.entity;

public class BodyMigrasi {
    private Long from;
    private Long to;
    private String isall;
    private Long iduser;

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
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

    public String getIsall() {
        return isall;
    }

    public void setIsall(String isall) {
        this.isall = isall;
    }

    @Override
    public String toString() {
        return "Integrasi{" +
                "from=" + from +
                ", to=" + to +
                ", isall='" + isall +
                '}';
    }
}
