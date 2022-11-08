package com.servlet.coa.service;

import java.util.List;

import com.servlet.coa.entity.BodyCoa;
import com.servlet.coa.entity.CoaData;
import com.servlet.shared.ReturnData;

public interface CoaService {
	List<CoaData> getListAll(Long idcompany,Long idbranch);
	CoaData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveCoa(Long idcompany,Long idbranch,Long iduser,BodyCoa body);
	List<CoaData> getListActiveCOA(Long idcompany,Long idbranch);
	ReturnData uploadCSV(Long idcompany,Long idbranch,Long iduser);
}
