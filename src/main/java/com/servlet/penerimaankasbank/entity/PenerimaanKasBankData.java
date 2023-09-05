package com.servlet.penerimaankasbank.entity;

import java.sql.Date;
import java.util.List;

public class PenerimaanKasBankData {
	private Long id;
	private String nodocument;
	private Date receivedate;
	private String receivefrom;
	private Long idcoa;
	private String coaName;
	private Long idbank;
	private String bankName;
	private String keterangan;
	private boolean isactive;
	private List<DetailPenerimaanKasBankData> details;
	private PenerimaanKasBankTemplate template;
	private boolean disablededitordelete;
	
	private Long idcustomer;
	private String customerName;
	private Long idvendor;
	private String vendorName;
	private Long idemployee;
	private String employeeName;
	private String idreceivetype;
	private String receivetypename;
	
	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getIdvendor() {
		return idvendor;
	}
	public void setIdvendor(Long idvendor) {
		this.idvendor = idvendor;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Long getIdemployee() {
		return idemployee;
	}
	public void setIdemployee(Long idemployee) {
		this.idemployee = idemployee;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getIdreceivetype() {
		return idreceivetype;
	}
	public void setIdreceivetype(String idreceivetype) {
		this.idreceivetype = idreceivetype;
	}
	public String getReceivetypename() {
		return receivetypename;
	}
	public void setReceivetypename(String receivetypename) {
		this.receivetypename = receivetypename;
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
	public Date getReceivedate() {
		return receivedate;
	}
	public void setReceivedate(Date receivedate) {
		this.receivedate = receivedate;
	}
	public String getReceivefrom() {
		return receivefrom;
	}
	public void setReceivefrom(String receivefrom) {
		this.receivefrom = receivefrom;
	}
	public Long getIdcoa() {
		return idcoa;
	}
	public void setIdcoa(Long idcoa) {
		this.idcoa = idcoa;
	}
	public Long getIdbank() {
		return idbank;
	}
	public void setIdbank(Long idbank) {
		this.idbank = idbank;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getCoaName() {
		return coaName;
	}
	public void setCoaName(String coaName) {
		this.coaName = coaName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public List<DetailPenerimaanKasBankData> getDetails() {
		return details;
	}
	public void setDetails(List<DetailPenerimaanKasBankData> details) {
		this.details = details;
	}
	public PenerimaanKasBankTemplate getTemplate() {
		return template;
	}
	public void setTemplate(PenerimaanKasBankTemplate template) {
		this.template = template;
	}
	public boolean isDisablededitordelete() {
		return disablededitordelete;
	}
	public void setDisablededitordelete(boolean disablededitordelete) {
		this.disablededitordelete = disablededitordelete;
	}
	
}
