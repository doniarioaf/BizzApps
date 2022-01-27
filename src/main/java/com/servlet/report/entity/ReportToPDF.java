package com.servlet.report.entity;

import java.io.ByteArrayInputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;

public class ReportToPDF {
	private PdfPTable table;
	private Document document;
	public PdfPTable getTable() {
		return table;
	}
	public void setTable(PdfPTable table) {
		this.table = table;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}

	
}
