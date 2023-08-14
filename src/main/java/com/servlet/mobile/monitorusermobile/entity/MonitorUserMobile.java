package com.servlet.mobile.monitorusermobile.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_monitor_user_mobile", schema = "public")
public class MonitorUserMobile implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="monitorusermobile_id_seq")
	private long id;	
	private long idcompany;
	private long idbranch;
	private long idproject;
	private long idusermobile;
	private long idcustomer;
	private Timestamp tanggal;
	private String checkintime;
	private String checkouttime;
	private String latitudein;
	private String longitudein;
	private String latitudeout;
	private String longitudeout;
	private String photo1;
	private String photo2;
	private String photo3;
	private String photo4;
	private String photo5;
	private String photo6;
	private String photo7;
	private String photo8;
	private Timestamp created;
	private Timestamp modified;
	private long idcallplan;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}
	public long getIdbranch() {
		return idbranch;
	}
	public void setIdbranch(long idbranch) {
		this.idbranch = idbranch;
	}
	public long getIdproject() {
		return idproject;
	}
	public void setIdproject(long idproject) {
		this.idproject = idproject;
	}
	public long getIdusermobile() {
		return idusermobile;
	}
	public void setIdusermobile(long idusermobile) {
		this.idusermobile = idusermobile;
	}
	public long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public Timestamp getTanggal() {
		return tanggal;
	}
	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
	public String getCheckintime() {
		return checkintime;
	}
	public void setCheckintime(String checkintime) {
		this.checkintime = checkintime;
	}
	public String getCheckouttime() {
		return checkouttime;
	}
	public void setCheckouttime(String checkouttime) {
		this.checkouttime = checkouttime;
	}
	public String getLatitudein() {
		return latitudein;
	}
	public void setLatitudein(String latitudein) {
		this.latitudein = latitudein;
	}
	public String getLongitudein() {
		return longitudein;
	}
	public void setLongitudein(String longitudein) {
		this.longitudein = longitudein;
	}
	public String getLatitudeout() {
		return latitudeout;
	}
	public void setLatitudeout(String latitudeout) {
		this.latitudeout = latitudeout;
	}
	public String getLongitudeout() {
		return longitudeout;
	}
	public void setLongitudeout(String longitudeout) {
		this.longitudeout = longitudeout;
	}
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getPhoto2() {
		return photo2;
	}
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}
	public String getPhoto3() {
		return photo3;
	}
	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}
	public String getPhoto4() {
		return photo4;
	}
	public void setPhoto4(String photo4) {
		this.photo4 = photo4;
	}
	public String getPhoto5() {
		return photo5;
	}
	public void setPhoto5(String photo5) {
		this.photo5 = photo5;
	}
	public String getPhoto6() {
		return photo6;
	}
	public void setPhoto6(String photo6) {
		this.photo6 = photo6;
	}
	public String getPhoto7() {
		return photo7;
	}
	public void setPhoto7(String photo7) {
		this.photo7 = photo7;
	}
	public String getPhoto8() {
		return photo8;
	}
	public void setPhoto8(String photo8) {
		this.photo8 = photo8;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getModified() {
		return modified;
	}
	public void setModified(Timestamp modified) {
		this.modified = modified;
	}
	public long getIdcallplan() {
		return idcallplan;
	}
	public void setIdcallplan(long idcallplan) {
		this.idcallplan = idcallplan;
	}
}
