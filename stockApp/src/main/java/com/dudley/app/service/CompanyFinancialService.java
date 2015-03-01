package com.dudley.app.service;

import com.dudley.app.entities.Company;
import com.dudley.app.entities.CompanyFinancial;

public interface CompanyFinancialService {
	
	void saveFinancial(CompanyFinancial companyFinancial, String stockTicker);
	
	CompanyFinancial getFinancialsByCompany(Company company);

}
