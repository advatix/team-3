/*
 * @author Advatix
 * @since 2019
 * @version 1.0
 */
package com.schedular.exceptions;

import org.springframework.http.HttpStatus;

/**
 * The Class EntityAlreadyExistsException.
 */
public class MinOrderQuantityRequiredException extends BaseException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3077468772228075912L;

	/** The Constant DEFAULT_MESSAGE. */
	private static final String DEFAULT_MESSAGE = "Minimum Order pickup quantity required !";

	/** The Constant DEFAULT_HTTP_STATUS. */
	private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.CONFLICT;

	/**
	 * Instantiates a new entity already exists exception.
	 */
	public MinOrderQuantityRequiredException() {
		this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
	}

	/**
	 * Instantiates a new entity already exists exception.
	 *
	 * @param httpStatus the http status
	 * @param errorMessage the error message
	 */
	public MinOrderQuantityRequiredException(HttpStatus httpStatus,
			String errorMessage) {
		super(httpStatus, DEFAULT_HTTP_STATUS, errorMessage, DEFAULT_MESSAGE);
	}

	/**
	 * Instantiates a new minimum order quantity required exception.
	 *
	 * @param errorMessage the error message
	 */
	public MinOrderQuantityRequiredException(String errorMessage) {
		super(DEFAULT_HTTP_STATUS, errorMessage);
	}
}
