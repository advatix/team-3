/*
 * 
 */
package com.schedular.exceptions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import com.schedular.commons.Logger;
import com.schedular.commons.MessageType;
import com.schedular.utils.LogManager;
import com.schedular.utils.RestResponse;
import com.schedular.utils.RestUtils;

/**
 * The Class RestExceptionHandler.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /** The logger. */
  private Logger LOGGER = Logger.getLogger(RestExceptionHandler.class);

  /** The ezr mail service. */
  // @Autowired
  // private EmailMailService ezrMailService;

  /** The environment. */
  @Value("${application.base.url}")
  private String environment;

  @Value("${spring.servlet.multipart.max-file-size}")
  private String maxFileSize;

  /**
   * Handle method argument not valid.
   *
   * @param ex the ex
   * @param headers the headers
   * @param status the status
   * @param request the request
   * @return the response entity
   */
  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.mvc.method.annotation. ResponseEntityExceptionHandler
   * #handleMethodArgumentNotValid(org.springframework .web.bind.MethodArgumentNotValidException,
   * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
   * org.springframework.web.context.request.WebRequest)
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return new ResponseEntity<>(
        new RestResponse<>(convertConstraintViolation(ex), null, Boolean.FALSE, status.value()),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Convert constraint violation.
   *
   * @param ex the ex
   * @return the string
   */
  protected String convertConstraintViolation(MethodArgumentNotValidException ex) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    List<String> errorMessages = new ArrayList<String>();
    for (FieldError c : fieldErrors) {
      errorMessages.add(c.getField() + '-' + c.getDefaultMessage());
    }
    /*
     * try { ezrMailService.sendException( "RequestKey: " + LOGGER.getValue(Constant.REQUEST_KEY) +
     * "<br>RequestId: " + LOGGER.getValue(Constant.REQUEST_ID) + "<br>" + errorMessages.toString(),
     * environment + ": MethodArgumentNotValidException", "ankush@brainworkindia.net"); } catch
     * (UnsupportedEncodingException e) { }
     */
    return errorMessages.toString();
  }

  /**
   * Handle Data Integrity Violation exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {DataIntegrityViolationException.class})
  protected ResponseEntity<RestResponse<?>> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex, WebRequest request) {
    LOGGER.error(ex.getRootCause().getLocalizedMessage(), ex);
    LogManager.error(getClass(), ex.getRootCause().getLocalizedMessage(), MessageType.Error);
    /*
     * try { ezrMailService.sendException( "RequestKey: " + LOGGER.getValue(Constant.REQUEST_KEY) +
     * "<br>RequestId: " + LOGGER.getValue(Constant.REQUEST_ID) + "<br>" +
     * ex.getRootCause().getLocalizedMessage(), environment + ": DataIntegrityViolationException",
     * "ankush@brainworkindia.net"); } catch (UnsupportedEncodingException e) { }
     */
    return RestUtils.errorResponse(ex.getRootCause().getLocalizedMessage(),
        ex.getLocalizedMessage(), HttpStatus.NOT_ACCEPTABLE);
  }

  /**
   * Handle lazy initialization exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {LazyInitializationException.class})
  protected ResponseEntity<?> handleLazyInitializationException(LazyInitializationException ex,
      WebRequest request) {
    LOGGER.error(ex.getLocalizedMessage(), ex);
    LogManager.error(getClass(), ex.getLocalizedMessage(), MessageType.Error);
    /*
     * try { String trace = Utils.displayErrorForWeb(ex.getStackTrace());
     * ezrMailService.sendException( "RequestKey: " + LOGGER.getValue(Constant.REQUEST_KEY) +
     * "<br>RequestId: " + LOGGER.getValue(Constant.REQUEST_ID) + "<br/>" + ex.getLocalizedMessage()
     * + "<br>StackTrace:<br/>" + trace, environment + ": Exception", "ankush@brainworkindia.net");
     * } catch (UnsupportedEncodingException e) { }
     */
    return RestUtils.errorResponse(ex.getLocalizedMessage(), ex.getLocalizedMessage(),
        HttpStatus.CONFLICT);
  }

  /**
   * Handle incorrect result size data access exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {IncorrectResultSizeDataAccessException.class})
  protected ResponseEntity<RestResponse<?>> handleIncorrectResultSizeDataAccessException(
      IncorrectResultSizeDataAccessException ex, WebRequest request) {
    LOGGER.error(ex.getRootCause().getLocalizedMessage(), ex);
    LogManager.error(getClass(), ex.getRootCause().getLocalizedMessage(), MessageType.Error);
    /*
     * try { ezrMailService.sendException( "RequestKey: " + LOGGER.getValue(Constant.REQUEST_KEY) +
     * "<br>RequestId: " + LOGGER.getValue(Constant.REQUEST_ID) + "<br>" +
     * ex.getRootCause().getLocalizedMessage(), environment +
     * ": IncorrectResultSizeDataAccessException", "ankush@brainworkindia.net"); } catch
     * (UnsupportedEncodingException e) { }
     */
    return RestUtils.errorResponse(ex.getRootCause().getLocalizedMessage(),
        ex.getLocalizedMessage(), HttpStatus.NOT_ACCEPTABLE);
  }

  /**
   * Handle SQL exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {SQLException.class})
  protected ResponseEntity<RestResponse<?>> handleSQLException(SQLException ex,
      WebRequest request) {
    LOGGER.error(ex.getMessage(), ex);
    LogManager.error(getClass(), ex.getLocalizedMessage(), MessageType.Error);
    /*
     * try { ezrMailService.sendException( "RequestKey: " + LOGGER.getValue(Constant.REQUEST_KEY) +
     * "<br>RequestId: " + LOGGER.getValue(Constant.REQUEST_ID) + "<br>" + ex.getLocalizedMessage(),
     * environment + ": SQLException", "ankush@brainworkindia.net"); } catch
     * (UnsupportedEncodingException e) { }
     */
    return RestUtils.errorResponse(ex.getLocalizedMessage(), ex.getLocalizedMessage(),
        HttpStatus.NOT_ACCEPTABLE);
  }

  /**
   * Handle unknown exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<RestResponse<?>> handleUnknownException(Exception ex,
      WebRequest request) {
    LOGGER.error(ex.getMessage(), ex);
    LogManager.error(getClass(), ex.getMessage(), MessageType.Error);
    LOGGER.error(ex.getClass().getName());
    /*
     * try { String trace = Utils.displayErrorForWeb(ex.getStackTrace());
     * ezrMailService.sendException( "RequestKey: " + LOGGER.getValue(Constant.REQUEST_KEY) +
     * "<br>RequestId: " + LOGGER.getValue(Constant.REQUEST_ID) + "<br/>" + ex.getLocalizedMessage()
     * + "<br>StackTrace:<br/>" + trace, environment + ": Exception", "ankush@brainworkindia.net");
     * } catch (UnsupportedEncodingException e) { }
     */
    return RestUtils.errorResponse(ex.getMessage(), ex.getLocalizedMessage(),
        BaseException.DEFAULT_HTTP_STATUS);
  }

  /**
   * Handle null pointer exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {NullPointerException.class})
  protected ResponseEntity<RestResponse<?>> handleNullPointerException(NullPointerException ex,
      WebRequest request) {
    LOGGER.error(ex.getMessage(), ex);
    LogManager.error(getClass(), ex.getMessage(), MessageType.Error);
    LOGGER.error(ex.getClass().getName());
    /*
     * try { String trace = Utils.displayErrorForWeb(ex.getStackTrace());
     * ezrMailService.sendException( "RequestKey: " + LOGGER.getValue(Constant.REQUEST_KEY) +
     * "<br>RequestId: " + LOGGER.getValue(Constant.REQUEST_ID) + "<br/>" + ex.getLocalizedMessage()
     * + "<br>StackTrace:<br/>" + trace, environment + ": NullPointerException",
     * "ankush@brainworkindia.net"); } catch (UnsupportedEncodingException e) { }
     */
    return RestUtils.errorResponse(ex.getMessage(), ex.getLocalizedMessage(),
        BaseException.DEFAULT_HTTP_STATUS);
  }

  /**
   * Handle max upload size exceeded exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {MultipartException.class})
  protected ResponseEntity<RestResponse<?>> handleMaxUploadSizeExceededException(
      MultipartException ex, WebRequest request) {
    LOGGER.error(ex.getMessage(), ex);
    LogManager.error(getClass(), ex.getMessage(), MessageType.Error);
    LOGGER.error(ex.getClass().getName());
    /*
     * try { String trace = Utils.displayErrorForWeb(ex.getStackTrace());
     * ezrMailService.sendException( "RequestKey: " + LOGGER.getValue(Constant.REQUEST_KEY) +
     * "<br>RequestId: " + LOGGER.getValue(Constant.REQUEST_ID) + "<br/>" + ex.getLocalizedMessage()
     * + "<br>StackTrace:<br/>" + trace, environment + ": MultipartException",
     * "ankush@brainworkindia.net"); } catch (UnsupportedEncodingException e) { }
     */
    return RestUtils.errorResponse(ex.getMessage(), ex.getLocalizedMessage(),
        BaseException.DEFAULT_HTTP_STATUS);
  }

  /**
   * Handle base exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {BaseException.class})
  protected ResponseEntity<RestResponse<?>> handleBaseException(BaseException ex,
      WebRequest request) {
    LOGGER.error(ex.getMessage(), ex);
    LogManager.error(getClass(), ex.getMessage(), MessageType.Error);
    return RestUtils.errorResponse(ex.getMessage(), ex.getData(), ex.getHttpStatus());
  }

  /**
   * Handle entity not found exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {EntityNotFoundException.class})
  protected ResponseEntity<RestResponse<?>> handleEntityNotFoundException(
      EntityNotFoundException ex, WebRequest request) {
    LOGGER.error(ex.getMessage(), ex);
    LogManager.error(getClass(), ex.getMessage(), MessageType.Error);
    return RestUtils.errorResponse(ex.getMessage(), ex.getHttpStatus());
  }

  /**
   * Handle entity already exists exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {EntityAlreadyExistsException.class})
  protected ResponseEntity<RestResponse<?>> handleEntityAlreadyExistsException(
      EntityAlreadyExistsException ex, WebRequest request) {
    LOGGER.error(ex.getMessage(), ex);
    LogManager.error(getClass(), ex.getMessage(), MessageType.Error);
    return RestUtils.errorResponse(ex.getMessage(), ex.getHttpStatus());
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc,
      HttpServletRequest request, HttpServletResponse response) {
    LogManager.info(getClass(), "FileUploadException: " + exc.getMessage(), MessageType.ApiRequest);
    ModelAndView view = new ModelAndView();
    view.setView(new MappingJackson2JsonView());
    RestResponse<?> apiResponseData = null;
    apiResponseData = new RestResponse<>("File size cannot exceed from " + maxFileSize,
        exc.getMessage(), false, HttpStatus.PAYLOAD_TOO_LARGE.value());
    // view.addObject(apiResponseData);
    view.addObject("test", true);
    view.setStatus(HttpStatus.PAYLOAD_TOO_LARGE);
    response.setStatus(view.getStatus().value());
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    return view;
  }

  @ExceptionHandler(value = {UnexpectedRollbackException.class})
  protected ResponseEntity<RestResponse<?>> unexpectedRollbackException(
      UnexpectedRollbackException ex, WebRequest request) {
    LOGGER.error(ex.getLocalizedMessage(), ex);
    LogManager.error(getClass(), ex.getLocalizedMessage(), MessageType.Error);
    return RestUtils.errorResponse(ex.getLocalizedMessage(), ex.getLocalizedMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
