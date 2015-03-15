package com.dudley.app.dao;

import java.util.List;

import com.dudley.app.entities.PriceHistory;

public interface JsonDao {
	
	List<PriceHistory> getPriceHistories(int companyId);

}
