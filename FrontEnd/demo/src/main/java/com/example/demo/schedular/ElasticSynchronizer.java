package com.example.demo.schedular;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.User;
import com.example.demo.repo.IUserESRepo;

@Service
public class ElasticSynchronizer {

	// Fields
	@Autowired
	private IUserESRepo userESRepo;
	// Constructor

	//@Scheduled(cron = "0 */3 * * * *")
	@Transactional
	public void sync() {
		System.out.println("Start Syncing - {} " + LocalDateTime.now());
		this.syncUsers();
		System.out.println(" End Syncing - {} " + LocalDateTime.now());
	}

	private void syncUsers() {

	}

	private static Predicate getModificationDatePredicate(CriteriaBuilder cb, Root<?> root) {
		return null;
	}
}