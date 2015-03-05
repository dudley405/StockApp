package com.dudley.app.service;

import java.util.List;

import org.hibernate.Session;

import com.dudley.app.entities.Company;

public interface CompanyService {
	
	List<Company> findAllCompanies();
	
	void deleteCompanyByTicker(String stockTicker);
	
	Company findByTicker(String stockTicker);
	
	void saveCompany(Company company);
	
	void updateCompany(Company company);
	
	void deleteCompany(Company company);
	
	void commit();
	
	void clear();
	
	List<Company> findFirst(int number);
}
