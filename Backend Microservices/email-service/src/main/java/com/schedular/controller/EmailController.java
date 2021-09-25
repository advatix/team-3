package com.schedular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.schedular.domain.SMTPConfig;
import com.schedular.dto.BatchRequest;
import com.schedular.exceptions.BaseException;
import com.schedular.service.IEmailBatchService;
import com.schedular.service.IEmailGatewayConfigService;
import com.schedular.utils.RestResponse;
import com.schedular.utils.RestUtils;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private IEmailGatewayConfigService configService;

	@Autowired
	private IEmailBatchService batchService;

	@GetMapping(path = "/getConfigDetails/{configId}")
	@ResponseBody
	public ResponseEntity<RestResponse<SMTPConfig>> getConfigDetails(@PathVariable(value = "configId") Integer configId)
			throws BaseException {
		return RestUtils.successResponse(configService.findById(configId));
	}

	@PostMapping(path = "/createBatch")
	@ResponseBody
	public ResponseEntity<RestResponse<String>> createBatch(@RequestBody BatchRequest batchRequest)
			throws BaseException {
		return RestUtils.successResponse(batchService.createEmailBatch(batchRequest));
	}

}
