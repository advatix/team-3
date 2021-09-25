package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.EmailModel;
import com.example.demo.pojo.UserModel;
import com.example.demo.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@GetMapping("/data")
	public ResponseEntity<Iterable<UserModel>> searchQuery() {
		return new ResponseEntity<>(searchService.getAllResult(), HttpStatus.OK);
	}

	@GetMapping("/email")
	public ResponseEntity<Iterable<EmailModel>> searchEmail() {
		return new ResponseEntity<>(searchService.getAllResultEmail(), HttpStatus.OK);
	}
}