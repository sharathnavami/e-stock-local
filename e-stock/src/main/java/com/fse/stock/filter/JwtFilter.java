package com.fse.stock.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		final HttpServletRequest request=(HttpServletRequest) req;
		final HttpServletResponse response=(HttpServletResponse) res;
		final String authHeader=request.getHeader("Authorization");
		if("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		}else {
			if(authHeader==null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or invalid authorization");
			}
			final String token=authHeader.substring(7);
			System.out.println("token=="+token);
			Claims claims=Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
			System.out.println("claims=="+claims.getSubject());
			request.setAttribute("claims", claims);
			chain.doFilter(req, res);
		}
	}

	
	
}

