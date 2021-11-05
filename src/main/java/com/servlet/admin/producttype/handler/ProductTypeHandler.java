package com.servlet.admin.producttype.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.admin.producttype.entity.BodyProductType;
import com.servlet.admin.producttype.entity.ProductType;
import com.servlet.admin.producttype.entity.ProductTypeData;
import com.servlet.admin.producttype.mapper.GetProductType;
import com.servlet.admin.producttype.repo.ProductTypeRepo;
import com.servlet.admin.producttype.service.ProductTypeService;
import com.servlet.shared.ReturnData;

@Service
public class ProductTypeHandler implements ProductTypeService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ProductTypeRepo repository;
	
	@Override
	public ReturnData saveProductType(BodyProductType producttype, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ProductType table = new ProductType();
		table.setNama(producttype.getNama());
		table.setDescription(producttype.getDescription());
		table.setIsdelete(false);
		table.setIdcompany(idcompany);
		table.setIdbranch(idbranch);
		ProductType returntable = repository.saveAndFlush(table);
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		
		return data;
	}

	@Override
	public ReturnData updateProductType(long id, BodyProductType producttype, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ProductTypeData productTypeData = getProductTypeById(id,idcompany,idbranch);
		if(productTypeData != null) {
			ProductType table = repository.getById(id);
			table.setNama(producttype.getNama());
			table.setDescription(producttype.getDescription());
			ProductType returntable = repository.saveAndFlush(table);
			ReturnData data = new ReturnData();
			data.setId(returntable.getId());
			
			return data;
		}
		return null;
	}

	@Override
	public List<ProductTypeData> getAllListProductType(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetProductType().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] { idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetProductType(), queryParameters);
	}

	@Override
	public ProductTypeData getProductTypeById(long id, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetProductType().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] { id,idcompany,idbranch};
		List<ProductTypeData> listproductTypeData = this.jdbcTemplate.query(sqlBuilder.toString(), new GetProductType(), queryParameters);
		if(listproductTypeData != null && listproductTypeData.size() > 0) {
			return listproductTypeData.get(0);
		}
		return null;
	}

}
