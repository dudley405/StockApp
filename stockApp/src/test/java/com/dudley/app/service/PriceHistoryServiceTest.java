package com.dudley.app.service;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dudley.app.entities.PriceHistory;

public class PriceHistoryServiceTest extends BaseTest {

	@Autowired
	PriceHistoryService historyService;
	
	@Test
	public void savePriceHistory() {
		PriceHistory priceHistory = new PriceHistory();
		
		LocalDate date = LocalDate.now();
		
		priceHistory.setPriceDate(date);
		priceHistory.setClosingPrice(new Double(63.45));
		
		historyService.savePriceHistory(priceHistory);
		
		assertEquals(priceHistory.getClosingPrice(), new Double(63.45));
	}

}
