package com.dudley.app.entities;

// Generated Feb 23, 2015 5:00:28 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CompanyFinancial generated by hbm2java
 */
@Entity
@Table(name = "CompanyFinancial")
public class CompanyFinancial implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int financialsId;
	private Company company;
	private Double earningsPerShare;

	public CompanyFinancial() {
	}

	public CompanyFinancial(int financialsId, Company company) {
		this.financialsId = financialsId;
		this.company = company;
	}

	public CompanyFinancial(int financialsId, Company company,
			Double earningsPerShare) {
		this.financialsId = financialsId;
		this.company = company;
		this.earningsPerShare = earningsPerShare;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "financials_id", unique = true, nullable = false)
	public int getFinancialsId() {
		return this.financialsId;
	}

	public void setFinancialsId(int financialsId) {
		this.financialsId = financialsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "earnings_per_share", precision = 5, scale=2)
	public Double getEarningsPerShare() {
		return this.earningsPerShare;
	}

	public void setEarningsPerShare(Double earningsPerShare) {
		this.earningsPerShare = earningsPerShare;
	}

}