package com.egt.user.exception;

public final class InvalidUserRequest extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3841107122567207518L;

	public String errorDescipton;

	public InvalidUserRequest(String errorDescipton) {
		super(errorDescipton);
	}
}
