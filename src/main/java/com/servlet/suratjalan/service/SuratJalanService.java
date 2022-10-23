package com.servlet.suratjalan.service;

import java.util.List;

import com.servlet.shared.ReturnData;
import com.servlet.suratjalan.entity.BodyStatusSuratJalan;
import com.servlet.suratjalan.entity.BodySuratJalan;
import com.servlet.suratjalan.entity.SuratJalanData;
import com.servlet.suratjalan.entity.SuratJalanTemplate;

public interface SuratJalanService {
	SuratJalanTemplate suratJalanTemplate(long idcompany,long idbranch);
	List<SuratJalanData> getListAll(Long idcompany,Long idbranch);
	List<SuratJalanData> getListActive(Long idcompany,Long idbranch);
	SuratJalanData getById(Long idcompany,Long idbranch,Long id);
	SuratJalanData getTemplateWithDataById(Long idcompany,Long idbranch,Long id);
	ReturnData saveObject(Long idcompany,Long idbranch,Long iduser,BodySuratJalan body);
	ReturnData updateObject(Long idcompany,Long idbranch,Long iduser,Long id,BodySuratJalan body);
	ReturnData deleteObject(Long idcompany,Long idbranch,Long iduser,Long id);
	ReturnData updateStatus(Long idcompany,Long idbranch,Long iduser,Long id,BodyStatusSuratJalan body);
	SuratJalanTemplate getTemplate(Long idcompany,Long idbranch);
}
