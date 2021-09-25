package com.schedular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.schedular.dto.BatchRequest;
import com.schedular.dto.QueryExecutorRequestDto;
import com.schedular.exceptions.BaseException;
import com.schedular.service.ISMSBatchService;
import com.schedular.utils.MySqlDynamicConnection;
import com.schedular.utils.RestResponse;
import com.schedular.utils.RestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/sms-batch")
@Api(value = "/sms-batch", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE, tags = {"SMS Batch Services"}, hidden = false)
public class SMSBatchController {

  @Autowired
  private ISMSBatchService batchService;

  @ApiOperation(value = "Create SMS Batch", response = String.class, httpMethod = "POST",
      notes = "Create SMS Batch")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "Successfully Added in Queue",
              response = String.class),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 403, message = "Not Authenticated"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")})
  // @ApiImplicitParams({@ApiImplicitParam(name = Constant.AUTH_TOKEN, required = true,
  // allowMultiple = false, paramType = "header", dataType = "string")})
  @PostMapping(path = "/createBatch")
  @ResponseBody
  public ResponseEntity<RestResponse<String>> createBatch(
      // @ApiIgnore @Authenticated UserTable userInfo,
      // @RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
      // @RequestHeader(name = Constant.APP_VERSION) String appVersion,
      @RequestBody @Validated BatchRequest batchRequest) throws BaseException {
    return RestUtils.successResponse(batchService.createBatch(batchRequest));
  }

  @ApiOperation(value = "Mannual Query", response = Boolean.class, httpMethod = "POST",
      notes = "Mannual Query")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "Successfully Added in Queue",
              response = Boolean.class),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 403, message = "Not Authenticated"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")})
  // @ApiImplicitParams({@ApiImplicitParam(name = Constant.AUTH_TOKEN, required = true,
  // allowMultiple = false, paramType = "header", dataType = "string")})
  @PostMapping(path = "/manualQuery")
  @ResponseBody
  public ResponseEntity<RestResponse<Boolean>> manualQuery(
      // @ApiIgnore @Authenticated UserTable userInfo,
      // @RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
      // @RequestHeader(name = Constant.APP_VERSION) String appVersion,
      @RequestBody @Validated QueryExecutorRequestDto requestDto) throws BaseException {
    MySqlDynamicConnection conn = new MySqlDynamicConnection();
    conn.connect(requestDto.getDatasource(), requestDto.getUsername(), requestDto.getPassword(),
        requestDto.getQuery());
    return RestUtils.successResponse(Boolean.TRUE);
  }

}
