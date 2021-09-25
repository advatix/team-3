package com.schedular.transformers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.schedular.domain.EmailQueue;
import com.schedular.domain.enums.MessageQueue;
import com.schedular.dto.BatchRequest;
import com.schedular.dto.EmailRequest;

public class BatchRequestTransformer {

	private BatchRequestTransformer() {
		super();
	}

	public static EmailQueue transformEmailQueue(EmailRequest request, Integer appId, UUID batchId, Integer configId) {
		EmailQueue queue = new EmailQueue();
		queue.setApplicationid(appId);
		queue.setBatchId(batchId.toString());
		queue.setMessage(request.getMessage());
		queue.setSubject(request.getSubject());
		queue.setMessageSent(Boolean.FALSE);
		queue.setQueueStatus(MessageQueue.INQUEUE.getId());
		queue.setConfigId(configId);
		queue.setMailTo(request.getMailTo());
		queue.setMailTocc(request.getMailToCC());
		queue.setMailTobcc(request.getMailToBCC());
		queue.setContentType(request.getContentType());
		return queue;
	}

	public static List<EmailQueue> transformEmailQueue(BatchRequest request, UUID batchId, Integer configId) {
		return request
				.getEmailList().parallelStream().map(requestDto -> BatchRequestTransformer
						.transformEmailQueue(requestDto, request.getAppId(), batchId, configId))
				.collect(Collectors.toList());
	}

}
