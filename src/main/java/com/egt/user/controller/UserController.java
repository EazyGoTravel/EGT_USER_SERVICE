package com.egt.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egt.user.entity.request.UserRequest;
import com.egt.user.entity.response.UserResponse;
import com.egt.user.exception.InvalidUserRequest;
import com.egt.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/save/customer")
	public ResponseEntity<UserResponse> saveCustomerDetails(@RequestBody UserRequest userRequest)
			throws InvalidUserRequest {
		log.info(userRequest.toString());
		return userService.saveCustomerDetails(userRequest);
	}

}
