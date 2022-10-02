package com.servlet.port.service;

import java.util.List;

import com.servlet.port.entity.BodyPort;
import com.servlet.port.entity.PortData;
import com.servlet.shared.ReturnData;

public interface PortService {
	List<PortData> getListAll(Long idcompany,Long idbranch);
	List<PortData> getListActive(Long idcompany,Long idbranch);
	PortData getById(Long idcompany,Long idbranch,Long id);
	ReturnData savePort(Long idcompany,Long idbranch,Long iduser,BodyPort body);
	ReturnData updatePort(Long idcompany,Long idbranch,Long iduser,Long id,BodyPort body);
	ReturnData deleteport(Long idcompany,Long idbranch,Long iduser,Long id);
}
