package com.servlet.workorder.entity;

import java.sql.Date;

public class WorkOrderData {
	private Long id;
	private String nodocument;
	private Date tanggal;
	private Long idcustomer;
	private String namaCustomer;
	private String namacargo;
	private String status;
	private String statusCodeName;
	private String jeniswo;
	private String jeniswoCodeName;
	private String modatransportasi;
	private String modatransportasiCodeName;
	private Date etd;
	private Date eta;
	private Long portasal;
	private String portasalname;
	private Long porttujuan;
	private String porttujuanname;
	private String jalur;
	private String jalurCodeName;
	private String noaju;
	private String nopen;
	private Date tanggalnopen;
	private String nobl;
	private Date tanggalbl;
	private Long pelayaran;
	private String pelayaranname;
	private Long importir;
	private String importirname;
	private Long eksportir;
	private String eksportirname;
	private Long qq;
	private String qqname;
	private String voyagenumber;
	private Date tanggalsppb_npe;
	private String depo;
	private String invoiceno;
	private boolean isactive;
	private WorkOrderTemplate templates;
	public String getStatusCodeName() {
		return statusCodeName;
	}
	public void setStatusCodeName(String statusCodeName) {
		this.statusCodeName = statusCodeName;
	}
	public String getJeniswoCodeName() {
		return jeniswoCodeName;
	}
	public void setJeniswoCodeName(String jeniswoCodeName) {
		this.jeniswoCodeName = jeniswoCodeName;
	}
	public String getModatransportasiCodeName() {
		return modatransportasiCodeName;
	}
	public void setModatransportasiCodeName(String modatransportasiCodeName) {
		this.modatransportasiCodeName = modatransportasiCodeName;
	}
	public String getJalurCodeName() {
		return jalurCodeName;
	}
	public void setJalurCodeName(String jalurCodeName) {
		this.jalurCodeName = jalurCodeName;
	}
	public String getNamaCustomer() {
		return namaCustomer;
	}
	public void setNamaCustomer(String namaCustomer) {
		this.namaCustomer = namaCustomer;
	}
	public String getPortasalname() {
		return portasalname;
	}
	public void setPortasalname(String portasalname) {
		this.portasalname = portasalname;
	}
	public String getPorttujuanname() {
		return porttujuanname;
	}
	public void setPorttujuanname(String porttujuanname) {
		this.porttujuanname = porttujuanname;
	}
	public String getPelayaranname() {
		return pelayaranname;
	}
	public void setPelayaranname(String pelayaranname) {
		this.pelayaranname = pelayaranname;
	}
	public String getImportirname() {
		return importirname;
	}
	public void setImportirname(String importirname) {
		this.importirname = importirname;
	}
	public String getEksportirname() {
		return eksportirname;
	}
	public void setEksportirname(String eksportirname) {
		this.eksportirname = eksportirname;
	}
	public String getQqname() {
		return qqname;
	}
	public void setQqname(String qqname) {
		this.qqname = qqname;
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
	public WorkOrderTemplate getTemplates() {
		return templates;
	}
	public void setTemplates(WorkOrderTemplate templates) {
		this.templates = templates;
	}
}
