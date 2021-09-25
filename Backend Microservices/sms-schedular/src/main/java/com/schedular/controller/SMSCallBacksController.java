package com.schedular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.schedular.dto.TwillioSMSCallBackDto;
import com.schedular.service.ISMSMessageLogsService;
import com.schedular.utils.RestResponse;
import com.schedular.utils.RestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/sms-callback")
@Api(value = "/sms-callback", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE, tags = {"SMS CallBack Service"}, hidden = false)
public class SMSCallBacksController {

  @Autowired
  private ISMSMessageLogsService smsLoggingService;

  @ApiOperation(value = "Twillio SMS CallBack", response = String.class, httpMethod = "POST",
      notes = "Twillio SMS CallBack")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Thank you for submitting message response",
          response = String.class),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 403, message = "Not Authenticated"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  @PostMapping(path = "/twillio-sms", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ResponseBody
  public ResponseEntity<RestResponse<String>> createBatch(TwillioSMSCallBackDto callbackData) {
    smsLoggingService.save(callbackData);
    return RestUtils.successResponse("Thank you for submitting message response");
  }

}
