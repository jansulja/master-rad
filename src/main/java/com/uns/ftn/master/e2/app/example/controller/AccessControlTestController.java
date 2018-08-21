package com.uns.ftn.master.e2.app.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/access_control_test")
public class AccessControlTestController {

	@GetMapping("/test1")
	@ApiOperation(value = "This resource is available to everyone")
	public ResponseEntity<String> test1() {

		return ResponseEntity.ok("This resource is accessible to anyone.");
 
	}

	@GetMapping("/test2")
	@ApiOperation(authorizations = {
			@Authorization(value = "apiKey") }, value = "This resource is available to all authenticated users")
	public ResponseEntity<String> test2() {

		return ResponseEntity.ok("This resource is accessible to all authenticated users.");

	}

	@GetMapping("/test3")
	@ApiOperation(authorizations = {
			@Authorization(value = "apiKey") }, value = "This resource is available only to Administrators")
	public ResponseEntity<String> test3() {

		return ResponseEntity.ok("This resource is available only to Administrators");

	}

	@GetMapping("/test4")
	@ApiOperation(authorizations = {
			@Authorization(value = "apiKey") }, value = "This resource is available only to Users")
	public ResponseEntity<String> test4() {

		return ResponseEntity.ok("This resource is available only to Users");

	}

	@GetMapping("/test5")
	@ApiOperation(authorizations = {
			@Authorization(value = "apiKey") }, value = "This resource is available to all authenticated users that have IP in range from 178.220.0.0 to 178.223.255.255")
	public ResponseEntity<String> test5() {

		return ResponseEntity.ok(
				"This resource is available to all authenticated users that have IP in range from 178.220.0.0 to 178.223.255.255");

	}

	@GetMapping("/test6")
	@ApiOperation(authorizations = {
			@Authorization(value = "apiKey") }, value = "This resource is available to all authenticated users that have a time permission at access time")
	public ResponseEntity<String> test6() {

		return ResponseEntity
				.ok("This resource is available to all authenticated users that have a time permission at access time");

	}

	@GetMapping("/test7")
	@ApiOperation(authorizations = {
			@Authorization(value = "apiKey") }, value = "This resource is available to all authenticated users that have a time permission at access time and IP in range from 178.220.0.0 to 178.223.255.255")
	public ResponseEntity<String> test7() {

		return ResponseEntity.ok(
				"This resource is available to all authenticated users that have a time permission at access time and IP in range from 178.220.0.0 to 178.223.255.255");

	}
	
}
