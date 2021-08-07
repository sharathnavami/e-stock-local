package com.stock.market.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stock.market.entity.Company;
import com.stock.market.exception.StockException;
import com.stock.market.model.AuthToken;
import com.stock.market.model.CompanyResponse;
import com.stock.market.model.ResponseObject;
import com.stock.market.service.CompanyService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="api/v1.0/market/company", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	AuthToken authToken;
	
	@PostMapping(path="/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject> registerCompany(@RequestBody Company company) {
		logger.info("companycode=="+company.getCompanyName());
		try {
			Company companySave = companyService.registerCompany(company);
			logger.info("Registered=="+companySave);
		} catch (StockException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponseObject>(new ResponseObject(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
       return new ResponseEntity<ResponseObject>(new ResponseObject("Registered"),HttpStatus.OK);
    }

	
	@GetMapping(path="/info/{companycode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<CompanyResponse> getCompanyInfo(@PathVariable String companycode,
    		@RequestHeader("Authorization") String authorization) {
		logger.info("companycode=="+companycode);
		authToken.setToken(authorization);
		CompanyResponse company=companyService.getCompanyInfo(companycode);
       logger.info("companyId=="+company);
       return new ResponseEntity<CompanyResponse>(company,HttpStatus.OK);
    }
	
	@GetMapping(path="/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<CompanyResponse>> getAllCompanyDetails(@RequestHeader("Authorization") String authorization) {
		authToken.setToken(authorization);
		List<CompanyResponse> allCompany=companyService.getAllCompanyDetails();
       logger.info("companyId=="+allCompany);
       return new ResponseEntity<List<CompanyResponse>>(allCompany,HttpStatus.OK);
    }
	
	@DeleteMapping(path="/delete/{companycode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject> deleteCompanyDetails(@PathVariable String companycode,@RequestHeader("Authorization") String authorization) {
		authToken.setToken(authorization);
       int count=companyService.deleteCompanyDetails(companycode);
       logger.info("Deleted count :{}",count);
       return new ResponseEntity<ResponseObject>(new ResponseObject("Company Deleted"),HttpStatus.OK);
    }
	
	
	

}
