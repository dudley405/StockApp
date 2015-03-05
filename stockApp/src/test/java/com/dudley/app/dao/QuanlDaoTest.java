package com.dudley.app.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.dudley.app.dao.quandl.QuandlDao;
import com.dudley.app.service.BaseTest;

public class QuanlDaoTest extends BaseTest {
	
	@Resource
	QuandlDao dao;

	@Test
	public void test() {
		dao.getAllStockInfoByTicker("AAPL");
		
		assertEquals("hey", "hey");
	}

}
