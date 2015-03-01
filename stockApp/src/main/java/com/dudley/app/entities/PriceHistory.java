package com.dudley.app.entities;

// Generated Feb 23, 2015 5:00:28 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

/**
 * PriceHistory generated by hbm2java
 */
@Entity
@Table(name = "PriceHistory")
public class PriceHistory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int historyId;
	private Company company;
	private LocalDate priceDate;
	private Double stockPrice;

	public PriceHistory() {
	}

	public PriceHistory(int historyId, Company company, LocalDate priceDate,
			Double stockPrice) {
		this.historyId = historyId;
		this.company = company;
		this.priceDate = priceDate;
		this.stockPrice = stockPrice;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "history_id", unique = true, nullable = false)
	public int getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "price_date", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	public LocalDate getPriceDate() {
		return this.priceDate;
	}

	public void setPriceDate(LocalDate priceDate) {
		this.priceDate = priceDate;
	}

	@Column(name = "stock_price", nullable = false, precision = 10, scale = 2)
	public Double getStockPrice() {
		return this.stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

}