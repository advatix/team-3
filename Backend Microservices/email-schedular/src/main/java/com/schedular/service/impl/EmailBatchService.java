package com.schedular.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schedular.domain.EmailQueue;
import com.schedular.domain.SMTPConfig;
import com.schedular.dto.BatchRequest;
import com.schedular.exceptions.EntityNotFoundException;
import com.schedular.service.IEmailGatewayConfigService;
import com.schedular.service.IEmailQueueDao;
import com.schedular.service.IEmailBatchService;

@Service
public class EmailBatchService implements IEmailBatchService {

  @Autowired
  private IEmailGatewayConfigService emailConfigService;

  @Autowired
  private IEmailQueueDao emailQueueDao;

  @Override
  public String createEmailBatch(BatchRequest batchRequest) throws EntityNotFoundException {
    UUID batchId = batchRequest.getBatchId();
    SMTPConfig getewayConfigDetails = null;
    if (Objects.nonNull(batchRequest.getGatewayId())) {
      getewayConfigDetails = emailConfigService.findById(batchRequest.getGatewayId());
    } else {
      getewayConfigDetails = emailConfigService.findApplicationId(batchRequest.getAppId());
    }
    List<EmailQueue> queueList =
        batchRequest.toEmailQueue(batchId, getewayConfigDetails.getConfigId());
    emailQueueDao.save(queueList);
    return batchId.toString();
  }

}
