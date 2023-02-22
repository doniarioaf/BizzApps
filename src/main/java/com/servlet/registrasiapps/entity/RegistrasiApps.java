package com.servlet.registrasiapps.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_registrasi_apps", schema = "public")
public class RegistrasiApps implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="m_registrasi_apps_seq")
	private Long id;
	private Long idcompany;
	private Long idbranch;
	private String keyapps;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(Long idcompany) {
		this.idcompany = idcompany;
	}
	public Long getIdbranch() {
		return idbranch;
	}
	public void setIdbranch(Long idbranch) {
		this.idbranch = idbranch;
	}
	public String getKeyapps() {
		return keyapps;
	}
	public void setKeyapps(String keyapps) {
		this.keyapps = keyapps;
	}
	
	
}
