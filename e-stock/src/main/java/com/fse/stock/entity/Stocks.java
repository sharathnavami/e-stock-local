package com.fse.stock.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Stocks {
	
	@Id
    @GeneratedValue
    private int id;
	String companyCode;
	BigDecimal stockPrice;
	LocalDateTime stockDTM;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	


}
