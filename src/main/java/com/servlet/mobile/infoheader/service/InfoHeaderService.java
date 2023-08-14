package com.servlet.mobile.infoheader.service;

import java.util.List;
import com.servlet.mobile.infoheader.entity.BodyInfoHeader;
import com.servlet.mobile.infoheader.entity.BodyInfoHeaderUpdate;
import com.servlet.mobile.infoheader.entity.InfoHeaderData;
import com.servlet.mobile.infoheader.entity.InfoHeaderDetailData;
import com.servlet.mobile.infoheader.entity.ListInfoHeader;
import com.servlet.mobile.infoheader.entity.TemplateInfo;
import com.servlet.shared.ReturnData;

public interface InfoHeaderService {
	ReturnData saveInfoHeader(BodyInfoHeader body,long idcompany,long idbranch);
	ReturnData updateInfoHeader(long id,BodyInfoHeaderUpdate body,long idcompany,long idbranch);
	List<InfoHeaderData> getAllListData(long idcompany,long idbranch);
	InfoHeaderDetailData getDetailById(long id,long idcompany,long idbranch);
	List<InfoHeaderDetailData> getAllListDataMobile(long idcompany,long idbranch);
	TemplateInfo getTemplate(long idcompany,long idbranch);
	ReturnData deleteInfo(long id);
	List<InfoHeaderData> getAllListDataForReport(long idcompany,long idbranch);
	List<ListInfoHeader> getAllListDataWeb(long idcompany,long idbranch);
}
