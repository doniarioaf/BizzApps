package com.servlet.suratjalan.entity;

import java.sql.Timestamp;

public class PrintData {
	private Long id;
	private String nodocument;
	private Timestamp tanggal;
	private Long idworkorder;
	private String nodocumentWO;
	private String woType;
	private String noblWO;
	private String noajuWO;
	private String namacargoWO;
	private String depoWO;
	private Long idcustomer;
	private String namacustomer;
	private String customerProvince;
	private String customerCity;
	private String customerDistrict;
	private String customerSubDistrict;
	private String customerKodePos;
	private String customerAddress;
	private String keterangan;
	private Long idwarehouse;
	private String warehousename;
	private String warehousecontactname;
	private String warehousecontactno;
	private String warehouseaddress;
	private String catatan;
	private String nocantainer;
	private String containerpartai;
	private String containerjumlahkoli;
	private String containerjumlahkg;
	private String status;
	private String statusname;
	private String companyname;
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
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
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
	public String getCustomerProvince() {
		return customerProvince;
	}
	public void setCustomerProvince(String customerProvince) {
		this.customerProvince = customerProvince;
	}
	public String getCustomerCity() {
		return customerCity;
	}
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	public String getCustomerDistrict() {
		return customerDistrict;
	}
	public void setCustomerDistrict(String customerDistrict) {
		this.customerDistrict = customerDistrict;
	}
	public String getCustomerSubDistrict() {
		return customerSubDistrict;
	}
	public void setCustomerSubDistrict(String customerSubDistrict) {
		this.customerSubDistrict = customerSubDistrict;
	}
	public String getCustomerKodePos() {
		return customerKodePos;
	}
	public void setCustomerKodePos(String customerKodePos) {
		this.customerKodePos = customerKodePos;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getDepoWO() {
		return depoWO;
	}
	public void setDepoWO(String depoWO) {
		this.depoWO = depoWO;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getWoType() {
		return woType;
	}
	public void setWoType(String woType) {
		this.woType = woType;
	}
}
