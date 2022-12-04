package com.servlet.pengluarankasbank.service;

import java.sql.Date;
import java.util.List;

import com.servlet.pengluarankasbank.entity.BodyPengeluaranKasBank;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankData;
import com.servlet.pengluarankasbank.entity.PengeluaranKasBankData;
import com.servlet.pengluarankasbank.entity.PengeluaranKasBankTemplate;
import com.servlet.shared.ReturnData;

public interface PengeluaranKasBankService {
	List<PengeluaranKasBankData> getListAll(Long idcompany,Long idbranch);
	List<PengeluaranKasBankData> getListActive(Long idcompany,Long idbranch);
	PengeluaranKasBankData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveData(Long idcompany,Long idbranch,Long iduser,BodyPengeluaranKasBank body);
	ReturnData updateData(Long idcompany,Long idbranch,Long iduser,Long id,BodyPengeluaranKasBank body);
	ReturnData deleteData(Long idcompany,Long idbranch,Long iduser,Long id);
	PengeluaranKasBankTemplate getTemplate(Long idcompany,Long idbranch);
	PengeluaranKasBankData getByIdWithTemplate(Long idcompany,Long idbranch,Long id);
	Double summaryAmountPengeluaranByDate(Long idcompany,Long idbranch,Date fromdate, Date todate, Long idpengeluaran);
	List<DetailPengeluaranKasBankData> getListDetailById(Long idcompany,Long idbranch,Long id);
}
