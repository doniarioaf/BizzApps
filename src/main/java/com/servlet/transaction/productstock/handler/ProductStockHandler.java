package com.servlet.transaction.productstock.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.transaction.productstock.entity.ProductStock;
import com.servlet.transaction.productstock.entity.ProductStockData;
import com.servlet.transaction.productstock.entity.ProductStockPK;
import com.servlet.transaction.productstock.repo.ProductStockRepo;
import com.servlet.transaction.productstock.service.ProductStockService;
import com.servlet.transaction.stockproducthistory.entity.StockProductHistory;
import com.servlet.transaction.stockproducthistory.service.StockProductHistoryService;

@Service
public class ProductStockHandler implements ProductStockService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ProductStockRepo repository;
	@Autowired
	private StockProductHistoryService stockProductHistoryService;
	
	@Override
	public ProductStockData getProductStock(long idproduct, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ProductStockPK pk = new ProductStockPK();
		pk.setIdcompany(idcompany);
		pk.setIdproduct(idproduct);
		Optional<ProductStock> value = repository.findById(pk);
		ProductStockData data = new ProductStockData();
		data.setIdcompany(idcompany);
		data.setIdproduct(idproduct);
		if(value.isPresent()) {
			ProductStock productstock = value.get();
			data.setStock(productstock.getStock());
		}else {
			data.setStock(0);
		}
		return data;
	}

	@Override
	public ReturnData addStock(long idproduct, long stock, long idcompany, long idbranch,long userid) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idreturn = 0;
		ProductStockPK pk = new ProductStockPK();
		pk.setIdcompany(idcompany);
		pk.setIdproduct(idproduct);
		Optional<ProductStock> value = repository.findById(pk);
		if(value.isPresent()) {
			ProductStock table = repository.getById(pk);
			long totalstock = table.getStock() + stock;
			table.setStock(totalstock);
			
			ProductStock returntable = repository.saveAndFlush(table);
			idreturn = returntable.getProductStockPK().getIdproduct();
		}else {
			ProductStock table = new ProductStock();
			table.setProductStockPK(pk);
			table.setStock(stock);
			ProductStock returntable = repository.saveAndFlush(table);
			idreturn = returntable.getProductStockPK().getIdproduct();
		}
		StockProductHistory stockhistory = setStockProductHistory(idcompany,idproduct,stock,"ADD",userid,"");
		stockProductHistoryService.saveStockProductHistory(stockhistory);
		
		ReturnData data = new ReturnData();
		data.setId(idreturn);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData substractStock(long idproduct, long stock, long idcompany, long idbranch,long userid) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idreturn = idproduct;
		ProductStockPK pk = new ProductStockPK();
		pk.setIdcompany(idcompany);
		pk.setIdproduct(idproduct);
		Optional<ProductStock> value = repository.findById(pk);
		if(value.isPresent()) {
			ProductStock table = repository.getById(pk);
			if(stock > table.getStock()) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.STOCK_NOT_ENOUGH,"Stock Tidak Cukup");
				validations.add(msg);
			}else {
				long totalstock = table.getStock() - stock;
				table.setStock(totalstock);
				ProductStock returntable = repository.saveAndFlush(table);
				idreturn = returntable.getProductStockPK().getIdproduct();
			}
		}else {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.STOCK_NOT_ENOUGH,"Stock Tidak Cukup");
			validations.add(msg);
		}
		
		StockProductHistory stockhistory = setStockProductHistory(idcompany,idproduct,stock,"SUBSTRACT",userid,"");
		stockProductHistoryService.saveStockProductHistory(stockhistory);
		
		ReturnData data = new ReturnData();
		data.setId(idreturn);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}
	
	private StockProductHistory setStockProductHistory(long idcompany,long idproduct,long stock,String type,long userid,String reason) {
		Timestamp ts = new Timestamp(new Date().getTime());
		StockProductHistory stockhistory = new StockProductHistory();
		stockhistory.setIdcompany(idcompany);
		stockhistory.setIdproduct(idproduct);
		stockhistory.setStock(stock);
		stockhistory.setType(type);
		stockhistory.setTanggal(ts);
		stockhistory.setUserid(userid);
		stockhistory.setReason(reason);
		return stockhistory;
	}

	@Override
	public ReturnData rejectStock(long idproduct, long stock, long idcompany, long idbranch, long userid,String reason) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idreturn = idproduct;
		ProductStockPK pk = new ProductStockPK();
		pk.setIdcompany(idcompany);
		pk.setIdproduct(idproduct);
		Optional<ProductStock> value = repository.findById(pk);
		if(value.isPresent()) {
			ProductStock table = repository.getById(pk);
			if(stock > table.getStock()) {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.STOCK_NOT_ENOUGH,"Stock Tidak Cukup");
				validations.add(msg);
			}else {
				long totalstock = table.getStock() - stock;
				table.setStock(totalstock);
				ProductStock returntable = repository.saveAndFlush(table);
				idreturn = returntable.getProductStockPK().getIdproduct();
			}
		}else {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.STOCK_NOT_ENOUGH,"Stock Tidak Cukup");
			validations.add(msg);
		}
		
		StockProductHistory stockhistory = setStockProductHistory(idcompany,idproduct,stock,"REJECT",userid,reason);
		stockProductHistoryService.saveStockProductHistory(stockhistory);
		
		ReturnData data = new ReturnData();
		data.setId(idreturn);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

}
