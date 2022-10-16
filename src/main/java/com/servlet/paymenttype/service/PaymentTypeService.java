package com.servlet.paymenttype.service;

import java.util.List;

import com.servlet.invoicetype.entity.BodyInvoiceType;
import com.servlet.paymenttype.entity.BodyPaymentType;
import com.servlet.paymenttype.entity.PaymentTypeData;
import com.servlet.paymenttype.entity.PaymentTypeTemplate;
import com.servlet.shared.ReturnData;

public interface PaymentTypeService {
	List<PaymentTypeData> getListAll(Long idcompany,Long idbranch);
	List<PaymentTypeData> getListActive(Long idcompany,Long idbranch);
	PaymentTypeData getById(Long idcompany,Long idbranch,Long id);
	ReturnData savePaymentType(Long idcompany,Long idbranch,Long iduser,BodyPaymentType body);
	ReturnData updatePaymentType(Long idcompany,Long idbranch,Long iduser,Long id,BodyPaymentType body);
	ReturnData deletePaymentType(Long idcompany,Long idbranch,Long iduser,Long id);
	PaymentTypeTemplate getTemplate(Long idcompany,Long idbranch);
	List<PaymentTypeData> getListAllByPaymentType(Long idcompany,Long idbranch,String paymenttype);
}
