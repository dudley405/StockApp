package com.dudley.app.service;

import java.util.List;

import com.dudley.app.entities.Company;

public interface CompanyService {
	
	List<Company> findAllCompanies();
	
	void deleteCompanyByTicker(String stockTicker);
	
	Company findByTicker(String stockTicker);
	
	void saveCompany(Company company);
	
	void updateCompany(Company company);
	
	void deleteCompany(Company company);
}
