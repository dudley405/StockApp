package com.dudley.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dudley.app.dao.CompanyDao;
import com.dudley.app.entities.Company;

@Repository
public class CompanyDaoImpl extends AbstractDaoImpl implements CompanyDao {
	
	public void saveCompany(Company company) {
		persist(company);	
	}

	public List<Company> findAllCompanies() {
		Criteria criteria = getSession().createCriteria(Company.class);
		return (List<Company>) criteria.list();
	}

	@Override
	public void deleteCompanyByTicker(String stockTicker) {
		Query query = getSession().createSQLQuery("delete from Company where stock_ticker = :stockTicker");
		query.executeUpdate();
	}

	@Override
	public Company findByTicker(String stockTicker) {
		Criteria criteria = getSession().createCriteria(Company.class);
        criteria.add(Restrictions.eq("stockTicker",stockTicker));
        return (Company) criteria.uniqueResult();
	}

	@Override
	public List<Company> findCompanyFindFirst(int number) {
		Criteria criteria = getSession().createCriteria(Company.class);
		criteria.setMaxResults(number);
		return (List<Company>) criteria.list();
	}

}
