package com.servlet.product.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.product.entity.BodyProduct;
import com.servlet.product.entity.Product;
import com.servlet.product.entity.ProductData;
import com.servlet.product.mapper.GetProductAllColumn;
import com.servlet.product.mapper.GetProductList;
import com.servlet.product.repo.ProductRepo;
import com.servlet.product.service.ProductService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class ProductHandler implements ProductService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ProductRepo repository;

	@Override
	public List<ProductData> getListProduct(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetProductList().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetProductList(), queryParameters);
	}
	
	@Override
	public ProductData getById(long idcompany, long idbranch, long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetProductAllColumn().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<ProductData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetProductList(), queryParameters);
		if(list != null && list.size() > 0) {
			ProductData val = list.get(0);
			return val;
		}
		
		return null;
	}

	@Override
	public ReturnData saveProduct(BodyProduct body, long idcompany, long idbranch,long iduser) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		if(validations.size() == 0) {
			try {
			Timestamp ts = new Timestamp(new Date().getTime());
			
			Product table = new Product();
			table.setIdcompany(idcompany);
			table.setIdbranch(idbranch);
			table.setCode(body.getCode());
			table.setNama(body.getNama());
			table.setHargabeli(body.getHargabeli());
			table.setHargajual(body.getHargajual());
			table.setConv1to4(body.getConv1to4());
			table.setConv2to4(body.getConv2to4());
			table.setConv3to4(body.getConv3to4());
			table.setConv4to4(body.getConv4to4());
			table.setIsdelete(false);
			table.setCreateddate(ts);
			table.setCreatedby(iduser+"");
			table.setBarcode(body.getBarcode());
			if(body.getImage() != null) {
				byte[] fileencode = Base64.encodeBase64(body.getImage().getBytes());
		        String result = new String(fileencode);
		        table.setImage(result);
			}
			}catch (Exception e) {
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
	public ReturnData updateProduct(long id, BodyProduct body, long idcompany, long idbranch,long iduser) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		ProductData value = getById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					Product table = repository.getById(id);
					table.setCode(body.getCode());
					table.setNama(body.getNama());
					table.setHargabeli(body.getHargabeli());
					table.setHargajual(body.getHargajual());
					table.setConv1to4(body.getConv1to4());
					table.setConv2to4(body.getConv2to4());
					table.setConv3to4(body.getConv3to4());
					table.setConv4to4(body.getConv4to4());
					
					table.setUpdatedate(ts);
					table.setUpdateby(iduser+"");
					
					table.setBarcode(body.getBarcode());
					if(body.getImage() != null) {
						byte[] fileencode = Base64.encodeBase64(body.getImage().getBytes());
				        String result = new String(fileencode);
				        table.setImage(result);
					}
					
					idsave = repository.saveAndFlush(table).getId();
				}catch (Exception e) {
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
	public ReturnData deleteProduct(long id,long idcompany,long idbranch,long iduser) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		ProductData value = getById(idcompany,idbranch,id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			Product table = repository.getById(id);
			table.setIsdelete(true);
			
			table.setDeletedate(ts);
			table.setDeleteby(iduser+"");
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

}
