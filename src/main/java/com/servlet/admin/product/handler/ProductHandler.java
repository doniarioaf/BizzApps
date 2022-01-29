package com.servlet.admin.product.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.admin.product.entity.BodyProduct;
import com.servlet.admin.product.entity.Product;
import com.servlet.admin.product.entity.ProductData;
import com.servlet.admin.product.entity.ProductDetailData;
import com.servlet.admin.product.entity.ProductListData;
import com.servlet.admin.product.mapper.GetDetailProduct;
import com.servlet.admin.product.mapper.GetProductData;
import com.servlet.admin.product.mapper.GetProductList;
import com.servlet.admin.product.repo.ProductRepo;
import com.servlet.admin.product.service.ProductService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.transaction.priceproducthistory.entity.PriceProductHistory;
import com.servlet.transaction.priceproducthistory.service.PriceProductHistoryService;

@Service
public class ProductHandler implements ProductService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ProductRepo repository;
	@Autowired
	private PriceProductHistoryService priceProductHistoryService;
	
	@Override
	public ReturnData saveProduct(BodyProduct product, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		long idreturn = 0;
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		ProductData searchcode = getProductByProductCode(product.getProductcode(),idcompany,idbranch);
		if(searchcode != null) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.PRODUCTCODE_IS_EXIST,"Product Code Sudah Terpakai");
			validations.add(msg);
		}else {
			Product table = new Product();
			table.setIdcompany(idcompany);
			table.setNama(product.getNama());
			table.setDescription(product.getDescription());
			table.setIdproducttype(product.getIdproducttype());
			table.setIsdelete(false);
			
			table.setProductcode(product.getProductcode());
			table.setShortname(product.getShortname());
			table.setUom1(product.getUom1());
			table.setUom2(product.getUom2());
			table.setUom3(product.getUom3());
			table.setUom4(product.getUom4());
			table.setPricebuy(product.getPricebuy());
			table.setPricesell(product.getPricesell());
			table.setConversion1to4(product.getConversion1to4());
			table.setConversion2to4(product.getConversion2to4());
			table.setConversion3to4(product.getConversion3to4());
			Product returntable = repository.saveAndFlush(table);
			idreturn = returntable.getId();
		}
		
		ReturnData data = new ReturnData();
		data.setId(idreturn);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData updateProduct(long id, BodyProduct product, long idcompany, long idbranch,long userid) {
		// TODO Auto-generated method stub
		ProductDetailData check = getProductById(id,idcompany,idbranch);
		long idreturn = 0;
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		ProductData searchcode = getProductByProductCode(product.getProductcode(),idcompany,idbranch);
		if(searchcode != null) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.PRODUCTCODE_IS_EXIST,"Product Code Sudah Terpakai");
			validations.add(msg);
		}else {
			if(check != null) {
				Product table = repository.getById(id);
				
				table.setNama(product.getNama());
				table.setDescription(product.getDescription());
				table.setIdproducttype(product.getIdproducttype());
				
				table.setProductcode(product.getProductcode());
				table.setShortname(product.getShortname());
				table.setUom1(product.getUom1());
				table.setUom2(product.getUom2());
				table.setUom3(product.getUom3());
				table.setUom4(product.getUom4());
				if(table.getPricebuy() != product.getPricebuy() || table.getPricesell() != product.getPricesell()) {
					priceProductHistoryService.savePriceProductHistory(setPriceProductHistory(table,userid));
				}
				table.setPricebuy(product.getPricebuy());
				table.setPricesell(product.getPricesell());
				table.setConversion1to4(product.getConversion1to4());
				table.setConversion2to4(product.getConversion2to4());
				table.setConversion3to4(product.getConversion3to4());
				
				Product returntable = repository.saveAndFlush(table);
				idreturn = returntable.getId();
			}else {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.PRODUCT_NOT_EXIST,"Product Tidak Ditemukan");
				validations.add(msg);
			}
		}
		ReturnData data = new ReturnData();
		data.setId(idreturn);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
//		return null;
	}
	
	private PriceProductHistory setPriceProductHistory(Product product,long userid) {
		PriceProductHistory priceProductHistory = new PriceProductHistory();
		Timestamp ts = new Timestamp(new Date().getTime());
		priceProductHistory.setIdcompany(product.getIdcompany());
		priceProductHistory.setIdproduct(product.getId());
		priceProductHistory.setTanggal(ts);
		priceProductHistory.setPricebuy(product.getPricebuy());
		priceProductHistory.setPricesell(product.getPricesell());
		priceProductHistory.setUserid(userid);
		return priceProductHistory;
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

	@Override
	public ProductData getProductByProductCode(String productcode, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetProductData().schema());
		sqlBuilder.append(" where mp.idcompany = ? and mp.productcode = ? and mp.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,productcode};
		List<ProductData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetProductData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
