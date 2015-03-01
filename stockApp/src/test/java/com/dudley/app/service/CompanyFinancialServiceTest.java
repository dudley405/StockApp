package com.dudley.app.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dudley.app.entities.CompanyFinancial;

public class CompanyFinancialServiceTest extends BaseTest {

	@Autowired
	CompanyFinancialService financialService;
	
	@Test
	public void saveFinancial() {
		CompanyFinancial companyFinancial = new CompanyFinancial();
		
		companyFinancial.setEarningsPerShare(new Double(50.23));
		
		financialService.saveFinancial(companyFinancial, "WLL");
		
		assertEquals(companyFinancial.getEarningsPerShare(), new Double(50.23));
	}
	

}
