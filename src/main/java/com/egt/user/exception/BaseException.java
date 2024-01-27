package com.egt.user.exception;

public sealed class BaseException extends Exception permits InvalidUserRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 326168107577974765L;
	public String errorDescipton;

	public BaseException(String errorDescipton) {
		super(errorDescipton);
	}
}
