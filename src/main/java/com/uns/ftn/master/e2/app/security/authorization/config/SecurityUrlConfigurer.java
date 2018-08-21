package com.uns.ftn.master.e2.app.security.authorization.config;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;

public class SecurityUrlConfigurer {

	private List<AntPathRequestMatcher> antMatchers;
	private List<AccessLock> accessLocks;
	private SecurityConfig securityConfig;
	
	public SecurityUrlConfigurer(SecurityConfig securityConfig, String... antMatchers) {
		accessLocks = new ArrayList<>();
		this.securityConfig = securityConfig;
		
		this.antMatchers = Stream.of(antMatchers)
				.map(AntPathRequestMatcher::new)
				.collect(Collectors.toList());
	}
	
	public SecurityConfig access(AccessLock... accessLock) {
		
		accessLocks.addAll(asList(accessLock));
		securityConfig.put(antMatchers, accessLocks);
		return securityConfig;
		
	}
	
}
