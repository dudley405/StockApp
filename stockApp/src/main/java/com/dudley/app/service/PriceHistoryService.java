package com.dudley.app.service;

import org.hibernate.Session;

import com.dudley.app.entities.Company;
import com.dudley.app.entities.PriceHistory;

public interface PriceHistoryService {
	
	void savePriceHistory(PriceHistory priceHistory);
	
	PriceHistory getPriceHistoryByCompany(Company company);
	
	void commit();
	
	void clear();

}
