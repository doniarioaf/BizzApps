package com.servlet.transaction.stockproducthistory.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.shared.ReturnData;
import com.servlet.transaction.stockproducthistory.entity.StockProductHistory;
import com.servlet.transaction.stockproducthistory.repo.StockProductHistoryRepo;
import com.servlet.transaction.stockproducthistory.service.StockProductHistoryService;

@Service
public class StockProductHistoryHandler implements StockProductHistoryService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private StockProductHistoryRepo repository;
	
	@Override
	public ReturnData saveStockProductHistory(StockProductHistory product) {
		// TODO Auto-generated method stub
		StockProductHistory returntable = repository.saveAndFlush(product);
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		
		return data;
	}

}
