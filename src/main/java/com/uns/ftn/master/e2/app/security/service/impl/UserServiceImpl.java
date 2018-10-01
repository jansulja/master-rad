package com.uns.ftn.master.e2.app.security.service.impl;

import static com.uns.ftn.master.e2.app.security.utils.CompoundAccessTicketUtils.extractAccessTickets;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uns.ftn.master.e2.app.security.dao.UserRepository;
import com.uns.ftn.master.e2.app.security.domain.User;
import com.uns.ftn.master.e2.app.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public <T> List<T> getAccessTicketsByType(Class<T> clazz, User user) {
		
		return extractAccessTickets(user.getCompoundAccessTickets()).stream()
			.filter(clazz::isInstance)
			.map(clazz::cast).collect(toList());
	
	}
	
	@Override
	public User getCurrentUser() {
		
		Object principal = Optional.ofNullable(SecurityContextHolder.getContext())
				.map(SecurityContext::getAuthentication)
				.map(Authentication::getPrincipal)
				.orElse(null);

		return nonNull(principal) ? userRepository.findByEmail((String) principal) : null;
	}

}
