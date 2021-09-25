package com.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user.dto.ResponseTemplate;
import com.demo.user.entity.User;
import com.demo.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		log.info("Inside createUser method of UserController");
		return userService.createUser(user);
	}
	
	@GetMapping("/{id}")
	public ResponseTemplate findUserWithCustomer(@PathVariable(name = "id") Long id) {
		log.info("Inside findUserWithCustomer method of UserController");
		return userService.findUserWithCustomer(id);
	}

}
