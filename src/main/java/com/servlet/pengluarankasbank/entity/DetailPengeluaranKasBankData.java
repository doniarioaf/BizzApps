package com.servlet.pengluarankasbank.entity;

public class DetailPengeluaranKasBankData {
	private Long idpengeluarankasbank;
	private Long idcoa;
	private String coaName;
	private String catatan;
	private Double amount;
	private Long idasset;
	private String assetName;
	private String assetNameKepala;
	private String assetNameBuntut;
	private Long idinvoiceitem;
	private String invoiceitemName;
	private Long idpaymentitem;
	private String paymentitemName;
	private Long idassetsparepart;
	private String assetsparepartNameKepala;
	private String assetsparepartNameBuntut;
	private String sparepartassettype;
	private String sparepartassettypeName;
	
	public String getAssetsparepartNameKepala() {
		return assetsparepartNameKepala;
	}
	public void setAssetsparepartNameKepala(String assetsparepartNameKepala) {
		this.assetsparepartNameKepala = assetsparepartNameKepala;
	}
	public String getAssetsparepartNameBuntut() {
		return assetsparepartNameBuntut;
	}
	public void setAssetsparepartNameBuntut(String assetsparepartNameBuntut) {
		this.assetsparepartNameBuntut = assetsparepartNameBuntut;
	}
	
	public Long getIdpaymentitem() {
		return idpaymentitem;
	}
	public void setIdpaymentitem(Long idpaymentitem) {
		this.idpaymentitem = idpaymentitem;
	}
	public String getPaymentitemName() {
		return paymentitemName;
	}
	public void setPaymentitemName(String paymentitemName) {
		this.paymentitemName = paymentitemName;
	}
	public Long getIdassetsparepart() {
		return idassetsparepart;
	}
	public void setIdassetsparepart(Long idassetsparepart) {
		this.idassetsparepart = idassetsparepart;
	}
	public String getSparepartassettype() {
		return sparepartassettype;
	}
	public void setSparepartassettype(String sparepartassettype) {
		this.sparepartassettype = sparepartassettype;
	}
	public String getSparepartassettypeName() {
		return sparepartassettypeName;
	}
	public void setSparepartassettypeName(String sparepartassettypeName) {
		this.sparepartassettypeName = sparepartassettypeName;
	}
	public Long getIdpengeluarankasbank() {
		return idpengeluarankasbank;
	}
	public void setIdpengeluarankasbank(Long idpengeluarankasbank) {
		this.idpengeluarankasbank = idpengeluarankasbank;
	}
	public String getInvoiceitemName() {
		return invoiceitemName;
	}
	public void setInvoiceitemName(String invoiceitemName) {
		this.invoiceitemName = invoiceitemName;
	}
	public Long getIdinvoiceitem() {
		return idinvoiceitem;
	}
	public void setIdinvoiceitem(Long idinvoiceitem) {
		this.idinvoiceitem = idinvoiceitem;
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
	public Long getIdasset() {
		return idasset;
	}
	public void setIdasset(Long idasset) {
		this.idasset = idasset;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetNameKepala() {
		return assetNameKepala;
	}
	public void setAssetNameKepala(String assetNameKepala) {
		this.assetNameKepala = assetNameKepala;
	}
	public String getAssetNameBuntut() {
		return assetNameBuntut;
	}
	public void setAssetNameBuntut(String assetNameBuntut) {
		this.assetNameBuntut = assetNameBuntut;
	}
}
