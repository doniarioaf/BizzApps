package com.servlet.workorder.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "detail_work_order", schema = "public")
public class DetailWorkOrder implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailWorkOrderPK detailWorkOrderPK;
	private String jumlahkoli;
	private String jumlahkg;
	private String nocontainer;
	private String noseal;
	private String barang;
	public DetailWorkOrderPK getDetailWorkOrderPK() {
		return detailWorkOrderPK;
	}
	public void setDetailWorkOrderPK(DetailWorkOrderPK detailWorkOrderPK) {
		this.detailWorkOrderPK = detailWorkOrderPK;
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
