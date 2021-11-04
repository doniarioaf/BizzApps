package com.servlet.user.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_user_apps", schema = "public")
public class UserApps implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="user_id_seq")
	private long id;	
	private String username;
	private String password;
	private String nama;
	private String notelepon;
	private String address;
	private boolean isactive;
	private long idcompany;
	private long idbranch;
	private String email;
	private String token;
	private boolean isdelete;
	private Timestamp created;
	private Timestamp modified;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean isIsdelete() {
		return isdelete;
	}
	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNotelepon() {
		return notelepon;
	}
	public void setNotelepon(String notelepon) {
		this.notelepon = notelepon;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
//	public UserApps(long id, String username, String password, String nama, String notelepon, boolean isactive,
//			long idcompany, long idbranch, String email, String token) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.nama = nama;
//		this.notelepon = notelepon;
//		this.isactive = isactive;
//		this.idcompany = idcompany;
//		this.idbranch = idbranch;
//		this.email = email;
//		this.token = token;
//	}
//	
	
}
