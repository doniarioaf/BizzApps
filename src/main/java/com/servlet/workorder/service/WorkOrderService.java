package com.servlet.workorder.service;

import java.util.List;

import com.servlet.shared.ReturnData;
import com.servlet.warehouse.entity.BodyWarehouse;
import com.servlet.warehouse.entity.WarehouseData;
import com.servlet.warehouse.entity.WarehouseTemplate;
import com.servlet.workorder.entity.BodyWorkOrder;
import com.servlet.workorder.entity.WorkOrderData;
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
}
