package com.dudley.app.service;

import java.util.List;

import org.hibernate.Session;

import com.dudley.app.entities.Company;
import com.dudley.app.entities.PriceHistory;

public interface PriceHistoryService {
	
	void savePriceHistory(PriceHistory priceHistory);
	
	List<PriceHistory> getPriceHistoryByCompany(Company company);
	
	void commit();
	
	void clear();

}
