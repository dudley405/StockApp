package com.dudley.app.service;

import com.dudley.app.entities.Company;
import com.dudley.app.entities.PriceHistory;

public interface PriceHistoryService {
	
	void savePriceHistory(PriceHistory priceHistory, String stockTicker);
	
	PriceHistory getPriceHistoryByCompany(Company company);

}
