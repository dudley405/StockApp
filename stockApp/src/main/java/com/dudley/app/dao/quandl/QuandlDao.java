package com.dudley.app.dao.quandl;

import java.io.File;
import java.util.Map;

import com.jimmoores.quandl.TabularResult;

public interface QuandlDao {
	
	//void loadAllStockData(File file);
	
	TabularResult getAllStockInfoByTicker(String ticker);
	
	TabularResult getLatestStockInfoByTicker(String ticker);

}
