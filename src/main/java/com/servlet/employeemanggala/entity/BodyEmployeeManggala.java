package com.servlet.employeemanggala.entity;


public class BodyEmployeeManggala {
	private String code;
	private String statuskaryawan;
	private String jabatan;
	private String nama;
	private String noidentitas;
	private String alamat;
	private Long tanggallahir;
	private String status;
	private String namapasangan;
	private Long tanggallahirpasangan;
	private String namabank;
	private String norekening;
	private String atasnama;
	private Long tanggalmulai;
	private Long tanggalresign;
	private String gaji;
	private String jeniskelamin;
	private boolean isactive;
	private BodyDetailEmployeeManggalaInfoFamily[] detailsInfoFamily;
	private String alamat2;
	private String alamat3;
	
	public String getAlamat2() {
		return alamat2;
	}
	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}
	public String getAlamat3() {
		return alamat3;
	}
	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}
	public BodyDetailEmployeeManggalaInfoFamily[] getDetailsInfoFamily() {
		return detailsInfoFamily;
	}
	public void setDetailsInfoFamily(BodyDetailEmployeeManggalaInfoFamily[] detailsInfoFamily) {
		this.detailsInfoFamily = detailsInfoFamily;
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
	public Long getTanggallahir() {
		return tanggallahir;
	}
	public void setTanggallahir(Long tanggallahir) {
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
	public Long getTanggallahirpasangan() {
		return tanggallahirpasangan;
	}
	public void setTanggallahirpasangan(Long tanggallahirpasangan) {
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
	public Long getTanggalmulai() {
		return tanggalmulai;
	}
	public void setTanggalmulai(Long tanggalmulai) {
		this.tanggalmulai = tanggalmulai;
	}
	public Long getTanggalresign() {
		return tanggalresign;
	}
	public void setTanggalresign(Long tanggalresign) {
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
}
