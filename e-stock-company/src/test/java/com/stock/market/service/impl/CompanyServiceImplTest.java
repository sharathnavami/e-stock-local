package com.stock.market.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.stock.market.entity.Company;
import com.stock.market.entity.Stocks;
import com.stock.market.exception.StockException;
import com.stock.market.model.AuthToken;
import com.stock.market.model.CompanyResponse;
import com.stock.market.model.StockResponse;
import com.stock.market.repository.CompanyRepository;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {
	

	Company company;
	Stocks stocks;
	
	@InjectMocks
	CompanyServiceImpl serviceImpl;
	
	@Mock
	private CompanyRepository companyRepository;

	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private AuthToken authToken;
	
	@Mock
	ResponseEntity<StockResponse> stockResponse;
	
	@Mock
	List<StockResponse> allStocks;

	ResponseEntity<StockResponse[]> responseEntity;
	
	List<Company> responseList= new ArrayList<>();
	
	
	@BeforeEach
	public void setupMock() {
		company= new Company(1,"Google", "GOG", "CEO","10000000000", "ww.abc.com","BSE");
		stocks= new Stocks("GOG",new BigDecimal(100000),LocalDateTime.now());
		StockResponse stockResponseObj= new StockResponse("GOG",new BigDecimal(100000),LocalDate.now(),LocalTime.now(),BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO);
		StockResponse[] stockResponseArray= {stockResponseObj};
		responseEntity= new ResponseEntity<StockResponse[]>(stockResponseArray, HttpStatus.OK);
		CompanyResponse response= new CompanyResponse("Google", "GOG", "CEO","10000000000", "ww.abc.com","BSE",stockResponseObj);
		responseList.add(company);
		stockResponse= new ResponseEntity<StockResponse>(new StockResponse(), HttpStatus.OK);
	}

	@Test
	public void getCompanyInfoTest() {
		when(companyRepository.findByCompanyCode("GOG")).thenReturn(company);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.eq(HttpMethod.GET) ,Mockito.anyObject(),Mockito.eq(StockResponse.class))).thenReturn(stockResponse);
		CompanyResponse response=serviceImpl.getCompanyInfo("GOG");
		Assertions.assertNotNull(response);
	}
	
	@Test
	public void getAllCompanyDetails() {
		when(restTemplate.exchange(Mockito.anyString(),Mockito.eq(HttpMethod.GET) ,Mockito.anyObject(),Mockito.eq(StockResponse[].class))).thenReturn(responseEntity);
		when(companyRepository.findAll()).thenReturn(responseList);
		List<CompanyResponse> response=serviceImpl.getAllCompanyDetails();
		Assertions.assertNotNull(response);
		
	}
	
	@Test
	public void deleteCompanyDetailsTest() {
		when(restTemplate.exchange(Mockito.anyString(),Mockito.eq(HttpMethod.DELETE) ,Mockito.anyObject(),Mockito.eq(String.class)))
		.thenReturn(new ResponseEntity<String>("success", HttpStatus.OK));
		when(companyRepository.deleteByCompanyCode("GOG")).thenReturn(1);
		serviceImpl.deleteCompanyDetails("GOG");
		verify(companyRepository,times(1)).deleteByCompanyCode("GOG");
	}
	
	@Test
	public void registerCompany() throws StockException {
		when(companyRepository.findByCompanyCode("GOG")).thenReturn(null);
		when(companyRepository.save(company)).thenReturn(company);
		Assertions.assertNotNull(serviceImpl.registerCompany(company));
	}
	

}
