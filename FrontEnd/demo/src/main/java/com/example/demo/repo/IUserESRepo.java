package com.example.demo.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.UserModel;

@Repository
public interface IUserESRepo extends ElasticsearchRepository<UserModel, Long> {
	
	

}