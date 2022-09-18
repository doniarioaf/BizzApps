package com.servlet.customermanggala.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.servlet.admin.rolepermissions.entity.RolePermissionsPK;

@Entity
@Table(name = "detail_customer_manggala_info_kementerian", schema = "public")
public class DetailCustomerManggalaInfoKementerian implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailCustomerManggalaInfoKementerianPK detailCustomerManggalaInfoKementerianPK;
	private String kementerian;
	private String alamat_email;
	private String password_email;
	
	
	public String getKementerian() {
		return kementerian;
	}
	public void setKementerian(String kementerian) {
		this.kementerian = kementerian;
	}
	public String getAlamat_email() {
		return alamat_email;
	}
	public void setAlamat_email(String alamat_email) {
		this.alamat_email = alamat_email;
	}
	public String getPassword_email() {
		return password_email;
	}
	public void setPassword_email(String password_email) {
		this.password_email = password_email;
	}
	public DetailCustomerManggalaInfoKementerianPK getDetailCustomerManggalaInfoKementerianPK() {
		return detailCustomerManggalaInfoKementerianPK;
	}
	public void setDetailCustomerManggalaInfoKementerianPK(
			DetailCustomerManggalaInfoKementerianPK detailCustomerManggalaInfoKementerianPK) {
		this.detailCustomerManggalaInfoKementerianPK = detailCustomerManggalaInfoKementerianPK;
	}

}
