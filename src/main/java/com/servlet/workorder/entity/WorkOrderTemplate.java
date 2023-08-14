package com.servlet.workorder.entity;

import java.util.List;

import com.servlet.parameter.entity.ParameterData;
import com.servlet.partai.entity.PartaiData;
import com.servlet.port.entity.PortData;
import com.servlet.vendor.entity.VendorListData;

public class WorkOrderTemplate {
	private List<ParameterData> woTypeOptions;
	private List<ParameterData> modaTransportasiOptions;
	private List<ParameterData> jalurOptions;
	private List<PortData> portOptions;
	private List<VendorListData> vendorOptions;
	private List<PartaiData> partaiOptions;
	public List<ParameterData> getWoTypeOptions() {
		return woTypeOptions;
	}
	public void setWoTypeOptions(List<ParameterData> woTypeOptions) {
		this.woTypeOptions = woTypeOptions;
	}
	public List<ParameterData> getJalurOptions() {
		return jalurOptions;
	}
	public void setJalurOptions(List<ParameterData> jalurOptions) {
		this.jalurOptions = jalurOptions;
	}
	public List<PortData> getPortOptions() {
		return portOptions;
	}
	public void setPortOptions(List<PortData> portOptions) {
		this.portOptions = portOptions;
	}
	public List<VendorListData> getVendorOptions() {
		return vendorOptions;
	}
	public void setVendorOptions(List<VendorListData> vendorOptions) {
		this.vendorOptions = vendorOptions;
	}
	
	public List<PartaiData> getPartaiOptions() {
		return partaiOptions;
	}
	public void setPartaiOptions(List<PartaiData> partaiOptions) {
		this.partaiOptions = partaiOptions;
	}
	public List<ParameterData> getModaTransportasiOptions() {
		return modaTransportasiOptions;
	}
	public void setModaTransportasiOptions(List<ParameterData> modaTransportasiOptions) {
		this.modaTransportasiOptions = modaTransportasiOptions;
	}
	
	
}
