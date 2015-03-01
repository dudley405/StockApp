package com.dudley.app.service.impl;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dudley.app.dao.PriceHistoryDao;
import com.dudley.app.entities.Company;
import com.dudley.app.entities.PriceHistory;
import com.dudley.app.service.CompanyService;
import com.dudley.app.service.PriceHistoryService;

@Service
@Transactional
public class PriceHistoryServiceImpl implements PriceHistoryService {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	PriceHistoryDao dao;

	@Override
	public void savePriceHistory(PriceHistory priceHistory, String stockTicker) {
		
		Company company = companyService.findByTicker(stockTicker);
		
		if (company != null) {
			priceHistory.setCompany(company);
			
			PriceHistory existingPriceHistory = historyExists(priceHistory.getPriceDate(), company);
			
			if (existingPriceHistory == null) {
				dao.persist(priceHistory);
			} else {
				existingPriceHistory.setPriceDate(priceHistory.getPriceDate());
				existingPriceHistory.setStockPrice(priceHistory.getStockPrice());
				dao.update(existingPriceHistory);
			}
		}
		
	}

	@Override
	public PriceHistory getPriceHistoryByCompany(Company company) {
		return (PriceHistory) dao.getPriceHistoryByCompany(company);
	}
	
	
	private PriceHistory historyExists(LocalDate date, Company company) {
		return dao.priceHistoryExists(date, company);
	}

}
