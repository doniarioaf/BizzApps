package com.servlet.warehouse.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.service.CityService;
import com.servlet.address.service.ProvinceService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.warehouse.entity.BodyWarehouse;
import com.servlet.warehouse.entity.Warehouse;
import com.servlet.warehouse.entity.WarehouseData;
import com.servlet.warehouse.entity.WarehouseTemplate;
import com.servlet.warehouse.mapper.GetWarehouseData;
import com.servlet.warehouse.mapper.GetWarehouseListData;
import com.servlet.warehouse.repo.WarehouseRepo;
import com.servlet.warehouse.service.WarehouseService;


@Service
public class WarehouseHandler implements WarehouseService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private WarehouseRepo repository;
	@Autowired
	private CityService cityService;
	@Autowired
	private ProvinceService provinceService;
	
	@Override
	public List<WarehouseData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWarehouseListData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWarehouseListData(), queryParameters);
	}

	@Override
	public List<WarehouseData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWarehouseListData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWarehouseListData(), queryParameters);
	}

	@Override
	public List<WarehouseData> getListSearchWarehouse(Long idcompany, Long idbranch, String name) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWarehouseListData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		sqlBuilder.append(" and lower(data.nama) like '%"+name.toLowerCase()+"%' ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetWarehouseListData(), queryParameters);
	}

	@Override
	public WarehouseData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWarehouseData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<WarehouseData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetWarehouseData(), queryParameters);
		if(list != null && list.size() > 0) {
			WarehouseData val = list.get(0);
			val.setTemplate(setTemplate(idcompany, idbranch));
			return val;
		}
		return null;
	}

	@Override
	public WarehouseData getByIdCustomer(Long idcompany, Long idbranch, Long idcustomer) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWarehouseListData().schema());
		sqlBuilder.append(" where data.idcustomer = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcustomer,idcompany,idbranch};
		List<WarehouseData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetWarehouseListData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ReturnData saveWarehouse(Long idcompany, Long idbranch, Long iduser, BodyWarehouse body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				Warehouse table = new Warehouse();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setProvince(body.getProvince());
				table.setCity(body.getCity());
				table.setKecamatan(body.getKecamatan());
				table.setNama(body.getNama());
				table.setAlamat(body.getAlamat());
				table.setAncerancer(body.getAncerancer());
				table.setContactnumber(body.getContactnumber());
				table.setContacthp(body.getContacthp());
				table.setNote(body.getNote());
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				
				idsave = repository.saveAndFlush(table).getId();
			}catch (Exception e) {
				// TODO: handle exception
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
				validations.add(msg);
				e.printStackTrace();
			}
			
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData updateWarehouse(Long idcompany, Long idbranch, Long iduser, Long id, BodyWarehouse body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		WarehouseData value = checkById(idcompany, idbranch, id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					Warehouse table = repository.getById(id);
					table.setProvince(body.getProvince());
					table.setCity(body.getCity());
					table.setKecamatan(body.getKecamatan());
					table.setNama(body.getNama());
					table.setAlamat(body.getAlamat());
					table.setAncerancer(body.getAncerancer());
					table.setContactnumber(body.getContactnumber());
					table.setContacthp(body.getContacthp());
					table.setNote(body.getNote());
					table.setIsactive(body.isIsactive());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
				}catch (Exception e) {
					// TODO: handle exception
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
					validations.add(msg);
					e.printStackTrace();
				}
			}
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData deleteWarehouse(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		
		WarehouseData value = checkById(idcompany, idbranch, id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			Warehouse table = repository.getById(id);
			table.setIsdelete(true);
			table.setDeleteby(iduser.toString());
			table.setDeletedate(ts);
			idsave = repository.saveAndFlush(table).getId();
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}
	
	private WarehouseData checkById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetWarehouseListData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<WarehouseData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetWarehouseListData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WarehouseTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return setTemplate(idcompany,idbranch);
	}
	
	private WarehouseTemplate setTemplate(long idcompany, long idbranch) {
		WarehouseTemplate data = new WarehouseTemplate();
		data.setCityOptions(cityService.getListCity());
		data.setProvinceOptions(provinceService.getListProvince());
		return data;
	}

}
