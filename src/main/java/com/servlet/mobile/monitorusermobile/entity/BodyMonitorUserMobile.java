package com.servlet.mobile.monitorusermobile.entity;

import java.util.List;

public class BodyMonitorUserMobile {
	private long idproject;
	private long idcustomer;
	private long tanggal;
	private Long chekintime;
	private Long chekouttime;
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
	private List<BodyInfoDetail> infodetails;
	public List<BodyInfoDetail> getInfodetails() {
		return infodetails;
	}
	public void setInfodetails(List<BodyInfoDetail> infodetails) {
		this.infodetails = infodetails;
	}
	public long getIdproject() {
		return idproject;
	}
	public void setIdproject(long idproject) {
		this.idproject = idproject;
	}
	public long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public long getTanggal() {
		return tanggal;
	}
	public void setTanggal(long tanggal) {
		this.tanggal = tanggal;
	}
	public Long getChekintime() {
		return chekintime;
	}
	public void setChekintime(Long chekintime) {
		this.chekintime = chekintime;
	}
	public Long getChekouttime() {
		return chekouttime;
	}
	public void setChekouttime(Long chekouttime) {
		this.chekouttime = chekouttime;
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
}
