package com.schedular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.schedular.dto.SMSGatewayConfigHeadersMappedDetails;
import com.schedular.exceptions.BaseException;
import com.schedular.service.ISMSGatewayConfigDao;
import com.schedular.utils.RestResponse;
import com.schedular.utils.RestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/sms-gateway-config")
@Api(value = "/sms-gateway-config", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE, tags = {"SMS Gateway Config Services"},
    hidden = false)
public class SMSGatewayConfigController {

  @Autowired
  private ISMSGatewayConfigDao configDaoService;

  @ApiOperation(value = "Get Config Details", response = SMSGatewayConfigHeadersMappedDetails.class,
      httpMethod = "GET", notes = "Get Config Details")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Data Json",
          response = SMSGatewayConfigHeadersMappedDetails.class),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 403, message = "Not Authenticated"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  // @ApiImplicitParams({@ApiImplicitParam(name = Constant.AUTH_TOKEN, required = true,
  // allowMultiple = false, paramType = "header", dataType = "string")})
  @GetMapping(path = "/getConfigDetails/{configId}")
  @ResponseBody
  public ResponseEntity<RestResponse<SMSGatewayConfigHeadersMappedDetails>> getConfigDetails(
      // @ApiIgnore @Authenticated UserTable userInfo,
      // @RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
      // @RequestHeader(name = Constant.APP_VERSION) String appVersion,
      @PathVariable(value = "configId") Integer configId) throws BaseException {
    return RestUtils.successResponse(configDaoService.findById(configId));
  }

}
