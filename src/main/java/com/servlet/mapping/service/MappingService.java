package com.servlet.mapping.service;

import java.util.List;

import com.servlet.mapping.entity.BodyMapping;
import com.servlet.mapping.entity.MappingData;
import com.servlet.shared.ReturnData;

public interface MappingService {
	ReturnData saveMapping(Long idcompany, Long idbranch, Long iduser,BodyMapping body);
	List<MappingData> getListMapping(long idcompany,long idbranch,String mappinggrup);
}
