package com.servlet.workorder.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.servlet.report.entity.ParamReportManggala;
import com.servlet.shared.ReturnData;
import com.servlet.workorder.entity.BodySearch;
import com.servlet.workorder.entity.BodyWorkOrder;
import com.servlet.workorder.entity.DetailWorkOrderData;
import com.servlet.workorder.entity.ListDocumentWorkOrderData;
import com.servlet.workorder.entity.ParamDropDownWO;
import com.servlet.workorder.entity.ParamWoReport;
import com.servlet.workorder.entity.WorkOrderData;
import com.servlet.workorder.entity.WorkOrderDropDownData;
import com.servlet.workorder.entity.WorkOrderTemplate;

public interface WorkOrderService {
	List<WorkOrderData> getListAll(Long idcompany,Long idbranch);
	List<WorkOrderData> getListActive(Long idcompany,Long idbranch);
	WorkOrderData getById(Long idcompany,Long idbranch,Long id);
	WorkOrderData getByIdForEdit(Long idcompany,Long idbranch,Long id);
	ReturnData saveWorkOrder(Long idcompany,Long idbranch,Long iduser,BodyWorkOrder body);
	ReturnData updateWorkOrder(Long idcompany,Long idbranch,Long iduser,Long id,BodyWorkOrder body);
	ReturnData deleteWorkOrder(Long idcompany,Long idbranch,Long iduser,Long id);
	WorkOrderTemplate getTemplate(Long idcompany,Long idbranch);
	List<WorkOrderDropDownData> getListDropDown(Long idcompany,Long idbranch);
	List<DetailWorkOrderData> getListContainerByIdWorkOrder(Long idcompany,Long idbranch,Long idworkorder,String nocaontainer);
	ReturnData uploadDocumentWorkOrder(Long idcompany,Long idbranch,Long iduser,Long idworkorder,MultipartFile file);
	ReturnData deleteDocumentWorkOrder(Long idcompany,Long idbranch,Long iduser,Long id);
	ListDocumentWorkOrderData getDocumentWorkOrder(Long idcompany,Long idbranch,Long id);
	List<WorkOrderData> getListDataWoForReport(ParamWoReport param);
	List<DetailWorkOrderData> getListContainerByIdWorkOrderForReport(Long idcompany,Long idbranch,Long idworkorder);
	List<WorkOrderDropDownData> getListWOByStatus(Long idcompany,Long idbranch,String status,Object param);
	WorkOrderData getByIdNotJoin(Long idcompany,Long idbranch,Long id);
	HashMap<String, Object> checkWO(Long idcompany,Long idbranch,Long id);
	List<WorkOrderData> getListSearchWO(Long idcompany,Long idbranch,BodySearch body);
	ReturnData changeStatusWO(Long idcompany,Long idbranch,Long id,String Status);
	List<DetailWorkOrderData> getListContainerByIdWorkOrderForReportStatusInvoice(Long idcompany,Long idbranch,Long idworkorder);
	List<WorkOrderData> getListDataWoForReportLabaRugi(Long idcompany,Long idbranch,ParamReportManggala param);
	List<WorkOrderDropDownData> getListDropDownByParam(Long idcompany,Long idbranch,ParamDropDownWO param);
}
