package com.servlet.partai.service;

import java.util.List;

import com.servlet.partai.entity.BodyPartai;
import com.servlet.partai.entity.PartaiData;
import com.servlet.shared.ReturnData;


public interface PartaiService {
	List<PartaiData> getListAll(Long idcompany,Long idbranch);
	List<PartaiData> getListActive(Long idcompany,Long idbranch);
	PartaiData getById(Long idcompany,Long idbranch,Long id);
	ReturnData savePartai(Long idcompany,Long idbranch,Long iduser,BodyPartai body);
	ReturnData updatePartai(Long idcompany,Long idbranch,Long iduser,Long id,BodyPartai body);
	ReturnData deletePartai(Long idcompany,Long idbranch,Long iduser,Long id);
}
