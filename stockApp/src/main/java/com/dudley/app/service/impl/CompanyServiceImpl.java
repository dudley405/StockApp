package com.dudley.app.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dudley.app.dao.CompanyDao;
import com.dudley.app.entities.Company;
import com.dudley.app.service.CompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao dao;

	public void saveCompany(Company company) {

		if (company != null) {
			
			// make sure we don't insert a duplicate record
			Company tempCompany = findByTicker(company.getStockTicker());
			
			if (tempCompany == null) {
				dao.persist(company);
			} else {
				tempCompany.setCompanyDescription(company.getCompanyDescription());
				tempCompany.setCompanyName(company.getCompanyName());
				tempCompany.setCompanyFinancials(company.getCompanyFinancials());
				tempCompany.setPriceHistories(company.getPriceHistories());
				
				dao.update(tempCompany);
			}
		}
	}

	public void updateCompany(Company company) {
		if (company != null) {
			dao.update(company);
		}
	}

	public void deleteCompany(Company company) {
		if (company != null) {
			dao.delete(company);
		}
	}

	public List<Company> findAllCompanies() {
		return dao.findAllCompanies();
	}

	public void deleteCompanyByTicker(String stockTicker) {
		if (stockTicker != null) {
			dao.deleteCompanyByTicker(stockTicker);
		}
	}
	
	public void commit() {
		dao.flush();
	}
	
	public void clear() {
		dao.clear();
	}
	
	public List<Company> findFirst(int number) {
		return dao.findCompanyFindFirst(number);
	}

	public Company findByTicker(String stockTicker) {
		return dao.findByTicker(stockTicker);
	}

}
