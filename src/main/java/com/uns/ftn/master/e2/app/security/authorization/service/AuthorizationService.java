package com.uns.ftn.master.e2.app.security.authorization.service;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationService {

	void authorize(HttpServletRequest request) throws Exception;
	
}
