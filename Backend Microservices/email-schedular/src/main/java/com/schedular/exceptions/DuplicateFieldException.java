/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.exceptions;

import org.springframework.http.HttpStatus;
import com.schedular.utils.ResponseDto;

/**
 * The Class DuplicateFieldException.
 */
public class DuplicateFieldException extends FileUploadException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -243909236486791164L;

  /** The Constant DEFAULT_MESSAGE. */
  private static final String DEFAULT_MESSAGE = "File Cannot Uploaded due to DuplicateFields ";

  /** The Constant DEFAULT_HTTP_STATUS. */
  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.BAD_REQUEST;

  /** The response dto. */
  private static ResponseDto responseDto;

  /**
   * Instantiates a new duplicate field exception.
   */
  public DuplicateFieldException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE, responseDto);
  }

  /**
   * Instantiates a new duplicate field exception.
   *
   * @param httpStatus the http status
   * @param message the message
   * @param responseDto the response dto
   */
  public DuplicateFieldException(HttpStatus httpStatus, String message, ResponseDto responseDto) {
    super();
    DuplicateFieldException.responseDto = responseDto;
  }

  /**
   * Instantiates a new duplicate field exception.
   *
   * @param message the message
   */
  public DuplicateFieldException(String message) {
    this(DEFAULT_HTTP_STATUS, message, responseDto);
  }

  public ResponseDto getResponseDto() {
    return responseDto;
  }

}
