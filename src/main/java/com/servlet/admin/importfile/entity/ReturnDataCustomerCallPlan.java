package com.servlet.admin.importfile.entity;

import java.util.HashMap;
import java.util.List;

import com.servlet.mobile.project.entity.BodyProject;
import com.servlet.shared.ValidationDataMessage;

public class ReturnDataCustomerCallPlan {
	private List<ValidationDataMessage> validations;
	private List<DataColumnFileCustomerCallPlan> listDataFile;
	private HashMap<String, String> mapDistinctCallPlan;
	private HashMap<String, BodyProject> mapDistinctProject;
	public List<ValidationDataMessage> getValidations() {
		return validations;
	}
	public void setValidations(List<ValidationDataMessage> validations) {
		this.validations = validations;
	}
	public List<DataColumnFileCustomerCallPlan> getListDataFile() {
		return listDataFile;
	}
	public void setListDataFile(List<DataColumnFileCustomerCallPlan> listDataFile) {
		this.listDataFile = listDataFile;
	}
	public HashMap<String, String> getMapDistinctCallPlan() {
		return mapDistinctCallPlan;
	}
	public void setMapDistinctCallPlan(HashMap<String, String> mapDistinctCallPlan) {
		this.mapDistinctCallPlan = mapDistinctCallPlan;
	}
	public HashMap<String, BodyProject> getMapDistinctProject() {
		return mapDistinctProject;
	}
	public void setMapDistinctProject(HashMap<String, BodyProject> mapDistinctProject) {
		this.mapDistinctProject = mapDistinctProject;
	}
}
