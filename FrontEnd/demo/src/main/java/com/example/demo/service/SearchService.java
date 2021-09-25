package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.EmailModel;
import com.example.demo.pojo.UserModel;
import com.example.demo.repo.IEmailESRepo;
import com.example.demo.repo.IUserESRepo;

@Service
public class SearchService {

	@Value("${api.elasticsearch.uri}")
	private String elasticSearchUri;
	
	@Autowired
	private IUserESRepo userEsRepo;
	
	@Autowired
	private IEmailESRepo emailRepo;

	
	public Iterable<UserModel> getAllResult(){
		return userEsRepo.findAll();
	} 
	
	
	public Iterable<EmailModel> getAllResultEmail(){
		return emailRepo.findAll();
	} 

}