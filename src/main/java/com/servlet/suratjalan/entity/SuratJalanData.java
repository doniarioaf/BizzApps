package com.servlet.suratjalan.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class SuratJalanData {
	private Long id;
	private String nodocument;
	private Timestamp tanggal;
	private Long idworkorder;
	private String nodocumentWO;
	private String noblWO;
	private String noajuWO;
	private String namacargoWO;
	private Long idcustomer;
	private String namacustomer;
	private String keterangan;
	private Long idwarehouse;
	private String warehousename;
	private String warehousecontactname;
	private String warehousecontactno;
	private String warehouseaddress;
	private String warehousekecamatan;
	private String catatan;
	private String nocantainer;
	private String containerpartai;
	private String containerjumlahkoli;
	private String containerjumlahkg;
	private String status;
	private String statusname;
	private String kepemilikanmobil;
	private String kepemilikanmobilname;
	private Long idemployee_supir;
	private String supirname;
	private Long idasset;
	private String assetname;
	private String nopolisi;
	private Long idvendormobil;
	private String vendormobilname;
	private String lembur;
	private String lemburname;
	private Date tanggalkembali;
	private SuratJalanTemplate template;
	private List<HistorySuratJalanData> history;
	
	public String getWarehousekecamatan() {
		return warehousekecamatan;
	}
	public void setWarehousekecamatan(String warehousekecamatan) {
		this.warehousekecamatan = warehousekecamatan;
	}
	public String getNopolisi() {
		return nopolisi;
	}
	public void setNopolisi(String nopolisi) {
		this.nopolisi = nopolisi;
	}
	public SuratJalanTemplate getTemplate() {
		return template;
	}
	public void setTemplate(SuratJalanTemplate template) {
		this.template = template;
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
	public Timestamp getTanggal() {
		return tanggal;
	}
	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
	public Long getIdworkorder() {
		return idworkorder;
	}
	public void setIdworkorder(Long idworkorder) {
		this.idworkorder = idworkorder;
	}
	public String getNodocumentWO() {
		return nodocumentWO;
	}
	public void setNodocumentWO(String nodocumentWO) {
		this.nodocumentWO = nodocumentWO;
	}
	public String getNoblWO() {
		return noblWO;
	}
	public void setNoblWO(String noblWO) {
		this.noblWO = noblWO;
	}
	public String getNoajuWO() {
		return noajuWO;
	}
	public void setNoajuWO(String noajuWO) {
		this.noajuWO = noajuWO;
	}
	public String getNamacargoWO() {
		return namacargoWO;
	}
	public void setNamacargoWO(String namacargoWO) {
		this.namacargoWO = namacargoWO;
	}
	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getNamacustomer() {
		return namacustomer;
	}
	public void setNamacustomer(String namacustomer) {
		this.namacustomer = namacustomer;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public Long getIdwarehouse() {
		return idwarehouse;
	}
	public void setIdwarehouse(Long idwarehouse) {
		this.idwarehouse = idwarehouse;
	}
	public String getWarehousecontactname() {
		return warehousecontactname;
	}
	public void setWarehousecontactname(String warehousecontactname) {
		this.warehousecontactname = warehousecontactname;
	}
	public String getWarehousecontactno() {
		return warehousecontactno;
	}
	public void setWarehousecontactno(String warehousecontactno) {
		this.warehousecontactno = warehousecontactno;
	}
	public String getWarehouseaddress() {
		return warehouseaddress;
	}
	public void setWarehouseaddress(String warehouseaddress) {
		this.warehouseaddress = warehouseaddress;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public String getNocantainer() {
		return nocantainer;
	}
	public void setNocantainer(String nocantainer) {
		this.nocantainer = nocantainer;
	}
	public String getContainerpartai() {
		return containerpartai;
	}
	public void setContainerpartai(String containerpartai) {
		this.containerpartai = containerpartai;
	}
	public String getContainerjumlahkoli() {
		return containerjumlahkoli;
	}
	public void setContainerjumlahkoli(String containerjumlahkoli) {
		this.containerjumlahkoli = containerjumlahkoli;
	}
	public String getContainerjumlahkg() {
		return containerjumlahkg;
	}
	public void setContainerjumlahkg(String containerjumlahkg) {
		this.containerjumlahkg = containerjumlahkg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	public List<HistorySuratJalanData> getHistory() {
		return history;
	}
	public void setHistory(List<HistorySuratJalanData> history) {
		this.history = history;
	}
	public String getKepemilikanmobil() {
		return kepemilikanmobil;
	}
	public void setKepemilikanmobil(String kepemilikanmobil) {
		this.kepemilikanmobil = kepemilikanmobil;
	}
	public String getKepemilikanmobilname() {
		return kepemilikanmobilname;
	}
	public void setKepemilikanmobilname(String kepemilikanmobilname) {
		this.kepemilikanmobilname = kepemilikanmobilname;
	}
	public Long getIdemployee_supir() {
		return idemployee_supir;
	}
	public void setIdemployee_supir(Long idemployee_supir) {
		this.idemployee_supir = idemployee_supir;
	}
	public String getSupirname() {
		return supirname;
	}
	public void setSupirname(String supirname) {
		this.supirname = supirname;
	}
	public Long getIdasset() {
		return idasset;
	}
	public void setIdasset(Long idasset) {
		this.idasset = idasset;
	}
	public String getAssetname() {
		return assetname;
	}
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}
	public Long getIdvendormobil() {
		return idvendormobil;
	}
	public void setIdvendormobil(Long idvendormobil) {
		this.idvendormobil = idvendormobil;
	}
	public String getVendormobilname() {
		return vendormobilname;
	}
	public void setVendormobilname(String vendormobilname) {
		this.vendormobilname = vendormobilname;
	}
	public String getLembur() {
		return lembur;
	}
	public void setLembur(String lembur) {
		this.lembur = lembur;
	}
	public String getLemburname() {
		return lemburname;
	}
	public void setLemburname(String lemburname) {
		this.lemburname = lemburname;
	}
	public Date getTanggalkembali() {
		return tanggalkembali;
	}
	public void setTanggalkembali(Date tanggalkembali) {
		this.tanggalkembali = tanggalkembali;
	}
}
