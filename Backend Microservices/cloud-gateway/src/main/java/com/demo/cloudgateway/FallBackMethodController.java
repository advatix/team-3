package com.demo.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

	@GetMapping("/userServiceFallBack")
	public String userServiceFallBackMethod() {
		return "User service is taking longer than Expected." + " Please try again later";
	}
	
	@GetMapping("/customerServiceFallBack")
	public String customerServiceFallBackMethod() {
		return "Customer service is taking longer than Expected." + " Please try again later";
	}

}
