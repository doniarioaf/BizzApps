package com.servlet.invoice.service;

import java.util.List;

import com.servlet.invoice.entity.*;
import com.servlet.shared.ReturnData;

public interface InvoiceService {
	List<InvoiceData> getListAll(Long idcompany,Long idbranch);
	List<InvoiceData> getListActive(Long idcompany,Long idbranch);
	InvoiceData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveInvoice(Long idcompany,Long idbranch,Long iduser,BodyInvoice body);
	ReturnData updateInvoice(Long idcompany,Long idbranch,Long iduser,Long id,BodyInvoice body);
	ReturnData deleteInvoice(Long idcompany,Long idbranch,Long iduser,Long id);
	InvoiceTemplate getTemplate(Long idcompany,Long idbranch);
	InvoiceData getByIdWithTemplate(Long idcompany,Long idbranch,Long id);
	List<InvoiceData> getListSearchInvoice(Long idcompany,Long idbranch,BodySearch body);
	PrintInvoiceData printInvoice(Long idcompany,Long idbranch,Long id);
	List<InvoiceData> getListInvoiceByIdWo(Long idcompany,Long idbranch,Long idwo,boolean docDPtermasuk);
	List<InvoiceData> checkInvoiceByIdWo(Long idcompany,Long idbranch,Long idwo);
	List<InvoiceDPData> getListInvoiceDPByIdWo(Long idcompany,Long idbranch,Long idwo);

	List<InvoiceData> getInvoiceYangBelumLunasByWo(Long idcompany,Long idbranch, Long idwo);
	List<InvoiceDataReportLabaRugi> getListInvoiceByIdWoReportLabaRugi(Long idcompany, Long idbranch, Long idwo, boolean docDPtermasuk);
	ReturnData saveInvoiceV2(Long idcompany,Long idbranch,Long iduser,BodyInvoiceV2 body);
	ReturnData updateInvoiceV2(Long idcompany,Long idbranch,Long iduser,Long id,BodyInvoiceV2 body);
	InvoiceData getByIdV2(Long idcompany,Long idbranch,Long id);
}
