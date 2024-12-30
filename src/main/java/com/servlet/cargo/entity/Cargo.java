package com.servlet.cargo.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "cargo", schema = "public")
public class Cargo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="cargo_id_seq")
    private Long id;
    private Long idcompany;
    private Long idbranch;
    private Long idvendor;
    private Date date;
    private String invoicenumber;
    private String smunumber;
    private String awbnumber;
    private Long koli;
    private Double grossamount;
    private Double ppnamount;
    private Double ppn23amount;
    private Double netamount;
    private Double outstanding;
    private String file;
    private boolean isdelete;
    private Long createdby;
    private Timestamp createddate;
    private Long modifiedby;
    private Timestamp modifieddate;
    private Long deleteby;
    private Timestamp deletedate;

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", idvendor=" + idvendor +
                ", date=" + date +
                ", invoicenumber='" + invoicenumber + '\'' +
                ", smunumber='" + smunumber + '\'' +
                ", awbnumber='" + awbnumber + '\'' +
                ", koli=" + koli +
                ", grossamount=" + grossamount +
                ", ppnamount=" + ppnamount +
                ", ppn23amount=" + ppn23amount +
                ", netamount=" + netamount +
                ", outstanding=" + outstanding +
                ", file='" + file + '\'' +
                '}';
    }

    public Double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Double outstanding) {
        this.outstanding = outstanding;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(Long idcompany) {
        this.idcompany = idcompany;
    }

    public Long getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(Long idbranch) {
        this.idbranch = idbranch;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(String invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public String getSmunumber() {
        return smunumber;
    }

    public void setSmunumber(String smunumber) {
        this.smunumber = smunumber;
    }

    public String getAwbnumber() {
        return awbnumber;
    }

    public void setAwbnumber(String awbnumber) {
        this.awbnumber = awbnumber;
    }

    public Long getKoli() {
        return koli;
    }

    public void setKoli(Long koli) {
        this.koli = koli;
    }

    public Double getGrossamount() {
        return grossamount;
    }

    public void setGrossamount(Double grossamount) {
        this.grossamount = grossamount;
    }

    public Double getPpnamount() {
        return ppnamount;
    }

    public void setPpnamount(Double ppnamount) {
        this.ppnamount = ppnamount;
    }

    public Double getPpn23amount() {
        return ppn23amount;
    }

    public void setPpn23amount(Double ppn23amount) {
        this.ppn23amount = ppn23amount;
    }

    public Double getNetamount() {
        return netamount;
    }

    public void setNetamount(Double netamount) {
        this.netamount = netamount;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public Long getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(Long modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Long getDeleteby() {
        return deleteby;
    }

    public void setDeleteby(Long deleteby) {
        this.deleteby = deleteby;
    }

    public Timestamp getDeletedate() {
        return deletedate;
    }

    public void setDeletedate(Timestamp deletedate) {
        this.deletedate = deletedate;
    }
}
