package com.servlet.invoice.service;

import java.util.List;

import com.servlet.invoice.entity.BodyInvoice;
import com.servlet.invoice.entity.BodySearch;
import com.servlet.invoice.entity.InvoiceData;
import com.servlet.invoice.entity.InvoiceTemplate;
import com.servlet.invoice.entity.PrintInvoiceData;
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
	List<InvoiceData> getListInvoiceByIdWo(Long idcompany,Long idbranch,Long idwo);
}
