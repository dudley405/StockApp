package com.dudley.app.service.impl;

import java.util.List;

import org.hibernate.Session;
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
	PriceHistoryDao dao;

	@Override
	public void savePriceHistory(PriceHistory priceHistory) {

		PriceHistory existingPriceHistory = historyExists(
				priceHistory.getPriceDate(), priceHistory.getCompany());

		if (existingPriceHistory == null) {
			dao.persist(priceHistory);
		} else {
			existingPriceHistory.setPriceDate(priceHistory.getPriceDate());
			existingPriceHistory.setClosingPrice(priceHistory.getClosingPrice());
			existingPriceHistory.setOpenPrice(priceHistory.getOpenPrice());
			existingPriceHistory.setLowPrice(priceHistory.getLowPrice());
			existingPriceHistory.setHighPrice(priceHistory.getHighPrice());
			existingPriceHistory.setVolume(priceHistory.getVolume());
			dao.update(existingPriceHistory);
		}
	}

	@Override
	public List<PriceHistory> getPriceHistoryByCompany(Company company) {
		return  dao.getPriceHistoryByCompany(company);
	}
	
	public void commit() {
		dao.flush();
	}
	
	public void clear() {
		dao.clear();
	}

	private PriceHistory historyExists(LocalDate date, Company company) {
		return dao.priceHistoryExists(date, company);
	}

}
