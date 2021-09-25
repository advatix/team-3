package com.schedular.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schedular.utils.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * The Class ErrorController.
 */
@Controller
@ApiIgnore
@Api(value = "Error Controller", hidden = true)
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

  private Logger log = LogManager.getLogger(getClass());

  /**
   * Handle error.
   *
   * @param request the request
   * @return the string
   */
  @ApiOperation(value = "Error Controller", hidden = true)
  @RequestMapping("/error")
  public String handleError(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());

      // if (statusCode == HttpStatus.NOT_FOUND.value()) {
      log.info(statusCode);
      // } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
      // log.info(statusCode);
      // } else if (statusCode == HttpStatus.PAYLOAD_TOO_LARGE.value()) {
      // log.info(statusCode);
      // }
    }
    return "error";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
