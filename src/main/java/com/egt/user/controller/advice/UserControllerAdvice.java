package com.egt.user.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.egt.user.constants.CommonConstants;
import com.egt.user.entity.response.UserResponse;
import com.egt.user.exception.InvalidUserRequest;

@ControllerAdvice
public class UserControllerAdvice {

	@ExceptionHandler
	public ResponseEntity<UserResponse> handleInvalidUserRequest(InvalidUserRequest e) {
		e.printStackTrace();
		return new ResponseEntity<>(getResponse(e.getMessage()), HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<UserResponse> handleException(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(getResponse(CommonConstants.ERROR_UNABLE_TO_PROCESS_DUE_TO_ERROR), HttpStatus.OK);
	}

	private UserResponse getResponse(String errorDesc) {

		return UserResponse.builder().errorDesc(errorDesc).status(false).build();
	}
}
