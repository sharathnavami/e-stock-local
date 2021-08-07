package com.stock.market.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.stock.market.entity.Company;
import com.stock.market.exception.StockException;
import com.stock.market.model.AuthToken;
import com.stock.market.model.CompanyResponse;
import com.stock.market.model.ResponseObject;
import com.stock.market.model.StockResponse;
import com.stock.market.repository.CompanyRepository;
import com.stock.market.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AuthToken authToken;
	
	@Autowired
    private EurekaClient eurekaClient;
	
	@Value("${service.stock.serviceId}")
    private String stockServiceId;
	
	@Value("${service.stock.url}")
    private String stockServiceUrl;
	
	private String getStockService() {
		Application application = eurekaClient.getApplication(stockServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        return "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/api/v1.0/market/stock";
	}

	@Override
	public CompanyResponse getCompanyInfo(String companyCode) {
		Company company = companyRepository.findByCompanyCode(companyCode);
		if(company!=null) {
			String stockUrl=getStockService()+"/latest/"+companyCode;
			ResponseEntity<StockResponse> stockResponse = restTemplate.exchange(stockUrl,HttpMethod.GET,getHeader(),StockResponse.class);
			return createCompanyResponse(company, stockResponse.getBody());
		}
		return null;
		
	}

	@Override
	public List<CompanyResponse> getAllCompanyDetails() {
		List<Company> allCompanies = companyRepository.findAll();
		String stockUrl=getStockService()+"/all";
		ResponseEntity<StockResponse[]> stockResponse = restTemplate.exchange(stockUrl,HttpMethod.GET,getHeader(),StockResponse[].class);
		List<StockResponse> allStocks = Arrays.asList(stockResponse.getBody());
		return allCompanies.stream().map(company -> getCompanyAndStockList(company, allStocks))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public int deleteCompanyDetails(String companycode) {
		
		String stockUrl=getStockService()+"/delete/"+companycode;
		ResponseEntity<ResponseObject> response =restTemplate.exchange(stockUrl,HttpMethod.DELETE,getHeader(),ResponseObject.class);
		return companyRepository.deleteByCompanyCode(companycode);
	}

	@Override
	public Company registerCompany(Company company) throws StockException {
		Company maxCompanyCode = companyRepository.findByCompanyCode(company.getCompanyCode());
		System.out.println(maxCompanyCode);
		if(company.getCompanyName().isEmpty() ||company.getCompanyCode().isEmpty() || company.getCompanyCEO().isEmpty() ||company.getCompanyTurnover().isEmpty()
				|| company.getCompanyWebsite().isEmpty() || company.getStockExchange().isEmpty()) {
			throw new StockException("Please enter all the mandatory details");
		}
		Double turnOver=Double.valueOf(company.getCompanyTurnover());
		if(turnOver<100000000) {
			throw new StockException("Company Turnover should be greater than 10CR");
		}
		if (maxCompanyCode != null ) {
			throw new StockException("Company Code Already Exist");
		} 
		return companyRepository.save(company);
	}

	private CompanyResponse createCompanyResponse(Company company, StockResponse stockResponse) {
		logger.debug("stockResponse==Company code:{}, stock value:{}",stockResponse.getCompanyCode(),stockResponse.getStockPrice());
		CompanyResponse companyResponse = new CompanyResponse();
		companyResponse.setCompanyCEO(company.getCompanyCEO());
		companyResponse.setCompanyCode(company.getCompanyCode());
		companyResponse.setCompanyName(company.getCompanyName());
		companyResponse.setCompanyTurnover(company.getCompanyTurnover());
		companyResponse.setStockExchange(company.getStockExchange());
		companyResponse.setStock(stockResponse);
		return companyResponse;
	}

	private CompanyResponse getCompanyAndStockList(Company company, List<StockResponse> stockResponse) {

		try {
			List<StockResponse> maxDate = stockResponse.stream()
					.filter(s -> s.getCompanyCode().equalsIgnoreCase(company.getCompanyCode()))
					.collect(Collectors.groupingBy(StockResponse::getStockDate, TreeMap::new, Collectors.toList()))
					.lastEntry().getValue();

			if (maxDate != null) {
				List<StockResponse> latestStock = maxDate.stream()
						.collect(Collectors.groupingBy(StockResponse::getStocktime, TreeMap::new, Collectors.toList()))
						.lastEntry().getValue();

				if (latestStock != null) {
					return createCompanyResponse(company, latestStock.get(0));
				}
			}
			return null;
		}catch (Exception e) {
			return createCompanyResponse(company, new StockResponse());
		}
		
	}
	
	private HttpEntity<String> getHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", authToken.getToken());
		return new HttpEntity<String>(headers);
	}

}
