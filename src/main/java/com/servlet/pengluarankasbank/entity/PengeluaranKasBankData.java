package com.servlet.pengluarankasbank.entity;

import java.sql.Date;
import java.util.List;

import com.servlet.vendor.entity.DetailVendorBankData;

public class PengeluaranKasBankData {
	private Long id;
	private String nodocument;
	private Date paymentdate;
	private Long idcoa;
	private String coaName;
	private Long idbank;
	private String bankName;
	private String paymentto;
	private String keterangan;
	private boolean isactive;
	private PengeluaranKasBankTemplate template;
	private List<DetailPengeluaranKasBankData> details;
	private boolean disablededitordelete;
	private Long idwo;
	private String nodocumentWO;
	private String noAjuWO;
	private Long idcustomer;
	private String customerName;
	private Long idvendor;
	private String vendorName;
	private Long idemployee;
	private String employeeName;
	private String idpaymenttype;
	private String paymenttypename;
	private List<DetailVendorBankData> listBank;
	
	public List<DetailVendorBankData> getListBank() {
		return listBank;
	}
	public void setListBank(List<DetailVendorBankData> listBank) {
		this.listBank = listBank;
	}
	public String getNoAjuWO() {
		return noAjuWO;
	}
	public void setNoAjuWO(String noAjuWO) {
		this.noAjuWO = noAjuWO;
	}
	public String getIdpaymenttype() {
		return idpaymenttype;
	}
	public void setIdpaymenttype(String idpaymenttype) {
		this.idpaymenttype = idpaymenttype;
	}
	public String getPaymenttypename() {
		return paymenttypename;
	}
	public void setPaymenttypename(String paymenttypename) {
		this.paymenttypename = paymenttypename;
	}
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
	public String getNodocumentWO() {
		return nodocumentWO;
	}
	public void setNodocumentWO(String nodocumentWO) {
		this.nodocumentWO = nodocumentWO;
	}
	public Long getIdwo() {
		return idwo;
	}
	public void setIdwo(Long idwo) {
		this.idwo = idwo;
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
	public Date getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}
	public Long getIdcoa() {
		return idcoa;
	}
	public void setIdcoa(Long idcoa) {
		this.idcoa = idcoa;
	}
	public String getCoaName() {
		return coaName;
	}
	public void setCoaName(String coaName) {
		this.coaName = coaName;
	}
	public Long getIdbank() {
		return idbank;
	}
	public void setIdbank(Long idbank) {
		this.idbank = idbank;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPaymentto() {
		return paymentto;
	}
	public void setPaymentto(String paymentto) {
		this.paymentto = paymentto;
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
	public PengeluaranKasBankTemplate getTemplate() {
		return template;
	}
	public void setTemplate(PengeluaranKasBankTemplate template) {
		this.template = template;
	}
	public List<DetailPengeluaranKasBankData> getDetails() {
		return details;
	}
	public void setDetails(List<DetailPengeluaranKasBankData> details) {
		this.details = details;
	}
	public boolean isDisablededitordelete() {
		return disablededitordelete;
	}
	public void setDisablededitordelete(boolean disablededitordelete) {
		this.disablededitordelete = disablededitordelete;
	}
}
