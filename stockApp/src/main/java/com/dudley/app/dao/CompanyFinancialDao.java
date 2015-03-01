package com.dudley.app.dao;

import org.joda.time.LocalDate;

import com.dudley.app.entities.Company;
import com.dudley.app.entities.CompanyFinancial;

public interface CompanyFinancialDao extends AbstractDao {
	
	void saveCompanyFinancial(CompanyFinancial companyFinancial);
	
	CompanyFinancial getFinancialsByCompany(Company company);

}
