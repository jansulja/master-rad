package com.uns.ftn.master.e2.app.security.service;

import static com.uns.ftn.master.e2.app.security.utils.CompoundAccessTicketUtils.extractAccessTickets;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uns.ftn.master.e2.app.security.authorization.component.rbac.RoleBasedAccessTicket;
import com.uns.ftn.master.e2.app.security.dao.UserRepository;
import com.uns.ftn.master.e2.app.security.domain.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		User user = userRepository.findByEmail(username);
		if(nonNull(user)) {
			
			List<RoleBasedAccessTicket> roles = extractAccessTickets(user.getCompoundAccessTickets()).stream()
					.filter(RoleBasedAccessTicket.class::isInstance)
					.map(RoleBasedAccessTicket.class::cast)
					.collect(toList());
			
			Collection<? extends GrantedAuthority> auth = roles.stream()
					.map(r -> new SimpleGrantedAuthority(r.getName()))
					.collect(toList());
			
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(), auth);
		}else {
			throw new UsernameNotFoundException("Bad credentials.");
		}
		
	}
	

	

}
