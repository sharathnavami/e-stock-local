package com.stock.market.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stocks {
	
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
	public LocalDateTime getStockDTM() {
		return stockDTM;
	}
	public void setStockDTM(LocalDateTime stockDTM) {
		this.stockDTM = stockDTM;
	}

	String companyCode;
	BigDecimal stockPrice;
	LocalDateTime stockDTM;
	public Stocks(String companyCode, BigDecimal stockPrice, LocalDateTime stockDTM) {
		super();
		this.companyCode = companyCode;
		this.stockPrice = stockPrice;
		this.stockDTM = stockDTM;
	}
	public Stocks() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
