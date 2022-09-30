package com.servlet.employeemanggala.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_employee_manggala", schema = "public")
public class EmployeeManggala implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="employee_manggala_id_seq")
	private Long id;
	private Long idcompany;
	private Long idbranch;
	private String code;
	private String statuskaryawan;
	private String jabatan;
	private String nama;
	private String noidentitas;
	private String alamat;
	private Date tanggallahir;
	private String status;
	private String namapasangan;
	private Date tanggallahirpasangan;
	private String namabank;
	private String norekening;
	private String atasnama;
	private Date tanggalmulai;
	private Date tanggalresign;
	private String gaji;
	private String jeniskelamin;
	private boolean isactive;
	private boolean isdelete;
	private String createdby;
	private Timestamp createddate;
	private String updateby;
	private Timestamp updatedate;
	private String deleteby;
	private Timestamp deletedate;
	private String photo;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatuskaryawan() {
		return statuskaryawan;
	}
	public void setStatuskaryawan(String statuskaryawan) {
		this.statuskaryawan = statuskaryawan;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNoidentitas() {
		return noidentitas;
	}
	public void setNoidentitas(String noidentitas) {
		this.noidentitas = noidentitas;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public Date getTanggallahir() {
		return tanggallahir;
	}
	public void setTanggallahir(Date tanggallahir) {
		this.tanggallahir = tanggallahir;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNamapasangan() {
		return namapasangan;
	}
	public void setNamapasangan(String namapasangan) {
		this.namapasangan = namapasangan;
	}
	public Date getTanggallahirpasangan() {
		return tanggallahirpasangan;
	}
	public void setTanggallahirpasangan(Date tanggallahirpasangan) {
		this.tanggallahirpasangan = tanggallahirpasangan;
	}
	public String getNamabank() {
		return namabank;
	}
	public void setNamabank(String namabank) {
		this.namabank = namabank;
	}
	public String getNorekening() {
		return norekening;
	}
	public void setNorekening(String norekening) {
		this.norekening = norekening;
	}
	public String getAtasnama() {
		return atasnama;
	}
	public void setAtasnama(String atasnama) {
		this.atasnama = atasnama;
	}
	public Date getTanggalmulai() {
		return tanggalmulai;
	}
	public void setTanggalmulai(Date tanggalmulai) {
		this.tanggalmulai = tanggalmulai;
	}
	public Date getTanggalresign() {
		return tanggalresign;
	}
	public void setTanggalresign(Date tanggalresign) {
		this.tanggalresign = tanggalresign;
	}
	public String getGaji() {
		return gaji;
	}
	public void setGaji(String gaji) {
		this.gaji = gaji;
	}
	public String getJeniskelamin() {
		return jeniskelamin;
	}
	public void setJeniskelamin(String jeniskelamin) {
		this.jeniskelamin = jeniskelamin;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public boolean isIsdelete() {
		return isdelete;
	}
	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public Timestamp getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
	public String getDeleteby() {
		return deleteby;
	}
	public void setDeleteby(String deleteby) {
		this.deleteby = deleteby;
	}
	public Timestamp getDeletedate() {
		return deletedate;
	}
	public void setDeletedate(Timestamp deletedate) {
		this.deletedate = deletedate;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
