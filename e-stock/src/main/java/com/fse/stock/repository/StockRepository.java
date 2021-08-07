package com.fse.stock.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.stock.entity.Stocks;


@Repository
public interface StockRepository extends JpaRepository<Stocks, Integer> {

	public List<Stocks> findByCompanyCodeAndStockDTMBetween(String companyCode, LocalDateTime localDateTime, LocalDateTime localDateTime2);

	public List<Stocks> findByCompanyCode(String companycode);

	public int deleteByCompanyCode(String companyCode);

}
