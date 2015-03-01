package com.dudley.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dudley.app.dao.CompanyFinancialDao;
import com.dudley.app.entities.Company;
import com.dudley.app.entities.CompanyFinancial;
import com.dudley.app.service.CompanyFinancialService;
import com.dudley.app.service.CompanyService;

@Service
@Transactional
public class CompanyFinancialServiceImpl implements CompanyFinancialService {

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyFinancialDao dao;

	@Override
	public void saveFinancial(CompanyFinancial companyFinancial,
			String stockTicker) {

		Company company = companyService.findByTicker(stockTicker);

		if (company != null) {
			companyFinancial.setCompany(company);

			CompanyFinancial existingFinancial = getFinancialsByCompany(company);

			if (existingFinancial == null) {
				dao.persist(companyFinancial);
			} else {
				existingFinancial.setEarningsPerShare(companyFinancial
						.getEarningsPerShare());
				dao.update(existingFinancial);
			}
		}

	}

	@Override
	public CompanyFinancial getFinancialsByCompany(Company company) {
		CompanyFinancial financial = dao.getFinancialsByCompany(company);
		if (financial == null) {
			return null;
		} else {
			return financial;
		}
	}

}
