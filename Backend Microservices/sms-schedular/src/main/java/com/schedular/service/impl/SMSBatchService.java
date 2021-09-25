package com.schedular.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schedular.domain.EmailQueue;
import com.schedular.domain.SMSQueue;
import com.schedular.domain.SMTPConfig;
import com.schedular.dto.BatchRequest;
import com.schedular.dto.SMSGatewayConfigHeadersMappedDetails;
import com.schedular.exceptions.EntityNotFoundException;
import com.schedular.service.IEmailGatewayConfigService;
import com.schedular.service.IEmailQueueDao;
import com.schedular.service.ISMSBatchService;
import com.schedular.service.ISMSGatewayConfigService;
import com.schedular.service.ISMSQueueDao;
import com.schedular.utils.Constant;

@Service
public class SMSBatchService implements ISMSBatchService {

  @Autowired
  private ISMSQueueDao smsQueueService;

  @Autowired
  private ISMSGatewayConfigService smsGatewayConfigService;

  @Autowired
  private IEmailGatewayConfigService emailConfigService;

  @Autowired
  private IEmailQueueDao emailQueueDao;

  @Override
  public String createBatch(BatchRequest batchRequest) throws EntityNotFoundException {
    UUID batchId = batchRequest.getBatchId();
    SMSGatewayConfigHeadersMappedDetails getewayConfigDetails = null;
    if (Objects.nonNull(batchRequest.getGatewayId())) {
      getewayConfigDetails = smsGatewayConfigService.findById(batchRequest.getGatewayId());
    } else {
      getewayConfigDetails = smsGatewayConfigService.findApplicationId(batchRequest.getAppId());
    }
    List<SMSQueue> queueList = batchRequest.toQueue(batchId, getewayConfigDetails.getId());
    queueList = mergeQueueList(queueList);
    smsQueueService.save(queueList);
    return batchId.toString();
  }

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

  /**
   * Merge Queue list by save same message to multiple numbers and saved by single row with comma
   * seperated phone numbers
   * 
   * @param queueList
   */
  private List<SMSQueue> mergeQueueList(List<SMSQueue> queueList) {
    return new ArrayList<>(queueList.parallelStream()
        .collect(Collectors.toMap(SMSQueue::getMessage, Function.identity(), (o1, o2) -> {
          o1.setContactNumber(
              o1.getContactNumber().concat(Constant.COMMA_SEPERATOR).concat(o2.getContactNumber()));
          return o1;
        })).values());
  }

}
