package com.servlet.pengluarankasbank.service;

import java.sql.Date;
import java.util.List;

import com.servlet.employeemanggala.entity.EmployeManggalaData;
import com.servlet.pengluarankasbank.entity.BodyPengeluaranKasBank;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankData;
import com.servlet.pengluarankasbank.entity.PengeluaranHeaderAndDetail;
import com.servlet.pengluarankasbank.entity.PengeluaranKasBankData;
import com.servlet.pengluarankasbank.entity.PengeluaranKasBankTemplate;
import com.servlet.report.entity.EntityHelperKasBank;
import com.servlet.shared.ReturnData;
import com.servlet.vendor.entity.DetailVendorBankData;

public interface PengeluaranKasBankService {
	List<PengeluaranKasBankData> getListAll(Long idcompany,Long idbranch);
	List<PengeluaranKasBankData> getListActive(Long idcompany,Long idbranch);
	List<PengeluaranKasBankData> getListActiveCheckBank(Long idcompany,Long idbranch,Long iduser);
	List<PengeluaranKasBankData> getListAllJoin(Long idcompany,Long idbranch);
	PengeluaranKasBankData getById(Long idcompany,Long idbranch,Long id);
	PengeluaranKasBankData getByIdCheckBank(Long idcompany,Long idbranch,Long id, Long iduser);
	ReturnData saveData(Long idcompany,Long idbranch,Long iduser,BodyPengeluaranKasBank body);
	ReturnData updateData(Long idcompany,Long idbranch,Long iduser,Long id,BodyPengeluaranKasBank body);
	ReturnData deleteData(Long idcompany,Long idbranch,Long iduser,Long id);
	PengeluaranKasBankTemplate getTemplate(Long idcompany,Long idbranch, Long iduser);
	PengeluaranKasBankData getByIdWithTemplate(Long idcompany,Long idbranch,Long id, Long iduser);
	Double summaryAmountPengeluaranByDate(Long idcompany,Long idbranch,Date fromdate, Date todate, Long idpengeluaran, Long idbank);
	List<DetailPengeluaranKasBankData> getListDetailById(Long idcompany,Long idbranch,Long id);
	Double summaryAmountPengeluaranByIdWo(Long idcompany,Long idbranch,Date fromdate, Date todate, Long idwo,Long idbank);
	PengeluaranHeaderAndDetail getListByIdWo(Long idcompany,Long idbranch,Long idWO, boolean isReimbursement);
	Double summaryAmountPengeluaranForSummaryKegiatanTruck(Long idcompany,Long idbranch, Long idwo,Long idcustomer,Long idemployee, Long idinvoiceitem,Long idpaymentitem, Long idasset);
	List<DetailVendorBankData> getListBankVendor(Long id,Long idcompany,Long idbranch);
	List<DetailVendorBankData> getEmpAccBankById(Long idcompany,Long idbranch,Long id);
	List<EntityHelperKasBank> getDataReportKasBankPengeluaran(Long idcompany,Long idbranch,Date fromdate, Date todate,Long idbank);

	List<DetailPengeluaranKasBankData> getListDetailByIdInvoice(Long idcompany,Long idbranch,Long idinvoice);
}
