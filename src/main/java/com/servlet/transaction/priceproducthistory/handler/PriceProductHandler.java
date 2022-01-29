package com.servlet.transaction.priceproducthistory.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.shared.ReturnData;
import com.servlet.transaction.priceproducthistory.entity.PriceProductHistory;
import com.servlet.transaction.priceproducthistory.repo.PriceProductHistoryRepo;
import com.servlet.transaction.priceproducthistory.service.PriceProductHistoryService;

@Service
public class PriceProductHandler implements PriceProductHistoryService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PriceProductHistoryRepo repository;
	
	@Override
	public ReturnData savePriceProductHistory(PriceProductHistory product) {
		// TODO Auto-generated method stub
		PriceProductHistory returntable = repository.saveAndFlush(product);
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		
		return data;
	}

}
