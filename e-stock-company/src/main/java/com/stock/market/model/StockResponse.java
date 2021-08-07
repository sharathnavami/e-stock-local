package com.stock.market.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class StockResponse {
	
	public StockResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StockResponse(String companyCode, BigDecimal stockPrice, LocalDate stockDate, LocalTime stocktime,
			BigDecimal maxStock, BigDecimal minStock, BigDecimal avgStock) {
		super();
		this.companyCode = companyCode;
		this.stockPrice = stockPrice;
		this.stockDate = stockDate;
		this.stocktime = stocktime;
		this.maxStock = maxStock;
		this.minStock = minStock;
		this.avgStock = avgStock;
	}
	String companyCode;
	BigDecimal stockPrice;
	LocalDate stockDate;
	LocalTime stocktime;
	BigDecimal maxStock;
	BigDecimal minStock;
	BigDecimal avgStock;
	
	
	public BigDecimal getMaxStock() {
		return maxStock;
	}
	public void setMaxStock(BigDecimal maxStock) {
		this.maxStock = maxStock;
	}
	public BigDecimal getMinStock() {
		return minStock;
	}
	public void setMinStock(BigDecimal minStock) {
		this.minStock = minStock;
	}
	public BigDecimal getAvgStock() {
		return avgStock;
	}
	public void setAvgStock(BigDecimal avgStock) {
		this.avgStock = avgStock;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public BigDecimal getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}
	public LocalDate getStockDate() {
		return stockDate;
	}
	public void setStockDate(LocalDate stockDate) {
		this.stockDate = stockDate;
	}
	public LocalTime getStocktime() {
		return stocktime;
	}
	public void setStocktime(LocalTime stocktime) {
		this.stocktime = stocktime;
	}
	

}
