package com.servlet.penerimaankasbank.entity;

import java.sql.Date;

public class DetailPenerimaanKasBankDataLabaRugi {
    private Long idpenerimaankasbank;
    private Long counter;
    private Long idcoa;
    private String coaname;
    private String catatan;
    private Double amount;
    private String isdownpayment;
    private Long idinvoice;
    private String nodocinvoice;
    private Long idworkorder;
    private String nodocworkorder;
    private String noaju;

    private Double penyesuaian;
    private String keterangan_penyesuaian;
    private String nodocpenerimaan;
    private Date tanggalpenerimaan;
    private String receivefrom;
    private String namabank;

    private Double nilaijasa;
    private Double nilaireimbursement;
    private Double nilaibuktipotong;
    private String nobuktipotong;
    private Date tanggalbuktipotong;
    private Double nilaippn;

    public Double getNilaijasa() {
        return nilaijasa;
    }

    public void setNilaijasa(Double nilaijasa) {
        this.nilaijasa = nilaijasa;
    }

    public Double getNilaireimbursement() {
        return nilaireimbursement;
    }

    public void setNilaireimbursement(Double nilaireimbursement) {
        this.nilaireimbursement = nilaireimbursement;
    }

    public Double getNilaibuktipotong() {
        return nilaibuktipotong;
    }

    public void setNilaibuktipotong(Double nilaibuktipotong) {
        this.nilaibuktipotong = nilaibuktipotong;
    }

    public String getNobuktipotong() {
        return nobuktipotong;
    }

    public void setNobuktipotong(String nobuktipotong) {
        this.nobuktipotong = nobuktipotong;
    }

    public Date getTanggalbuktipotong() {
        return tanggalbuktipotong;
    }

    public void setTanggalbuktipotong(Date tanggalbuktipotong) {
        this.tanggalbuktipotong = tanggalbuktipotong;
    }

    public Double getNilaippn() {
        return nilaippn;
    }

    public void setNilaippn(Double nilaippn) {
        this.nilaippn = nilaippn;
    }

    public String getNamabank() {
        return namabank;
    }

    public void setNamabank(String namabank) {
        this.namabank = namabank;
    }

    public String getReceivefrom() {
        return receivefrom;
    }

    public void setReceivefrom(String receivefrom) {
        this.receivefrom = receivefrom;
    }

    public Date getTanggalpenerimaan() {
        return tanggalpenerimaan;
    }

    public void setTanggalpenerimaan(Date tanggalpenerimaan) {
        this.tanggalpenerimaan = tanggalpenerimaan;
    }

    public String getNodocpenerimaan() {
        return nodocpenerimaan;
    }

    public void setNodocpenerimaan(String nodocpenerimaan) {
        this.nodocpenerimaan = nodocpenerimaan;
    }

    public Double getPenyesuaian() {
        return penyesuaian;
    }

    public void setPenyesuaian(Double penyesuaian) {
        this.penyesuaian = penyesuaian;
    }

    public String getKeterangan_penyesuaian() {
        return keterangan_penyesuaian;
    }

    public void setKeterangan_penyesuaian(String keterangan_penyesuaian) {
        this.keterangan_penyesuaian = keterangan_penyesuaian;
    }
    @Override
    public String toString() {
        return "DetailPenerimaanKasBankData [idpenerimaankasbank=" + idpenerimaankasbank + ", counter=" + counter
                + ", idcoa=" + idcoa + ", coaname=" + coaname + ", catatan=" + catatan + ", amount=" + amount
                + ", isdownpayment=" + isdownpayment + ", idinvoice=" + idinvoice + ", nodocinvoice=" + nodocinvoice
                + ", idworkorder=" + idworkorder + ", nodocworkorder=" + nodocworkorder + ", noaju=" + noaju + "]";
    }
    public Long getIdcoa() {
        return idcoa;
    }
    public void setIdcoa(Long idcoa) {
        this.idcoa = idcoa;
    }
    public String getCoaname() {
        return coaname;
    }
    public void setCoaname(String coaname) {
        this.coaname = coaname;
    }
    public String getCatatan() {
        return catatan;
    }
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getIsdownpayment() {
        return isdownpayment;
    }
    public void setIsdownpayment(String isdownpayment) {
        this.isdownpayment = isdownpayment;
    }
    public Long getIdinvoice() {
        return idinvoice;
    }
    public void setIdinvoice(Long idinvoice) {
        this.idinvoice = idinvoice;
    }
    public String getNodocinvoice() {
        return nodocinvoice;
    }
    public void setNodocinvoice(String nodocinvoice) {
        this.nodocinvoice = nodocinvoice;
    }
    public Long getIdworkorder() {
        return idworkorder;
    }
    public void setIdworkorder(Long idworkorder) {
        this.idworkorder = idworkorder;
    }
    public String getNodocworkorder() {
        return nodocworkorder;
    }
    public void setNodocworkorder(String nodocworkorder) {
        this.nodocworkorder = nodocworkorder;
    }
    public Long getCounter() {
        return counter;
    }
    public void setCounter(Long counter) {
        this.counter = counter;
    }
    public Long getIdpenerimaankasbank() {
        return idpenerimaankasbank;
    }
    public void setIdpenerimaankasbank(Long idpenerimaankasbank) {
        this.idpenerimaankasbank = idpenerimaankasbank;
    }
    public String getNoaju() {
        return noaju;
    }
    public void setNoaju(String noaju) {
        this.noaju = noaju;
    }
}
