package com.dudley.app.dao.quandl.impl;

import org.springframework.stereotype.Repository;

import com.dudley.app.dao.quandl.QuandlDao;
import com.jimmoores.quandl.DataSetRequest;
import com.jimmoores.quandl.QuandlSession;
import com.jimmoores.quandl.TabularResult;

@Repository
public class QuandlDaoImpl implements QuandlDao {
	
	private final String API_KEY = "ffK1qtMbLxUrNstmpzv1";

	@Override
	public TabularResult getAllStockInfoByTicker(String ticker) {
		QuandlSession session = QuandlSession.create(API_KEY);
		TabularResult tabularResult = session.getDataSet(
		  DataSetRequest.Builder.of("WIKI/" + ticker).build());
		return tabularResult;
	}

	@Override
	public TabularResult getLatestStockInfoByTicker(String ticker) {
		QuandlSession session = QuandlSession.create(API_KEY);
		TabularResult tabularResult = session.getDataSet(
		  DataSetRequest.Builder.of("WIKI/" + ticker).withMaxRows(1).build());
		return tabularResult;
	}

}
