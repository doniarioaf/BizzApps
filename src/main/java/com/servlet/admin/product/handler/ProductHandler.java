package com.servlet.admin.product.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.admin.product.entity.BodyProduct;
import com.servlet.admin.product.entity.Product;
import com.servlet.admin.product.entity.ProductDetailData;
import com.servlet.admin.product.entity.ProductListData;
import com.servlet.admin.product.mapper.GetDetailProduct;
import com.servlet.admin.product.mapper.GetProductList;
import com.servlet.admin.product.repo.ProductRepo;
import com.servlet.admin.product.service.ProductService;
import com.servlet.shared.ReturnData;

@Service
public class ProductHandler implements ProductService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ProductRepo repository;
	
	@Override
	public ReturnData saveProduct(BodyProduct product, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		
		Product table = new Product();
		table.setIdcompany(idcompany);
		table.setNama(product.getNama());
		table.setDescription(product.getDescription());
		table.setIdproducttype(product.getIdproducttype());
		table.setIsdelete(false);
		Product returntable = repository.saveAndFlush(table);
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		
		return data;
	}

	@Override
	public ReturnData updateProduct(long id, BodyProduct product, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ProductDetailData check = getProductById(id,idcompany,idbranch);
		if(check != null) {
			Product table = repository.getById(id);
			table.setNama(product.getNama());
			table.setDescription(product.getDescription());
			table.setIdproducttype(product.getIdproducttype());
			Product returntable = repository.saveAndFlush(table);
			ReturnData data = new ReturnData();
			data.setId(returntable.getId());
			return data;
		}
		return null;
	}

	@Override
	public List<ProductListData> getAllListProduct(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetProductList().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetProductList(), queryParameters);
	}

	@Override
	public ProductDetailData getProductById(long id, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailProduct().schema());
		sqlBuilder.append(" where mp.id = ? and mp.idcompany = ? and mp.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany};
		List<ProductDetailData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailProduct(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
