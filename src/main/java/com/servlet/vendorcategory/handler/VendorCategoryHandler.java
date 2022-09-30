package com.servlet.vendorcategory.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendorcategory.entity.BodyVendorCategory;
import com.servlet.vendorcategory.entity.VendorCategory;
import com.servlet.vendorcategory.entity.VendorCategoryData;
import com.servlet.vendorcategory.mapper.GetDataVendorCategory;
import com.servlet.vendorcategory.repo.VendorCategoryRepo;
import com.servlet.vendorcategory.service.VendorCategoryService;

@Service
public class VendorCategoryHandler implements VendorCategoryService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private VendorCategoryRepo repository;
	
	
	@Override
	public List<VendorCategoryData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataVendorCategory().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataVendorCategory(), queryParameters);
	}

	@Override
	public List<VendorCategoryData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataVendorCategory().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataVendorCategory(), queryParameters);
	}

	@Override
	public VendorCategoryData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataVendorCategory().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<VendorCategoryData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataVendorCategory(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ReturnData saveVendorCategory(Long idcompany, Long idbranch, Long iduser, BodyVendorCategory body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				VendorCategory table = new VendorCategory();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setCategoryname(body.getCategoryname());
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
	public ReturnData updateVendorCategory(Long idcompany, Long idbranch, Long iduser, Long id,
			BodyVendorCategory body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		VendorCategoryData value = getById(idcompany, idbranch, id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					VendorCategory table = repository.getById(id);
					table.setCategoryname(body.getCategoryname());
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
	public ReturnData deleteVendorCategory(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		VendorCategoryData value = getById(idcompany, idbranch, id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			VendorCategory table = repository.getById(id);
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

}
