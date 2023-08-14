package com.servlet.asset.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_asset", schema = "public")
public class Asset implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="m_asset_id_seq")
	private Long id;
	private Long idcompany;
	private Long idbranch;
	private String kodeasset;
	private String assettype;
	private String kepala_nama;
	private String kepala_nopolisi;
	private String kepala_jeniskendaraan;
	private String kepala_merk;
	private String kepala_nomesin;
	private String kepala_norangka;
	private String kepala_nostnk;
	private Date kepala_masaberlakustnk;
	private String kepala_kir;
	private Date kepala_masaberlakukir;
	private Date kepala_lunastanggal;
	private String kepala_keterangan;
	private String buntut_nama;
	private String buntut_nobuntut;
	private String buntut_nobearingluar;
	private String buntut_nobearingdalam;
	private String buntut_nokir;
	private Date buntut_masaberlakukir;
	private String buntut_rangka;
	private String buntut_merkaxel;
	private String buntut_jenisaxel;
	private String buntut_jenishole;
	private Date buntut_lunastanggal;
	private String sparepartkepala_nama;
	private String sparepartkepala_jenis;
	private String sparepartkepala_keterangan;
	private String sparepartkepala_bearing_nobearing;
	private String sparepartkepala_bearing_posisibearing;
	private String sparepartkepala_bearing_merk;
	private String sparepartkepala_bearing_jenishole;
	private String sparepartkepala_bearing_kotakbulat;
	private String sparepartkepala_ban_nama;
	private String sparepartkepala_ban_keterangan;
	private String sparepartkepala_ban_posisi;
	private String sparepartkepala_ban_jenis;
	private String sparepartkepala_ban_ukuran;
	private String sparepartkepala_ban_status;
	private String sparepartkepala_lainnya_nama;
	private String sparepartkepala_lainnya_keterangan;
	private String sparepartbuntut_nama;
	private String sparepartbuntut_jenis;
	private String sparepartbuntut_keterangan;
	private String sparepartbuntut_bearing_nobearing;
	private String sparepartbuntut_bearing_posisi;
	private String sparepartbuntut_bearing_merk;
	private String sparepartbuntut_bearing_jenishole;
	private String sparepartbuntut_bearing_kotakbulat;
	private String sparepartbuntut_ban_nama;
	private String sparepartbuntut_ban_keterangan;
	private String sparepartbuntut_ban_posisi;
	private String sparepartbuntut_ban_jenis;
	private String sparepartbuntut_ban_ukuran;
	private String sparepartbuntut_ban_status;
	private String sparepartbuntut_lainnya_nama;
	private String sparepartbuntut_lainnya_keterangan;
	
	private String sparepartkepala_filter_type;
	private String sparepartkepala_filter_posisi;
	private String sparepartkepala_bohlam_type;
	private String sparepartkepala_selang_type;
	private String sparepartbuntut_filter_type;
	private String sparepartbuntut_filter_posisi;
	private String sparepartbuntut_bohlam_type;
	private String sparepartbuntut_selang_type;
	private boolean isactive;
	private boolean isdelete;
	private String createdby;
	private Timestamp createddate;
	private String updateby;
	private Timestamp updatedate;
	private String deleteby;
	private Timestamp deletedate;
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
	public String getKodeasset() {
		return kodeasset;
	}
	public void setKodeasset(String kodeasset) {
		this.kodeasset = kodeasset;
	}
	public String getAssettype() {
		return assettype;
	}
	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}
	public String getKepala_nama() {
		return kepala_nama;
	}
	public void setKepala_nama(String kepala_nama) {
		this.kepala_nama = kepala_nama;
	}
	public String getKepala_nopolisi() {
		return kepala_nopolisi;
	}
	public void setKepala_nopolisi(String kepala_nopolisi) {
		this.kepala_nopolisi = kepala_nopolisi;
	}
	public String getKepala_jeniskendaraan() {
		return kepala_jeniskendaraan;
	}
	public void setKepala_jeniskendaraan(String kepala_jeniskendaraan) {
		this.kepala_jeniskendaraan = kepala_jeniskendaraan;
	}
	public String getKepala_merk() {
		return kepala_merk;
	}
	public void setKepala_merk(String kepala_merk) {
		this.kepala_merk = kepala_merk;
	}
	public String getKepala_nomesin() {
		return kepala_nomesin;
	}
	public void setKepala_nomesin(String kepala_nomesin) {
		this.kepala_nomesin = kepala_nomesin;
	}
	public String getKepala_norangka() {
		return kepala_norangka;
	}
	public void setKepala_norangka(String kepala_norangka) {
		this.kepala_norangka = kepala_norangka;
	}
	public String getKepala_nostnk() {
		return kepala_nostnk;
	}
	public void setKepala_nostnk(String kepala_nostnk) {
		this.kepala_nostnk = kepala_nostnk;
	}
	public Date getKepala_masaberlakustnk() {
		return kepala_masaberlakustnk;
	}
	public void setKepala_masaberlakustnk(Date kepala_masaberlakustnk) {
		this.kepala_masaberlakustnk = kepala_masaberlakustnk;
	}
	public String getKepala_kir() {
		return kepala_kir;
	}
	public void setKepala_kir(String kepala_kir) {
		this.kepala_kir = kepala_kir;
	}
	public Date getKepala_masaberlakukir() {
		return kepala_masaberlakukir;
	}
	public void setKepala_masaberlakukir(Date kepala_masaberlakukir) {
		this.kepala_masaberlakukir = kepala_masaberlakukir;
	}
	public Date getKepala_lunastanggal() {
		return kepala_lunastanggal;
	}
	public void setKepala_lunastanggal(Date kepala_lunastanggal) {
		this.kepala_lunastanggal = kepala_lunastanggal;
	}
	public String getKepala_keterangan() {
		return kepala_keterangan;
	}
	public void setKepala_keterangan(String kepala_keterangan) {
		this.kepala_keterangan = kepala_keterangan;
	}
	public String getBuntut_nama() {
		return buntut_nama;
	}
	public void setBuntut_nama(String buntut_nama) {
		this.buntut_nama = buntut_nama;
	}
	public String getBuntut_nobuntut() {
		return buntut_nobuntut;
	}
	public void setBuntut_nobuntut(String buntut_nobuntut) {
		this.buntut_nobuntut = buntut_nobuntut;
	}
	public String getBuntut_nobearingluar() {
		return buntut_nobearingluar;
	}
	public void setBuntut_nobearingluar(String buntut_nobearingluar) {
		this.buntut_nobearingluar = buntut_nobearingluar;
	}
	public String getBuntut_nobearingdalam() {
		return buntut_nobearingdalam;
	}
	public void setBuntut_nobearingdalam(String buntut_nobearingdalam) {
		this.buntut_nobearingdalam = buntut_nobearingdalam;
	}
	public String getBuntut_nokir() {
		return buntut_nokir;
	}
	public void setBuntut_nokir(String buntut_nokir) {
		this.buntut_nokir = buntut_nokir;
	}
	public Date getBuntut_masaberlakukir() {
		return buntut_masaberlakukir;
	}
	public void setBuntut_masaberlakukir(Date buntut_masaberlakukir) {
		this.buntut_masaberlakukir = buntut_masaberlakukir;
	}
	public String getBuntut_rangka() {
		return buntut_rangka;
	}
	public void setBuntut_rangka(String buntut_rangka) {
		this.buntut_rangka = buntut_rangka;
	}
	public String getBuntut_merkaxel() {
		return buntut_merkaxel;
	}
	public void setBuntut_merkaxel(String buntut_merkaxel) {
		this.buntut_merkaxel = buntut_merkaxel;
	}
	public String getBuntut_jenisaxel() {
		return buntut_jenisaxel;
	}
	public void setBuntut_jenisaxel(String buntut_jenisaxel) {
		this.buntut_jenisaxel = buntut_jenisaxel;
	}
	public String getBuntut_jenishole() {
		return buntut_jenishole;
	}
	public void setBuntut_jenishole(String buntut_jenishole) {
		this.buntut_jenishole = buntut_jenishole;
	}
	public Date getBuntut_lunastanggal() {
		return buntut_lunastanggal;
	}
	public void setBuntut_lunastanggal(Date buntut_lunastanggal) {
		this.buntut_lunastanggal = buntut_lunastanggal;
	}
	public String getSparepartkepala_nama() {
		return sparepartkepala_nama;
	}
	public void setSparepartkepala_nama(String sparepartkepala_nama) {
		this.sparepartkepala_nama = sparepartkepala_nama;
	}
	public String getSparepartkepala_jenis() {
		return sparepartkepala_jenis;
	}
	public void setSparepartkepala_jenis(String sparepartkepala_jenis) {
		this.sparepartkepala_jenis = sparepartkepala_jenis;
	}
	public String getSparepartkepala_keterangan() {
		return sparepartkepala_keterangan;
	}
	public void setSparepartkepala_keterangan(String sparepartkepala_keterangan) {
		this.sparepartkepala_keterangan = sparepartkepala_keterangan;
	}
	public String getSparepartkepala_bearing_nobearing() {
		return sparepartkepala_bearing_nobearing;
	}
	public void setSparepartkepala_bearing_nobearing(String sparepartkepala_bearing_nobearing) {
		this.sparepartkepala_bearing_nobearing = sparepartkepala_bearing_nobearing;
	}
	public String getSparepartkepala_bearing_posisibearing() {
		return sparepartkepala_bearing_posisibearing;
	}
	public void setSparepartkepala_bearing_posisibearing(String sparepartkepala_bearing_posisibearing) {
		this.sparepartkepala_bearing_posisibearing = sparepartkepala_bearing_posisibearing;
	}
	public String getSparepartkepala_bearing_merk() {
		return sparepartkepala_bearing_merk;
	}
	public void setSparepartkepala_bearing_merk(String sparepartkepala_bearing_merk) {
		this.sparepartkepala_bearing_merk = sparepartkepala_bearing_merk;
	}
	public String getSparepartkepala_bearing_jenishole() {
		return sparepartkepala_bearing_jenishole;
	}
	public void setSparepartkepala_bearing_jenishole(String sparepartkepala_bearing_jenishole) {
		this.sparepartkepala_bearing_jenishole = sparepartkepala_bearing_jenishole;
	}
	public String getSparepartkepala_bearing_kotakbulat() {
		return sparepartkepala_bearing_kotakbulat;
	}
	public void setSparepartkepala_bearing_kotakbulat(String sparepartkepala_bearing_kotakbulat) {
		this.sparepartkepala_bearing_kotakbulat = sparepartkepala_bearing_kotakbulat;
	}
	public String getSparepartkepala_ban_nama() {
		return sparepartkepala_ban_nama;
	}
	public void setSparepartkepala_ban_nama(String sparepartkepala_ban_nama) {
		this.sparepartkepala_ban_nama = sparepartkepala_ban_nama;
	}
	public String getSparepartkepala_ban_keterangan() {
		return sparepartkepala_ban_keterangan;
	}
	public void setSparepartkepala_ban_keterangan(String sparepartkepala_ban_keterangan) {
		this.sparepartkepala_ban_keterangan = sparepartkepala_ban_keterangan;
	}
	public String getSparepartkepala_ban_posisi() {
		return sparepartkepala_ban_posisi;
	}
	public void setSparepartkepala_ban_posisi(String sparepartkepala_ban_posisi) {
		this.sparepartkepala_ban_posisi = sparepartkepala_ban_posisi;
	}
	public String getSparepartkepala_ban_jenis() {
		return sparepartkepala_ban_jenis;
	}
	public void setSparepartkepala_ban_jenis(String sparepartkepala_ban_jenis) {
		this.sparepartkepala_ban_jenis = sparepartkepala_ban_jenis;
	}
	public String getSparepartkepala_ban_ukuran() {
		return sparepartkepala_ban_ukuran;
	}
	public void setSparepartkepala_ban_ukuran(String sparepartkepala_ban_ukuran) {
		this.sparepartkepala_ban_ukuran = sparepartkepala_ban_ukuran;
	}
	public String getSparepartkepala_ban_status() {
		return sparepartkepala_ban_status;
	}
	public void setSparepartkepala_ban_status(String sparepartkepala_ban_status) {
		this.sparepartkepala_ban_status = sparepartkepala_ban_status;
	}
	public String getSparepartkepala_lainnya_nama() {
		return sparepartkepala_lainnya_nama;
	}
	public void setSparepartkepala_lainnya_nama(String sparepartkepala_lainnya_nama) {
		this.sparepartkepala_lainnya_nama = sparepartkepala_lainnya_nama;
	}
	public String getSparepartkepala_lainnya_keterangan() {
		return sparepartkepala_lainnya_keterangan;
	}
	public void setSparepartkepala_lainnya_keterangan(String sparepartkepala_lainnya_keterangan) {
		this.sparepartkepala_lainnya_keterangan = sparepartkepala_lainnya_keterangan;
	}
	public String getSparepartbuntut_nama() {
		return sparepartbuntut_nama;
	}
	public void setSparepartbuntut_nama(String sparepartbuntut_nama) {
		this.sparepartbuntut_nama = sparepartbuntut_nama;
	}
	public String getSparepartbuntut_jenis() {
		return sparepartbuntut_jenis;
	}
	public void setSparepartbuntut_jenis(String sparepartbuntut_jenis) {
		this.sparepartbuntut_jenis = sparepartbuntut_jenis;
	}
	public String getSparepartbuntut_keterangan() {
		return sparepartbuntut_keterangan;
	}
	public void setSparepartbuntut_keterangan(String sparepartbuntut_keterangan) {
		this.sparepartbuntut_keterangan = sparepartbuntut_keterangan;
	}
	public String getSparepartbuntut_bearing_nobearing() {
		return sparepartbuntut_bearing_nobearing;
	}
	public void setSparepartbuntut_bearing_nobearing(String sparepartbuntut_bearing_nobearing) {
		this.sparepartbuntut_bearing_nobearing = sparepartbuntut_bearing_nobearing;
	}
	public String getSparepartbuntut_bearing_posisi() {
		return sparepartbuntut_bearing_posisi;
	}
	public void setSparepartbuntut_bearing_posisi(String sparepartbuntut_bearing_posisi) {
		this.sparepartbuntut_bearing_posisi = sparepartbuntut_bearing_posisi;
	}
	public String getSparepartbuntut_bearing_merk() {
		return sparepartbuntut_bearing_merk;
	}
	public void setSparepartbuntut_bearing_merk(String sparepartbuntut_bearing_merk) {
		this.sparepartbuntut_bearing_merk = sparepartbuntut_bearing_merk;
	}
	public String getSparepartbuntut_bearing_jenishole() {
		return sparepartbuntut_bearing_jenishole;
	}
	public void setSparepartbuntut_bearing_jenishole(String sparepartbuntut_bearing_jenishole) {
		this.sparepartbuntut_bearing_jenishole = sparepartbuntut_bearing_jenishole;
	}
	public String getSparepartbuntut_bearing_kotakbulat() {
		return sparepartbuntut_bearing_kotakbulat;
	}
	public void setSparepartbuntut_bearing_kotakbulat(String sparepartbuntut_bearing_kotakbulat) {
		this.sparepartbuntut_bearing_kotakbulat = sparepartbuntut_bearing_kotakbulat;
	}
	public String getSparepartbuntut_ban_nama() {
		return sparepartbuntut_ban_nama;
	}
	public void setSparepartbuntut_ban_nama(String sparepartbuntut_ban_nama) {
		this.sparepartbuntut_ban_nama = sparepartbuntut_ban_nama;
	}
	public String getSparepartbuntut_ban_keterangan() {
		return sparepartbuntut_ban_keterangan;
	}
	public void setSparepartbuntut_ban_keterangan(String sparepartbuntut_ban_keterangan) {
		this.sparepartbuntut_ban_keterangan = sparepartbuntut_ban_keterangan;
	}
	public String getSparepartbuntut_ban_posisi() {
		return sparepartbuntut_ban_posisi;
	}
	public void setSparepartbuntut_ban_posisi(String sparepartbuntut_ban_posisi) {
		this.sparepartbuntut_ban_posisi = sparepartbuntut_ban_posisi;
	}
	public String getSparepartbuntut_ban_jenis() {
		return sparepartbuntut_ban_jenis;
	}
	public void setSparepartbuntut_ban_jenis(String sparepartbuntut_ban_jenis) {
		this.sparepartbuntut_ban_jenis = sparepartbuntut_ban_jenis;
	}
	public String getSparepartbuntut_ban_ukuran() {
		return sparepartbuntut_ban_ukuran;
	}
	public void setSparepartbuntut_ban_ukuran(String sparepartbuntut_ban_ukuran) {
		this.sparepartbuntut_ban_ukuran = sparepartbuntut_ban_ukuran;
	}
	public String getSparepartbuntut_ban_status() {
		return sparepartbuntut_ban_status;
	}
	public void setSparepartbuntut_ban_status(String sparepartbuntut_ban_status) {
		this.sparepartbuntut_ban_status = sparepartbuntut_ban_status;
	}
	public String getSparepartbuntut_lainnya_nama() {
		return sparepartbuntut_lainnya_nama;
	}
	public void setSparepartbuntut_lainnya_nama(String sparepartbuntut_lainnya_nama) {
		this.sparepartbuntut_lainnya_nama = sparepartbuntut_lainnya_nama;
	}
	public String getSparepartbuntut_lainnya_keterangan() {
		return sparepartbuntut_lainnya_keterangan;
	}
	public void setSparepartbuntut_lainnya_keterangan(String sparepartbuntut_lainnya_keterangan) {
		this.sparepartbuntut_lainnya_keterangan = sparepartbuntut_lainnya_keterangan;
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
	public String getSparepartkepala_filter_type() {
		return sparepartkepala_filter_type;
	}
	public void setSparepartkepala_filter_type(String sparepartkepala_filter_type) {
		this.sparepartkepala_filter_type = sparepartkepala_filter_type;
	}
	public String getSparepartkepala_filter_posisi() {
		return sparepartkepala_filter_posisi;
	}
	public void setSparepartkepala_filter_posisi(String sparepartkepala_filter_posisi) {
		this.sparepartkepala_filter_posisi = sparepartkepala_filter_posisi;
	}
	public String getSparepartkepala_bohlam_type() {
		return sparepartkepala_bohlam_type;
	}
	public void setSparepartkepala_bohlam_type(String sparepartkepala_bohlam_type) {
		this.sparepartkepala_bohlam_type = sparepartkepala_bohlam_type;
	}
	public String getSparepartkepala_selang_type() {
		return sparepartkepala_selang_type;
	}
	public void setSparepartkepala_selang_type(String sparepartkepala_selang_type) {
		this.sparepartkepala_selang_type = sparepartkepala_selang_type;
	}
	public String getSparepartbuntut_filter_type() {
		return sparepartbuntut_filter_type;
	}
	public void setSparepartbuntut_filter_type(String sparepartbuntut_filter_type) {
		this.sparepartbuntut_filter_type = sparepartbuntut_filter_type;
	}
	public String getSparepartbuntut_filter_posisi() {
		return sparepartbuntut_filter_posisi;
	}
	public void setSparepartbuntut_filter_posisi(String sparepartbuntut_filter_posisi) {
		this.sparepartbuntut_filter_posisi = sparepartbuntut_filter_posisi;
	}
	public String getSparepartbuntut_bohlam_type() {
		return sparepartbuntut_bohlam_type;
	}
	public void setSparepartbuntut_bohlam_type(String sparepartbuntut_bohlam_type) {
		this.sparepartbuntut_bohlam_type = sparepartbuntut_bohlam_type;
	}
	public String getSparepartbuntut_selang_type() {
		return sparepartbuntut_selang_type;
	}
	public void setSparepartbuntut_selang_type(String sparepartbuntut_selang_type) {
		this.sparepartbuntut_selang_type = sparepartbuntut_selang_type;
	}
	
}
