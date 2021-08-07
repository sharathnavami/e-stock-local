package com.stock.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.market.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	public Company findByCompanyCode(String companyCode);
	
	public int deleteByCompanyCode(String companyCode);
	
	public Company findTopByOrderByCompanyCodeDesc();
	
	

	
}
