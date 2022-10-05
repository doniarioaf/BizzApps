package com.servlet.parametermanggala.service;

import java.util.List;

import com.servlet.parametermanggala.entity.BodyParameterManggala;
import com.servlet.parametermanggala.entity.ParameterManggalaData;
import com.servlet.shared.ReturnData;

public interface ParameterManggalaService {
	List<ParameterManggalaData> getListAll(Long idcompany,Long idbranch);
	List<ParameterManggalaData> getListActive(Long idcompany,Long idbranch);
	ParameterManggalaData getById(Long idcompany,Long idbranch,Long id);
	ParameterManggalaData getByParamName(Long idcompany,Long idbranch,String paramName);
	ReturnData saveParameterManggala(Long idcompany,Long idbranch,Long iduser,BodyParameterManggala body);
	ReturnData updateParameterManggala(Long idcompany,Long idbranch,Long iduser,Long id,BodyParameterManggala body);
	ReturnData deleteParameterManggala(Long idcompany,Long idbranch,Long iduser,Long id);
}
