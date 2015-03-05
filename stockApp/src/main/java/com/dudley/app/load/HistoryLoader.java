package com.dudley.app.load;

import java.util.List;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dudley.app.entities.Company;
import com.dudley.app.service.CompanyService;
import com.dudley.app.service.quandl.QuandlService;

@Service
public class HistoryLoader {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	QuandlService quandlService;

	
	public void run() {
		
		List<Company> companyList = companyService.findFirst(10);
		
		for (Company comp : companyList) {
			quandlService.updateAllPriceHistoryByTicker(comp.getStockTicker());
		}
	}
}
