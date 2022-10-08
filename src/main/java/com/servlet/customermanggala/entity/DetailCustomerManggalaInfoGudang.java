package com.servlet.customermanggala.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "detail_customer_manggala_info_gudang", schema = "public")
public class DetailCustomerManggalaInfoGudang implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailCustomerManggalaInfoGudangPK detailCustomerManggalaInfoGudangPK;
	private Long idwarehouse;
	public DetailCustomerManggalaInfoGudangPK getDetailCustomerManggalaInfoGudangPK() {
		return detailCustomerManggalaInfoGudangPK;
	}
	public void setDetailCustomerManggalaInfoGudangPK(
			DetailCustomerManggalaInfoGudangPK detailCustomerManggalaInfoGudangPK) {
		this.detailCustomerManggalaInfoGudangPK = detailCustomerManggalaInfoGudangPK;
	}
	public Long getIdwarehouse() {
		return idwarehouse;
	}
	public void setIdwarehouse(Long idwarehouse) {
		this.idwarehouse = idwarehouse;
	}
	
	
}
