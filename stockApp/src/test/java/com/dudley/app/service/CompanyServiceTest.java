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
		Company company = service.findByTicker("WLL");
		
		assertEquals(company.getStockTicker().trim(), "WLL");
	}
	
	@Test
	public void updateCompany() {
		
		Company company = service.findByTicker("WLL");
		
		company.setCompanyDescription("what");
		service.updateCompany(company);
		
		assertEquals(company.getCompanyDescription().trim(), "what");
		
	}
	
	@Test public void saveComany() {
		Company company = new Company();
		
		company.setCompanyName("Haliburton");
		company.setCompanyDescription("Large contractor");
		company.setStockTicker("HAL2");
		
		service.saveCompany(company);
	}
}


