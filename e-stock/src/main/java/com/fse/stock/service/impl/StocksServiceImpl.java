package com.fse.stock.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.stock.entity.Stocks;
import com.fse.stock.exception.StockException;
import com.fse.stock.model.StockResponse;
import com.fse.stock.repository.StockRepository;
import com.fse.stock.service.StocksService;


@Service
public class StocksServiceImpl implements StocksService {

	@Autowired
	StockRepository stockRepo;
	
	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public Stocks addStock(Stocks stocks, String companycode) throws StockException {
		if(stocks.getStockPrice()==null) {
			throw new StockException("Stock Price cannot be null");
		}
		stocks.setCompanyCode(companycode);
		stocks.setStockDTM(LocalDateTime.now());
		return stockRepo.save(stocks);
	}

	@Override
	public List<StockResponse> getStockDetails(String companycode, String startdate, String enddate) {
		List<StockResponse> stockRes=null;
		LocalDateTime startDate=LocalDate.parse(startdate,format).atStartOfDay();
		LocalDateTime endDate=LocalDate.parse(enddate,format).plusDays(1).atStartOfDay();
		List<Stocks> stockList= stockRepo.findByCompanyCodeAndStockDTMBetween(companycode,startDate ,endDate );
		if(stockList!=null && stockList.size()>0) {
			BigDecimal min=new BigDecimal(Integer.MAX_VALUE);
			BigDecimal max=new BigDecimal(Integer.MIN_VALUE);
			BigDecimal sum=BigDecimal.ZERO;
			for(Stocks stock : stockList ) {
				if(min.compareTo(stock.getStockPrice())== 1) {
					min=stock.getStockPrice();
				}
				if(max.compareTo(stock.getStockPrice())== -1) {
					max=stock.getStockPrice();
				}
				sum =sum.add(stock.getStockPrice());
			}
			BigDecimal avg=sum.divide(BigDecimal.valueOf(stockList.size()),2,RoundingMode.HALF_EVEN);
			final BigDecimal minimumStock=min;
			final BigDecimal maximumStock=max;
			final BigDecimal averageStock=avg;
			stockRes = stockList.stream()
			        .map(s -> inttoString(s,minimumStock,maximumStock,averageStock))
			        .collect(Collectors.toList());
			
		}
		return stockRes;
	}

	private StockResponse inttoString(Stocks s,BigDecimal min,BigDecimal max,BigDecimal avg) {
		StockResponse response = new StockResponse();
		response.setCompanyCode(s.getCompanyCode());
		response.setStockPrice(s.getStockPrice());
		response.setStockDate(s.getStockDTM().toLocalDate());
		response.setStocktime(s.getStockDTM().toLocalTime());
		response.setMinStock(min);
		response.setMaxStock(max);
		response.setAvgStock(avg);
		return response;
	}
	
	@Override
	public StockResponse getLatestStockDetailsForCompany(String companycode) {
		List<Stocks> stockList= stockRepo.findByCompanyCode(companycode);
		Optional<StockResponse> stockRes = stockList.stream()
		.max(Comparator.comparing(Stocks::getStockDTM))
        .map(s -> inttoString(s,null,null,null));
		if(stockRes.isPresent()){
			return stockRes.get();
		}
		return null;
	}
	
	@Override
	public List<StockResponse> getAllStock() {
		List<Stocks> allStocks= stockRepo.findAll();
		List<StockResponse> stockRes = allStocks.stream()
		        .map(s -> inttoString(s,null,null,null))
		        .collect(Collectors.toList());
		return stockRes;
	}
	
	@Override
	@Transactional
	public int deleteStocks(String companyCode) {
		return stockRepo.deleteByCompanyCode(companyCode);
	}

}
