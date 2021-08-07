package com.fse.stock.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class StockResponse {
	
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
