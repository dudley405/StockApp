package com.dudley.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import com.dudley.app.dao.PriceHistoryDao;
import com.dudley.app.entities.Company;
import com.dudley.app.entities.PriceHistory;


@Repository
public class PriceHistoryDaoImpl extends AbstractDaoImpl implements PriceHistoryDao {

	@Override
	public List<PriceHistory> getPriceHistoryByCompany(Company company) {
		Criteria criteria = getSession().createCriteria(PriceHistory.class);
        criteria.add(Restrictions.eq("company",company));
        return (List<PriceHistory>) criteria.list();
	}

	@Override
	public PriceHistory priceHistoryExists(LocalDate date, Company company) {
		Criteria criteria = getSession().createCriteria(PriceHistory.class);
        criteria.add(Restrictions.eq("priceDate",date));
        criteria.add(Restrictions.eq("company", company));
        return (PriceHistory) criteria.uniqueResult();
	}

	

}
