package com.servlet.warehouse.service;

import java.util.List;

import com.servlet.shared.ReturnData;
import com.servlet.warehouse.entity.BodyWarehouse;
import com.servlet.warehouse.entity.WarehouseData;
import com.servlet.warehouse.entity.WarehouseTemplate;

public interface WarehouseService {
	List<WarehouseData> getListAll(Long idcompany,Long idbranch);
	List<WarehouseData> getListActive(Long idcompany,Long idbranch);
	List<WarehouseData> getListSearchWarehouse(Long idcompany,Long idbranch,String name);
	WarehouseData getById(Long idcompany,Long idbranch,Long id);
	WarehouseData getByIdCustomer(Long idcompany,Long idbranch,Long idcustomer);
	ReturnData saveWarehouse(Long idcompany,Long idbranch,Long iduser,BodyWarehouse body);
	ReturnData updateWarehouse(Long idcompany,Long idbranch,Long iduser,Long id,BodyWarehouse body);
	ReturnData deleteWarehouse(Long idcompany,Long idbranch,Long iduser,Long id);
	WarehouseTemplate getTemplate(Long idcompany,Long idbranch);
}
