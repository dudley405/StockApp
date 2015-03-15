package com.dudley.app.service.quandl;

import java.sql.Connection;

public interface QuandlService {

	void updateAllPriceHistoryByTicker(String ticker, int companyId, Connection conn);
}
