package com.servlet.workorder.entity;

public class DetailWorkOrderData {
	private Long idworkorder;
	private Long idpartai;
	private String partainame;
	private String jumlahkoli;
	private String jumlahkg;
	private String nocontainer;
	private String noseal;
	private String barang;
	public Long getIdworkorder() {
		return idworkorder;
	}
	public void setIdworkorder(Long idworkorder) {
		this.idworkorder = idworkorder;
	}
	public Long getIdpartai() {
		return idpartai;
	}
	public void setIdpartai(Long idpartai) {
		this.idpartai = idpartai;
	}
	public String getPartainame() {
		return partainame;
	}
	public void setPartainame(String partainame) {
		this.partainame = partainame;
	}
	public String getJumlahkoli() {
		return jumlahkoli;
	}
	public void setJumlahkoli(String jumlahkoli) {
		this.jumlahkoli = jumlahkoli;
	}
	public String getJumlahkg() {
		return jumlahkg;
	}
	public void setJumlahkg(String jumlahkg) {
		this.jumlahkg = jumlahkg;
	}
	public String getNocontainer() {
		return nocontainer;
	}
	public void setNocontainer(String nocontainer) {
		this.nocontainer = nocontainer;
	}
	public String getNoseal() {
		return noseal;
	}
	public void setNoseal(String noseal) {
		this.noseal = noseal;
	}
	public String getBarang() {
		return barang;
	}
	public void setBarang(String barang) {
		this.barang = barang;
	}
}