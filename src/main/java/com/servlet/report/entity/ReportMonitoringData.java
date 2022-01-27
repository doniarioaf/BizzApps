package com.servlet.report.entity;

import java.util.List;

public class ReportMonitoringData {
	private MonitoringData monitoring;
	private List<MonitoringInfoData> listanswer;
	
	public List<MonitoringInfoData> getListanswer() {
		return listanswer;
	}
	public void setListanswer(List<MonitoringInfoData> listanswer) {
		this.listanswer = listanswer;
	}
	public MonitoringData getMonitoring() {
		return monitoring;
	}
	public void setMonitoring(MonitoringData monitoring) {
		this.monitoring = monitoring;
	}
}
