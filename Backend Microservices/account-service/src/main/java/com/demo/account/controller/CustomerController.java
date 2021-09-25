package com.demo.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.account.entity.Customer;
import com.demo.account.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/create")
	public Customer createCustomer(@RequestBody Customer customer) {
		log.info("Inside createCustomer moethod of CustomerController");
		return customerService.createCustomer(customer);
	}
	
	@GetMapping("/{id}")
	public Customer findCustomerById(@PathVariable(name = "id") Long customerId) {
		log.info("Inside findCustomerById moethod of CustomerController");
		return customerService.findCustomerById(customerId);
	}
	
}
