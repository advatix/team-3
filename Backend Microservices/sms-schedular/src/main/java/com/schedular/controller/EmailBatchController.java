package com.schedular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.schedular.dto.BatchRequest;
import com.schedular.exceptions.BaseException;
import com.schedular.service.ISMSBatchService;
import com.schedular.utils.RestResponse;
import com.schedular.utils.RestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/email-batch")
@Api(value = "/email-batch", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Email Batch Services"}, hidden = false)
public class EmailBatchController {

  @Autowired
  private ISMSBatchService batchService;

  @ApiOperation(value = "Create Email Batch", response = String.class, httpMethod = "POST",
      notes = "Create Email Batch")
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
      @RequestBody BatchRequest batchRequest) throws BaseException {
    return RestUtils.successResponse(batchService.createEmailBatch(batchRequest));
  }

}
