package com.fse.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.fse.stock.filter.JwtFilter;

@SpringBootApplication
public class StockApplication {
	
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		
		final FilterRegistrationBean filterRegistrationBean= new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
	}


	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

}
