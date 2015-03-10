package com.dudley.app.service.quandl.impl;

import java.util.Iterator;

import org.hibernate.Transaction;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dudley.app.dao.quandl.QuandlDao;
import com.dudley.app.entities.Company;
import com.dudley.app.entities.PriceHistory;
import com.dudley.app.service.CompanyService;
import com.dudley.app.service.PriceHistoryService;
import com.dudley.app.service.quandl.QuandlService;
import com.jimmoores.quandl.Row;
import com.jimmoores.quandl.TabularResult;

@Service
public class QuandlServiceImpl implements QuandlService {

	@Autowired
	QuandlDao dao;

	@Autowired
	PriceHistoryService historyService;

	@Autowired
	CompanyService companyService;

	public void updateAllPriceHistoryByTicker(String ticker) {

		Company company = companyService.findByTicker(ticker);

		if (company != null) {
			TabularResult results = dao.getAllStockInfoByTicker(ticker);

			if (results != null && results.size() > 0) {
				Iterator iter = results.iterator();

				while (iter.hasNext()) {
					Row row = (Row) iter.next();

					PriceHistory history = new PriceHistory();
					history.setCompany(company);
					history.setPriceDate(LocalDate.parse(row.getLocalDate("Date").toString()));
					history.setClosingPrice(row.getDouble("Close"));
					history.setOpenPrice(row.getDouble("Open"));
					history.setHighPrice(row.getDouble("High"));
					history.setLowPrice(row.getDouble("Low"));
					history.setVolume(row.getDouble("Volume"));
					
					if(history.getPriceDate() != null && history.getClosingPrice() != null) {
						historyService.savePriceHistory(history);
					} 
					
					historyService.commit();
					historyService.clear();
				}

			}

		}
	}
}
