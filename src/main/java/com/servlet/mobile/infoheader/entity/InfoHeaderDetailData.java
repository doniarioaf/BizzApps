package com.servlet.mobile.infoheader.entity;

import java.util.List;

public class InfoHeaderDetailData {
	private InfoHeaderData infoheader;
	private List<com.servlet.mobile.infodetail.entity.InfoHeaderDetailData> detail;

	public List<com.servlet.mobile.infodetail.entity.InfoHeaderDetailData> getDetail() {
		return detail;
	}

	public void setDetail(List<com.servlet.mobile.infodetail.entity.InfoHeaderDetailData> detail) {
		this.detail = detail;
	}

//	public ListInfoHeader getInfoheader() {
//		return infoheader;
//	}
//
//	public void setInfoheader(ListInfoHeader infoheader) {
//		this.infoheader = infoheader;
//	}

	public InfoHeaderData getInfoheader() {
		return infoheader;
	}

	public void setInfoheader(InfoHeaderData infoheader) {
		this.infoheader = infoheader;
	}
}
