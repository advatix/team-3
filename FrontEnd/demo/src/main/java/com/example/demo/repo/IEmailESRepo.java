package com.example.demo.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.EmailModel;

@Repository
public interface IEmailESRepo extends ElasticsearchRepository<EmailModel, Long> {

}