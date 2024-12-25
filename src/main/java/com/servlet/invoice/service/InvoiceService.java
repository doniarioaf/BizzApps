package com.servlet.invoice.service;

import com.servlet.invoice.entity.*;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDataList> getList(Long idcompany, Long idbranch, ParamSearchInvoice param);
    InvoiceTemplate getTemplate(Long idcompany, Long idbranch);
    InvoiceDataDetail getDetail(Long id, Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyInvoice body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyInvoice body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
    PrintInvoice getPrintDataByID(Long id, Long idcompany, Long idbranch);
}
