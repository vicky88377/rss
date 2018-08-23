package com.mindtree.restaurantsearchservice.exceptions;

public class DataAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataAccessException(String errorResponse) {
		super(errorResponse);
	}

}
