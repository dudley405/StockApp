package com.dudley.app.load;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dudley.app.service.CompanyService;
import com.dudley.app.service.quandl.QuandlService;

@Service
public class HistoryLoader {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired 
	DataSource dataSource;
	
	@Autowired
	QuandlService quandlService;
	
	private static Logger logger = LoggerFactory.getLogger(HistoryLoader.class);

	
	public void run() {
		
		String sql = "select stock_ticker, company_id from Company";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
		
			conn = dataSource.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				quandlService.updateAllPriceHistoryByTicker(rs.getString("stock_ticker"), rs.getInt("company_id"), conn);
			}
			
		} catch (Exception e) {
			logger.error("Error loading priceHistory data ", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				logger.error("Error closing database connection ", ex);
			}
		}
	}
}
