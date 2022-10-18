package com.servlet.workorder.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_workorder", schema = "public")
public class WorkOrder implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="m_workorder_id_seq")
	private Long id;
	private Long idcompany;
	private Long idbranch;
	private String nodocument;
	private Date tanggal;
	private Long idcustomer;
	private String namacargo;
	private String status;
	private String jeniswo;
	private String modatransportasi;
	private Date etd;
	private Date eta;
	private Long portasal;
	private Long porttujuan;
	private String jalur;
	private String noaju;
	private String nopen;
	private Date tanggalnopen;
	private String nobl;
	private Date tanggalbl;
	private Long pelayaran;
	private Long importir;
	private Long eksportir;
	private Long qq;
	private String voyagenumber;
	private Date tanggalsppb_npe;
	private String depo;
	private String invoiceno;
	private boolean isactive;
	private boolean isdelete;
	private String createdby;
	private Timestamp createddate;
	private String updateby;
	private Timestamp updatedate;
	private String deleteby;
	private Timestamp deletedate;
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
	public String getNodocument() {
		return nodocument;
	}
	public void setNodocument(String nodocument) {
		this.nodocument = nodocument;
	}
	public Date getTanggal() {
		return tanggal;
	}
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}
	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getNamacargo() {
		return namacargo;
	}
	public void setNamacargo(String namacargo) {
		this.namacargo = namacargo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJeniswo() {
		return jeniswo;
	}
	public void setJeniswo(String jeniswo) {
		this.jeniswo = jeniswo;
	}
	public String getModatransportasi() {
		return modatransportasi;
	}
	public void setModatransportasi(String modatransportasi) {
		this.modatransportasi = modatransportasi;
	}
	public Date getEtd() {
		return etd;
	}
	public void setEtd(Date etd) {
		this.etd = etd;
	}
	public Date getEta() {
		return eta;
	}
	public void setEta(Date eta) {
		this.eta = eta;
	}
	public Long getPortasal() {
		return portasal;
	}
	public void setPortasal(Long portasal) {
		this.portasal = portasal;
	}
	public Long getPorttujuan() {
		return porttujuan;
	}
	public void setPorttujuan(Long porttujuan) {
		this.porttujuan = porttujuan;
	}
	public String getJalur() {
		return jalur;
	}
	public void setJalur(String jalur) {
		this.jalur = jalur;
	}
	public String getNoaju() {
		return noaju;
	}
	public void setNoaju(String noaju) {
		this.noaju = noaju;
	}
	public String getNopen() {
		return nopen;
	}
	public void setNopen(String nopen) {
		this.nopen = nopen;
	}
	public Date getTanggalnopen() {
		return tanggalnopen;
	}
	public void setTanggalnopen(Date tanggalnopen) {
		this.tanggalnopen = tanggalnopen;
	}
	public String getNobl() {
		return nobl;
	}
	public void setNobl(String nobl) {
		this.nobl = nobl;
	}
	public Date getTanggalbl() {
		return tanggalbl;
	}
	public void setTanggalbl(Date tanggalbl) {
		this.tanggalbl = tanggalbl;
	}
	public Long getPelayaran() {
		return pelayaran;
	}
	public void setPelayaran(Long pelayaran) {
		this.pelayaran = pelayaran;
	}
	public Long getImportir() {
		return importir;
	}
	public void setImportir(Long importir) {
		this.importir = importir;
	}
	public Long getEksportir() {
		return eksportir;
	}
	public void setEksportir(Long eksportir) {
		this.eksportir = eksportir;
	}
	public Long getQq() {
		return qq;
	}
	public void setQq(Long qq) {
		this.qq = qq;
	}
	public String getVoyagenumber() {
		return voyagenumber;
	}
	public void setVoyagenumber(String voyagenumber) {
		this.voyagenumber = voyagenumber;
	}
	public Date getTanggalsppb_npe() {
		return tanggalsppb_npe;
	}
	public void setTanggalsppb_npe(Date tanggalsppb_npe) {
		this.tanggalsppb_npe = tanggalsppb_npe;
	}
	public String getDepo() {
		return depo;
	}
	public void setDepo(String depo) {
		this.depo = depo;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public boolean isIsdelete() {
		return isdelete;
	}
	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public Timestamp getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
	public String getDeleteby() {
		return deleteby;
	}
	public void setDeleteby(String deleteby) {
		this.deleteby = deleteby;
	}
	public Timestamp getDeletedate() {
		return deletedate;
	}
	public void setDeletedate(Timestamp deletedate) {
		this.deletedate = deletedate;
	}
}
