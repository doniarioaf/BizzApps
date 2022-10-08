package com.servlet.invoicetype.service;

import java.util.List;

import com.servlet.invoicetype.entity.BodyInvoiceType;
import com.servlet.invoicetype.entity.InvoiceTypeData;
import com.servlet.invoicetype.entity.InvoiceTypeTemplate;
import com.servlet.shared.ReturnData;

public interface InvoiceTypeService {
	List<InvoiceTypeData> getListAll(Long idcompany,Long idbranch);
	List<InvoiceTypeData> getListActiveBankAccount(Long idcompany,Long idbranch);
	InvoiceTypeData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveInvoiceType(Long idcompany,Long idbranch,Long iduser,BodyInvoiceType body);
	ReturnData updateInvoiceType(Long idcompany,Long idbranch,Long iduser,Long id,BodyInvoiceType body);
	ReturnData deleteInvoiceType(Long idcompany,Long idbranch,Long iduser,Long id);
	InvoiceTypeTemplate getTemplate(Long idcompany,Long idbranch);
}
