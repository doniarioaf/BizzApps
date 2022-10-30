package com.servlet.upload.image;

public class InfoFile {
	private String namaFile;
	private String contectType;
	private Long sizeFile;
	public String getNamaFile() {
		return namaFile;
	}
	public void setNamaFile(String namaFile) {
		this.namaFile = namaFile;
	}
	public String getContectType() {
		return contectType;
	}
	public void setContectType(String contectType) {
		this.contectType = contectType;
	}
	public Long getSizeFile() {
		return sizeFile;
	}
	public void setSizeFile(Long sizeFile) {
		this.sizeFile = sizeFile;
	}
}
