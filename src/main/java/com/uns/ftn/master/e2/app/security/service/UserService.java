package com.uns.ftn.master.e2.app.security.service;

import java.util.List;

import com.uns.ftn.master.e2.app.security.domain.User;

public interface UserService {

	<T> List<T> getAccessTicketsByType(Class<T> clazz, User user);

	User getCurrentUser();
	
}
