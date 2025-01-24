package com.servlet.pelunasanhutang.entity;

public class PelunasanHutangReportStatusTagihanCargo {
    private Long id;
    private String nodocument;
    private String notes;
    private Long idcargo;

    public Long getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Long idcargo) {
        this.idcargo = idcargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
