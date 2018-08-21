package com.uns.ftn.master.e2.app.security.utils;

public class SecurityConstants {
	
	private SecurityConstants() {
		throw new IllegalStateException("Utility class");
	}
	
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/auth/token";
    
    public static final String[] AUTH_WHITELIST = {        
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**", "/", "/csrf", SIGN_UP_URL, "/h2-console/**", "/login"
    };
}