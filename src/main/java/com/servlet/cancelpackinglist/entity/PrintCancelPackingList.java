package com.servlet.cancelpackinglist.entity;

import com.servlet.packinglist.entity.PackingListDataItemDetail;
import com.servlet.packinglist.entity.PrintPackingList;

import java.sql.Date;
import java.util.List;

public class PrintCancelPackingList {

    private Long idCPL;
    private String nodocumentCPL;
    private String keteranganCPL;
    private PrintPackingList packingList;

    public Long getIdCPL() {
        return idCPL;
    }

    public void setIdCPL(Long idCPL) {
        this.idCPL = idCPL;
    }

    public String getNodocumentCPL() {
        return nodocumentCPL;
    }

    public void setNodocumentCPL(String nodocumentCPL) {
        this.nodocumentCPL = nodocumentCPL;
    }

    public String getKeteranganCPL() {
        return keteranganCPL;
    }

    public void setKeteranganCPL(String keteranganCPL) {
        this.keteranganCPL = keteranganCPL;
    }

    public PrintPackingList getPackingList() {
        return packingList;
    }

    public void setPackingList(PrintPackingList packingList) {
        this.packingList = packingList;
    }
}
