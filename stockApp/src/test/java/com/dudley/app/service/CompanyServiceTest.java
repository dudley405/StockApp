package com.dudley.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dudley.app.dao.CompanyDao;
import com.dudley.app.entities.Company;
import com.dudley.app.entities.CompanyFinancial;


public class CompanyServiceTest extends BaseTest {
	
	@Autowired
	CompanyService service;
	
	@Resource 
	CompanyDao dao;
	
	@Test
	public void testRetrieveByTicker() {
		Company company = service.findByTicker("ACT");
		
		assertEquals(company.getStockTicker().trim(), "ACT");
	}
	
}


