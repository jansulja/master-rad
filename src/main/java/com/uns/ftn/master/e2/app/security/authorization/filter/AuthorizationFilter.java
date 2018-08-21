package com.uns.ftn.master.e2.app.security.authorization.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.uns.ftn.master.e2.app.security.authorization.service.AuthorizationService;
import com.uns.ftn.master.e2.app.security.utils.SecurityConstants;

@Component
public class AuthorizationFilter extends GenericFilterBean {

	@Autowired
	private AuthorizationService authorizationService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		for (String string : SecurityConstants.AUTH_WHITELIST) {
			if(new AntPathRequestMatcher(string).matches(((HttpServletRequest) request))){
				chain.doFilter(request, response);
				return;
			}
		}
		
        try {
			authorizationService.authorize((HttpServletRequest) request);
		} catch (Exception e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return ;
		}
        
		chain.doFilter(request, response);
		 

	}

}
