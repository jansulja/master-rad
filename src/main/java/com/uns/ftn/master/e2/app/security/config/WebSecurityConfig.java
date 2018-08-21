package com.uns.ftn.master.e2.app.security.config;

import static com.uns.ftn.master.e2.app.security.authorization.utils.AccessLockUtils.ibac;
import static com.uns.ftn.master.e2.app.security.authorization.utils.AccessLockUtils.rbac;
import static com.uns.ftn.master.e2.app.security.authorization.utils.AccessLockUtils.tbac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.uns.ftn.master.e2.app.security.authentication.JWTLoginFilter;
import com.uns.ftn.master.e2.app.security.authorization.config.SecurityConfig;
import com.uns.ftn.master.e2.app.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private SecurityConfig securityConfig;
	private UserDetailsServiceImpl userDetailsService;
	
	public WebSecurityConfig(SecurityConfig securityConfig, UserDetailsServiceImpl userDetailsService) {
		this.securityConfig = securityConfig;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		securityConfig
				.antMatcher("/access_control_test/test3")
					.access(rbac("ADMIN"))
				.antMatcher("/access_control_test/test4")
					.access(rbac("USER"))
				.antMatcher("/access_control_test/test5")
					.access(ibac("178.220.0.0-178.223.255.255"))
				.antMatcher("/access_control_test/test6")
					.access(tbac())
				.antMatcher("/access_control_test/test7")
					.access(ibac("178.220.0.0-178.223.255.255"), tbac())
		;
		
		http.authorizeRequests()
			.antMatchers("/access_control_test/test1")
				.permitAll()
			.antMatchers("/access_control_test/test2")
				.authenticated();
		
		
		http.csrf()
				.disable()
			.addFilter(new JWTLoginFilter(authenticationManager()))
			.headers()
				.frameOptions()
				.disable();
		
		
//		http.csrf()
//				.disable()
//				.authorizeRequests().antMatchers(SecurityConstants.AUTH_WHITELIST).permitAll()
//				.anyRequest()
//				.authenticated()
//				.and()
//				.addFilter(new JWTLoginFilter(authenticationManager()))
//				.headers()
//				.frameOptions()
//				.disable();
//		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}


	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	

	
	
}