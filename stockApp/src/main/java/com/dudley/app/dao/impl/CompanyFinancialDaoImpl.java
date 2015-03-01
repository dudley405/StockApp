package com.dudley.app.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import com.dudley.app.dao.CompanyFinancialDao;
import com.dudley.app.entities.Company;
import com.dudley.app.entities.CompanyFinancial;

@Repository
public class CompanyFinancialDaoImpl extends AbstractDaoImpl implements CompanyFinancialDao {

	@Override
	public void saveCompanyFinancial(CompanyFinancial companyFinancial) {
		persist(companyFinancial);
	}

	@Override
	public CompanyFinancial getFinancialsByCompany(Company company) {
		Criteria criteria = getSession().createCriteria(CompanyFinancial.class);
        criteria.add(Restrictions.eq("company",company));
        return (CompanyFinancial) criteria.uniqueResult();
	}

}
