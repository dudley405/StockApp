package com.dudley.app.dao;

import java.util.List;

import com.dudley.app.entities.Company;

public interface CompanyDao extends AbstractDao {
	
	void saveCompany(Company company);
	
	List<Company> findAllCompanies();
	
	void deleteCompanyByTicker(String stockTicker);
	
	Company findByTicker(String stockTicker);
	
	List<Company> findCompanyFindFirst(int number);

}
