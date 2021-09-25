package com.demo.user.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.user.dto.Customer;
import com.demo.user.dto.ResponseTemplate;
import com.demo.user.dto.ResponseTemplate.ResponseTemplateBuilder;
import com.demo.user.entity.User;
import com.demo.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	public User createUser(User entity) {
		log.info("Inside createUser method of UserService");
		return userRepository.save(entity);
	}

	public ResponseTemplate findUserWithCustomer(Long id) {
		log.info("Inside findUserWithCustomer method of UserService");
		ResponseTemplateBuilder response = new ResponseTemplate().toBuilder();
		User user = userRepository.findByUserId(id);
		response.user(user);
		if (Objects.nonNull(user)) {
			Customer customer = restTemplate.getForObject("http://ACCOUNT-SERVICE/api/customer/" + user.getCustomerId(),
					Customer.class);
			response.customer(customer);
		}
		return response.build();
	}

}
