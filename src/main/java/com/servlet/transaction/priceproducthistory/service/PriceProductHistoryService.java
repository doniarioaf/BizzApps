package com.servlet.transaction.priceproducthistory.service;

import com.servlet.shared.ReturnData;
import com.servlet.transaction.priceproducthistory.entity.PriceProductHistory;

public interface PriceProductHistoryService {
	ReturnData savePriceProductHistory(PriceProductHistory product);
}
