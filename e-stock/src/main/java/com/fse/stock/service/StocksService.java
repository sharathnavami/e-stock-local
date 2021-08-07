package com.fse.stock.service;

import java.util.List;

import com.fse.stock.entity.Stocks;
import com.fse.stock.exception.StockException;
import com.fse.stock.model.StockResponse;

public interface StocksService {

	Stocks addStock(Stocks stocks, String companycode) throws StockException;

	List<StockResponse> getStockDetails(String companycode, String startdate, String enddate);

	StockResponse getLatestStockDetailsForCompany(String companycode);

	List<StockResponse> getAllStock();

	int deleteStocks(String companyCode);

}
