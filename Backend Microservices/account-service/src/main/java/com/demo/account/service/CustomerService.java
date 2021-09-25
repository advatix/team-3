package com.demo.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.account.entity.Customer;
import com.demo.account.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	public Customer createCustomer(Customer customer) {
		log.info("Inside createCostomer method of CustomerService");
		return repository.save(customer);
	}

	public Customer findCustomerById(Long customerId) {
		log.info("Inside findCustomerById method of CustomerService");
		log.info("Customer Id: {}", customerId);
		return repository.findByCustomerId(customerId);
	}
	
}
