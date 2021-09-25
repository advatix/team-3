package com.schedular.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.schedular.EmailSchedulerApplication;
import com.schedular.dto.UpdateInventoryCommonDto;

@RestController
public class QueueController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping(path = "/receiveInventory")
	@ResponseBody
	public ResponseEntity<?> receieveInventory(@RequestBody List<UpdateInventoryCommonDto> inventoryList)
			throws EntityNotFoundException, IllegalArgumentException {
		rabbitTemplate.convertAndSend(EmailSchedulerApplication.topicExchangeName, "com.adv.inventory", inventoryList);
		return new ResponseEntity<>("Successfully Queued !!!", HttpStatus.OK);
	}

}
