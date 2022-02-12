package com.servlet.mobile.project.service;

import java.util.List;

import com.servlet.mobile.project.entity.BodyProject;
import com.servlet.mobile.project.entity.ProjectData;
import com.servlet.mobile.project.entity.ProjectDetailData;
import com.servlet.mobile.project.entity.ProjectTemplateData;
import com.servlet.shared.ReturnData;

public interface ProjectService {
	ReturnData saveProject(BodyProject body,long idcompany,long idbranch);
	ReturnData updateProject(long id,BodyProject body,long idcompany,long idbranch);
	List<ProjectData> getAllListProject(long idcompany,long idbranch);
	ProjectData getProjectById(long id,long idcompany,long idbranch);
	ProjectData getProjectByProjectNumber(String projectnumber,long idcompany,long idbranch);
	ProjectDetailData getProjectByIdDetail(long id,long idcompany,long idbranch);
	ProjectTemplateData getTemplate(long idcompany,long idbranch);
	ReturnData deleteProject(long id);
}
