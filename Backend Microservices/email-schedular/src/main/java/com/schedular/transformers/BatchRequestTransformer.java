package com.schedular.transformers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.schedular.domain.EmailQueue;
import com.schedular.domain.SMSQueue;
import com.schedular.domain.embeddables.SMSGatewayId;
import com.schedular.domain.enums.MessageQueue;
import com.schedular.dto.BatchRequest;
import com.schedular.dto.ContactsRequest;
import com.schedular.dto.EmailRequest;

public class BatchRequestTransformer {

  private BatchRequestTransformer() {
    super();
  }

  public static SMSQueue transform(ContactsRequest request, Integer appId, Integer attempts,
      UUID batchId, SMSGatewayId configId) {
    SMSQueue queue = new SMSQueue();
    queue.setApplicationid(appId);
    queue.setAttempts(attempts);
    queue.setBatchId(batchId.toString());
    queue.setContactNumber(request.getContact());
    queue.setMessage(request.getMessage());
    queue.setMessageSent(Boolean.FALSE);
    queue.setQueueStatus(MessageQueue.INQUEUE.getId());
    queue.setConfigId(configId);
    return queue;
  }

  public static EmailQueue transformEmailQueue(EmailRequest request, Integer appId, UUID batchId,
      Integer configId) {
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

  public static List<SMSQueue> transform(BatchRequest request, UUID batchId,
      SMSGatewayId configId) {
    return request
        .getContactsList().parallelStream().map(contact -> BatchRequestTransformer
            .transform(contact, request.getAppId(), 0, batchId, configId))
        .collect(Collectors.toList());
  }

  public static List<EmailQueue> transformEmailQueue(BatchRequest request, UUID batchId,
      Integer configId) {
    return request
        .getEmailList().parallelStream().map(requestDto -> BatchRequestTransformer
            .transformEmailQueue(requestDto, request.getAppId(), batchId, configId))
        .collect(Collectors.toList());
  }

}
