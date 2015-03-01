package com.dudley.app.dao;

import java.util.List;

import org.joda.time.LocalDate;

import com.dudley.app.entities.Company;
import com.dudley.app.entities.PriceHistory;

public interface PriceHistoryDao extends AbstractDao {
	
	List<PriceHistory> getPriceHistoryByCompany(Company company);
	
	PriceHistory priceHistoryExists(LocalDate date, Company company);

}
