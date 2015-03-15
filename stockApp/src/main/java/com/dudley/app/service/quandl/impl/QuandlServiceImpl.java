package com.dudley.app.service.quandl.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.sql.DataSource;

import org.hibernate.Transaction;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dudley.app.dao.impl.JsonDaoImpl;
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

	private static final Logger logger = LoggerFactory
			.getLogger(QuandlServiceImpl.class);
	
	
	/**
	 * Loads all of the price histories for every company found in the 
	 * database
	 */
	public void updateAllPriceHistoryByTicker(String ticker, int companyId, Connection conn) {
		
		PreparedStatement ps = null;
		String sql = "insert into dbo.PriceHistory (company_id, price_date, closing_price, open_price, high_price, "
				+ "low_price, volume) VALUES (?,?,?,?,?,?,?)";

		try {
			

			if (ticker != null) {
				TabularResult results = dao.getAllStockInfoByTicker(ticker);

				if (results != null && results.size() > 0) {
					Iterator iter = results.iterator();

					while (iter.hasNext()) {
						Row row = (Row) iter.next();
						String priceDate = null;
						double closingPrice = 0;
						double openPrice = 0;
						double highPrice = 0;
						double lowPrice = 0;
						double volume = 0;
						
						// get values from the call the quandl call
						try {
	
							 priceDate = row.getLocalDate("Date").toString();
							 closingPrice =  row.getDouble("Close");
							 openPrice = row.getDouble("Open");
							 highPrice = row.getDouble("High");
							 lowPrice = row.getDouble("Low");
							 volume = row.getDouble("Volume");
						} catch (NullPointerException e) {
							// catch exception here to account for missing values in the 
							// results returned from the quandl call
						}
						
						
						ps = conn.prepareStatement(sql);
						
						ps.setInt(1, companyId);
						
						// don't load the record if it has information missing
						if (priceDate != null && closingPrice > 0 && openPrice > 0 && highPrice > 0
								&& lowPrice > 0 && volume > 0) {
							ps.setString(2, priceDate);
							ps.setDouble(3, closingPrice);
							ps.setDouble(4, openPrice);
							ps.setDouble(5, highPrice);
							ps.setDouble(6, lowPrice);
							ps.setDouble(7, volume);
						
							ps.execute();
						}

					}
				}
			}
		} catch (Exception e) {
			logger.error("Error trying to update all price History", e);
		} 

	}
}
