package com.uns.ftn.master.e2.app.security.authorization.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;

@Component
public class SecurityConfig {
		
	private Map<List<AntPathRequestMatcher>, List<AccessLock>> configuration;
	
	@PostConstruct
	public void init() {
		configuration = new HashMap<>();
	}

	public Map<List<AntPathRequestMatcher>, List<AccessLock>> getConfiguration() {
		return configuration;
	}
	
	public SecurityUrlConfigurer antMatcher(String ... patterns) {
		
		return new SecurityUrlConfigurer(this, patterns);
		
	}
	
	public void put(List<AntPathRequestMatcher> antMatchers, List<AccessLock> accessLocks) {
		configuration.put(antMatchers, accessLocks);
	}
	
	public List<AccessLock> getLocks(HttpServletRequest request){
		
		Optional<List<AntPathRequestMatcher>> findFirst = configuration.keySet()
				.stream()
				.filter(l -> l.stream().anyMatch(p -> p.matches(request)))
				.findFirst();
		
		return findFirst.isPresent() ? configuration.get(findFirst.get()) : Collections.emptyList();
		
	}

	
}


