package com.servlet.report.entity;

import org.apache.poi.xslf.usermodel.XMLSlideShow;

public class ReportToPPT {
	private XMLSlideShow ppt;

	public XMLSlideShow getPpt() {
		return ppt;
	}

	public void setPpt(XMLSlideShow ppt) {
		this.ppt = ppt;
	}
}
