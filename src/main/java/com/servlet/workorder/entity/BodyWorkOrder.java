package com.servlet.workorder.entity;


public class BodyWorkOrder {
	private Long tanggal;
	private Long idcustomer;
	private String namacargo;
	private String status;
	private String jeniswo;
	private String modatransportasi;
	private Long etd;
	private Long eta;
	private Long portasal;
	private Long porttujuan;
	private String jalur;
	private String noaju;
	private String nopen;
	private Long tanggalnopen;
	private String nobl;
	private Long tanggalbl;
	private Long pelayaran;
	private Long importir;
	private Long eksportir;
	private Long qq;
	private String voyagenumber;
	private Long tanggalsppb_npe;
	private String depo;
	private String invoiceno;
	private boolean isactive;
	private BodyDetailWorkOrder[] details;
	
	public Long getTanggal() {
		return tanggal;
	}
	public void setTanggal(Long tanggal) {
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
	public Long getEtd() {
		return etd;
	}
	public void setEtd(Long etd) {
		this.etd = etd;
	}
	public Long getEta() {
		return eta;
	}
	public void setEta(Long eta) {
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
	public Long getTanggalnopen() {
		return tanggalnopen;
	}
	public void setTanggalnopen(Long tanggalnopen) {
		this.tanggalnopen = tanggalnopen;
	}
	public String getNobl() {
		return nobl;
	}
	public void setNobl(String nobl) {
		this.nobl = nobl;
	}
	public Long getTanggalbl() {
		return tanggalbl;
	}
	public void setTanggalbl(Long tanggalbl) {
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
	public Long getTanggalsppb_npe() {
		return tanggalsppb_npe;
	}
	public void setTanggalsppb_npe(Long tanggalsppb_npe) {
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
	public BodyDetailWorkOrder[] getDetails() {
		return details;
	}
	public void setDetails(BodyDetailWorkOrder[] details) {
		this.details = details;
	}
}
