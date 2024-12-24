package com.servlet.invoice.service;

import com.servlet.invoice.entity.BodyInvoice;
import com.servlet.invoice.entity.InvoiceDataList;
import com.servlet.invoice.entity.InvoiceTemplate;
import com.servlet.invoice.entity.ParamSearchInvoice;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDataList> getList(Long idcompany, Long idbranch, ParamSearchInvoice param);
    InvoiceTemplate getTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyInvoice body);
}
