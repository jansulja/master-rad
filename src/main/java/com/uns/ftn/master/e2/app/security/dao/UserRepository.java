package com.uns.ftn.master.e2.app.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uns.ftn.master.e2.app.security.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
}
