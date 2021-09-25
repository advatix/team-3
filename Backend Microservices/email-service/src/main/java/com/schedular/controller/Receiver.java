package com.schedular.controller;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.schedular.EmailSchedulerApplication;
import com.schedular.dto.UpdateInventoryCommonDto;

@Component
public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);

	@RabbitListener(id = "Inventory Receiver", queues = EmailSchedulerApplication.queueName)
	public void receiveMessage(List<UpdateInventoryCommonDto> message) {
		System.out.println("Received <" + message + ">");
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
