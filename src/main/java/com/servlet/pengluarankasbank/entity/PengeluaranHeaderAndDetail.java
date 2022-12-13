package com.servlet.pengluarankasbank.entity;

import java.util.List;

public class PengeluaranHeaderAndDetail {
	private List<PengeluaranKasBankData> headers;
	private List<DetailPengeluaranKasBankData> details;
	public List<PengeluaranKasBankData> getHeaders() {
		return headers;
	}
	public void setHeaders(List<PengeluaranKasBankData> headers) {
		this.headers = headers;
	}
	public List<DetailPengeluaranKasBankData> getDetails() {
		return details;
	}
	public void setDetails(List<DetailPengeluaranKasBankData> details) {
		this.details = details;
	}
}
