package com.servlet.parameterclient.service;

import java.util.List;

import com.servlet.parameterclient.entity.BodyParameterClient;
import com.servlet.parameterclient.entity.ParameterClientData;
import com.servlet.parameterclient.entity.ParameterClientTemplate;
import com.servlet.parameterclient.entity.ValueParameter;
import com.servlet.shared.ReturnData;

public interface ParameterClientService {
	List<ParameterClientData> getListAll(Long idcompany, Long idbranch);
	List<ParameterClientData> getListActive(Long idcompany,Long idbranch);
	ParameterClientData getById(Long idcompany,Long idbranch,Long id);
	ParameterClientData getByParamName(Long idcompany,Long idbranch,String paramName);
	ReturnData saveParameterManggala(Long idcompany, Long idbranch, Long iduser, BodyParameterClient body);
	ReturnData updateParameterManggala(Long idcompany,Long idbranch,Long iduser,Long id,BodyParameterClient body);
	ReturnData deleteParameterManggala(Long idcompany,Long idbranch,Long iduser,Long id);
	ParameterClientTemplate getTemplate(Long idcompany, Long idbranch);
	ValueParameter getValueByParamName(Long idcompany, Long idbranch, String paramName, String type);
}
