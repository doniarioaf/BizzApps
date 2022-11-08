package com.servlet.penerimaankasbank.service;

import java.util.List;

import com.servlet.penerimaankasbank.entity.BodyPenerimaanKasBank;
import com.servlet.penerimaankasbank.entity.PenerimaanKasBankData;
import com.servlet.shared.ReturnData;

public interface PenerimaanKasBankService {
	List<PenerimaanKasBankData> getListAll(Long idcompany,Long idbranch);
	List<PenerimaanKasBankData> getListActive(Long idcompany,Long idbranch);
	PenerimaanKasBankData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveData(Long idcompany,Long idbranch,Long iduser,BodyPenerimaanKasBank body);
	ReturnData updateData(Long idcompany,Long idbranch,Long iduser,Long id,BodyPenerimaanKasBank body);
	ReturnData deleteData(Long idcompany,Long idbranch,Long iduser,Long id);
}
