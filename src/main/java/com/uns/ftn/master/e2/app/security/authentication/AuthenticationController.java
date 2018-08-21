package com.uns.ftn.master.e2.app.security.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uns.ftn.master.e2.app.security.domain.User;

@RestController
@RequestMapping("/auth/token")
public class AuthenticationController {

	@PostMapping
	public ResponseEntity<Object> authenticate(@RequestBody User user){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
	}
	
}