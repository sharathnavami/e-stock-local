package com.stock.market.service;

import java.util.List;

import com.stock.market.entity.Company;
import com.stock.market.exception.StockException;
import com.stock.market.model.CompanyResponse;

public interface CompanyService {
	
	public CompanyResponse getCompanyInfo(String companyCode) ;

	public List<CompanyResponse> getAllCompanyDetails();

	public int deleteCompanyDetails(String companycode);

	public Company registerCompany(Company company) throws StockException;

}
