package com.stock.market.model;

public class CompanyResponse {
	
	String companyName;
	String companyCode;
	String companyCEO;
	String companyTurnover;
	String companyWebsite;
	String stockExchange;
	StockResponse stock;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyCEO() {
		return companyCEO;
	}
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	public String getCompanyTurnover() {
		return companyTurnover;
	}
	public void setCompanyTurnover(String companyTurnover) {
		this.companyTurnover = companyTurnover;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	public StockResponse getStock() {
		return stock;
	}
	public void setStock(StockResponse stock) {
		this.stock = stock;
	}
	public CompanyResponse(String companyName, String companyCode, String companyCEO, String companyTurnover,
			String companyWebsite, String stockExchange, StockResponse stock) {
		super();
		this.companyName = companyName;
		this.companyCode = companyCode;
		this.companyCEO = companyCEO;
		this.companyTurnover = companyTurnover;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
		this.stock = stock;
	}
	public CompanyResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
