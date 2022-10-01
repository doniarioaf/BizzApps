package com.servlet.workordertype.service;

import java.util.List;

import com.servlet.shared.ReturnData;
import com.servlet.workordertype.entity.BodyWorkOrderType;
import com.servlet.workordertype.entity.WorkOrderTypeData;

public interface WorkOrderTypeService {
	List<WorkOrderTypeData> getListAll(Long idcompany,Long idbranch);
	List<WorkOrderTypeData> getListActive(Long idcompany,Long idbranch);
	WorkOrderTypeData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveWorkOrderType(Long idcompany,Long idbranch,Long iduser,BodyWorkOrderType body);
	ReturnData updateWorkOrderType(Long idcompany,Long idbranch,Long iduser,Long id,BodyWorkOrderType body);
	ReturnData deleteWorkOrderType(Long idcompany,Long idbranch,Long iduser,Long id);
}
