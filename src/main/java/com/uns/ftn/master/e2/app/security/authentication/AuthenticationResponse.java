package com.uns.ftn.master.e2.app.security.authentication;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private final String token;
	private final Long expires;
	
}
