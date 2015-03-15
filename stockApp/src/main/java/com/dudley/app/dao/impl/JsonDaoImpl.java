package com.dudley.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dudley.app.dao.JsonDao;
import com.dudley.app.entities.PriceHistory;
import com.dudley.app.web.controller.HomeController;

@Repository
public class JsonDaoImpl implements JsonDao {
	
	@Autowired
	DataSource datasource;
	
	private static final Logger logger = LoggerFactory.getLogger(JsonDaoImpl.class);

	@Override
	public List<PriceHistory> getPriceHistories(int companyId) {
		
		List<PriceHistory> priceHistories = new ArrayList<PriceHistory>();
		
		String sql = "select * from dbo.PriceHistory (nolock) where company_id = ? order by price_date";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, companyId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PriceHistory priceHistory = new PriceHistory();
				
				priceHistory.setClosingPrice(rs.getDouble("closing_price"));
				priceHistory.setHighPrice(rs.getDouble("high_price"));
				priceHistory.setLowPrice(rs.getDouble("low_price"));
				priceHistory.setOpenPrice(rs.getDouble("open_price"));
				priceHistory.setPriceDate(LocalDate.parse(rs.getDate("price_date").toString()));
				priceHistory.setVolume(rs.getDouble("volume"));
				
				priceHistories.add(priceHistory);
				
			}
		} catch (Exception e) {
			logger.error("Error trying to retrieve price history data", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				logger.error("Error closing database connection", ex);
			}
		}
		
		return priceHistories;
	}

}
