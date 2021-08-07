package com.fse.stock.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.stock.entity.Stocks;
import com.fse.stock.exception.StockException;
import com.fse.stock.model.ResponseObject;
import com.fse.stock.model.StockResponse;
import com.fse.stock.service.StocksService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="api/v1.0/market/stock", produces = MediaType.APPLICATION_JSON_VALUE)
public class StocksController {

	private static final Logger logger = LoggerFactory.getLogger(StocksController.class);

	@Autowired
	StocksService stocksService;

	@GetMapping(path = "/get/{companycode}/{startdate}/{enddate}", produces = "application/json")
	public ResponseEntity<List<StockResponse>> fetchStockPriceList(@PathVariable String companycode, @PathVariable String startdate,
			@PathVariable String enddate) {
		logger.info("id==" + companycode);
		List<StockResponse> stock = stocksService.getStockDetails(companycode,startdate,enddate);
		logger.info("stock: {}", stock);
		return new ResponseEntity<List<StockResponse>>(stock, HttpStatus.OK);
	}

	@PostMapping(path = "/add/{companycode}", produces = "application/json")
	public ResponseEntity<ResponseObject> addStockToCompany(@RequestBody Stocks stocks,@PathVariable String companycode) {
		logger.info("id==" + companycode);
		try {
			Stocks stock = stocksService.addStock(stocks,companycode);
			logger.info("Stock added successfully :{}",stock);
		} catch (StockException e) {
			return new ResponseEntity<ResponseObject>(new ResponseObject(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseObject>(new ResponseObject("Stock added successfully"), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete/{companycode}", produces = "application/json")
	public ResponseEntity<ResponseObject> deleteStockForCompany(@PathVariable String companycode) {
		logger.info("id==" + companycode);
		int stock = stocksService.deleteStocks(companycode);
		logger.info("Stock deleted successfully :{}",stock);
		return new ResponseEntity<ResponseObject>(new ResponseObject("Stock deleted successfully"), HttpStatus.OK);
	}
	
	@GetMapping(path = "/all", produces = "application/json")
	public ResponseEntity<List<StockResponse>> getAllStocksForCompany() {
		List<StockResponse> stock = stocksService.getAllStock();
		logger.info("All Stock fetch successfully :{}",stock);
		return new ResponseEntity<List<StockResponse>>(stock, HttpStatus.OK);
	}
	
	@GetMapping(path = "/latest/{companycode}", produces = "application/json")
	public ResponseEntity<StockResponse> getLatestStocksForCompany(@PathVariable String companycode) {
		StockResponse stock = stocksService.getLatestStockDetailsForCompany(companycode);
		logger.info("Latest Stock for comapany :{}",stock);
		return new ResponseEntity<StockResponse>(stock, HttpStatus.OK);
	}

}
