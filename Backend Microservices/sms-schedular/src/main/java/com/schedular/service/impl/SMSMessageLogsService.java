package com.schedular.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schedular.commons.Logger;
import com.schedular.domain.SMSMessageLogs;
import com.schedular.domain.embeddables.ApplicationId;
import com.schedular.domain.embeddables.SMSGatewayId;
import com.schedular.dto.TwillioSMSCallBackDto;
import com.schedular.dto.TwillioSMSResponseDto;
import com.schedular.repository.SMSMessageLogsRepository;
import com.schedular.service.ISMSMessageLogsService;

@Service
public class SMSMessageLogsService implements ISMSMessageLogsService {

  private Logger log = Logger.getLogger(getClass());

  @Autowired
  private SMSMessageLogsRepository repository;

  @Override
  public SMSMessageLogs save(String batchId, Integer applicationId, Integer configId,
      TwillioSMSResponseDto domain) {
    SMSMessageLogs logValue = new SMSMessageLogs();
    logValue.setAppId(new ApplicationId().withAppId(applicationId));
    logValue.setConfigId(new SMSGatewayId().withConfigId(configId));
    logValue.setBatchId(batchId);
    logValue.setMessageId(domain.getSid());
    logValue.setErrorCode(domain.getError_code());
    logValue.setErrorMessage(domain.getError_message());
    logValue.setMessageStatus(domain.getStatus());
    logValue.setFrom(domain.getFrom());
    logValue.setTo(domain.getTo());
    setMediaCount(logValue, domain.getNum_media());
    return repository.save(logValue);
  }

  @Override
  public SMSMessageLogs save(TwillioSMSCallBackDto domain) {
    Optional<SMSMessageLogs> logPrevValue =
        repository.findTopByMessageIdOrderByCreatedOnDesc(domain.getMessageSid());
    if (Boolean.TRUE.equals(logPrevValue.isPresent())) {
      SMSMessageLogs logValue = new SMSMessageLogs();
      logValue.setAppId(logPrevValue.get().getAppId());
      logValue.setConfigId(logPrevValue.get().getConfigId());
      logValue.setBatchId(logPrevValue.get().getBatchId());
      logValue.setMessageId(domain.getMessageSid());
      logValue.setFrom(domain.getFrom());
      logValue.setTo(domain.getTo());
      logValue.setMessageStatus(domain.getMessageStatus());
      return repository.save(logValue);
    }
    return null;
  }

  private void setMediaCount(SMSMessageLogs logValue, String count) {
    try {
      logValue.setMediaCount(Integer.valueOf(count));
    } catch (Exception e) {
      log.error(e.getLocalizedMessage(), e);
    }
  }

}
