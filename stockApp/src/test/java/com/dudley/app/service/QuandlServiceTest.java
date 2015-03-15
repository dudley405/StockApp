package com.dudley.app.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dudley.app.service.quandl.QuandlService;

public class QuandlServiceTest extends BaseTest {
	
	@Autowired
	QuandlService service;

	@Test
	public void testLoadAllHistories() {
		//service.updateAllPriceHistoryByTicker("WLL");
	}

}
