package com.uns.ftn.master.e2.app.security.authorization.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.uns.ftn.master.e2.app.security.authorization.chain.AuthorizationHandler;
import com.uns.ftn.master.e2.app.security.authorization.config.SecurityConfig;
import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;
import com.uns.ftn.master.e2.app.security.authorization.service.AuthorizationService;

import lombok.SneakyThrows;

@Service
public class SimpleAuthorizationService implements AuthorizationService {

	private SecurityConfig securityConfig;
	private AuthorizationHandler authorizationChain;
	
	public SimpleAuthorizationService(SecurityConfig securityConfig, List<AuthorizationHandler> links) {
		this.securityConfig = securityConfig;
		this.authorizationChain = links.stream().reduce(null, (a,b) -> { b.setSuccessor(a); return b; });
	}

	@Override
	public void authorize(HttpServletRequest request) throws Exception {
		
		securityConfig.getLocks(request)
				.stream()
				.forEach(this::authorize);

	}

	@SneakyThrows
	private void authorize(AccessLock lock) {
		
		authorizationChain.handleRequest(lock);
		
	}

}
