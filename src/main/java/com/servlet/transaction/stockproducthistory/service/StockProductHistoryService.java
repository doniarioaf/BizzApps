package com.servlet.transaction.stockproducthistory.service;

import com.servlet.shared.ReturnData;
import com.servlet.transaction.stockproducthistory.entity.StockProductHistory;

public interface StockProductHistoryService {
	ReturnData saveStockProductHistory(StockProductHistory product);
}
