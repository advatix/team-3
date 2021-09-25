package com.schedular.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.schedular.domain.SMTPConfig;
import com.schedular.domain.enums.Status;
import com.schedular.exceptions.EntityNotFoundException;
import com.schedular.repository.SmtpConfigRepository;
import com.schedular.service.IEmailGatewayConfigDao;

@Service
public class EmailGatewayConfigDao implements IEmailGatewayConfigDao {

  @Value("${sms.config.not.found.by.id}")
  private String entityNotFoundErrorMessage;

  @Value("${sms.config.not.found.by.appid}")
  private String entityNotFoundByApplicationIdErrorMessage;

  @Autowired
  private SmtpConfigRepository repository;

  @Override
  public SMTPConfig findTopByApplicationIdActiveConfig(Integer applicationId)
      throws EntityNotFoundException {
    return repository.findTopByApplicationIdAndStatus(applicationId, Status.ACTIVE.getId())
        .orElseThrow(() -> new EntityNotFoundException(entityNotFoundByApplicationIdErrorMessage));
  }

  @Override
  public SMTPConfig findById(Integer configId) throws EntityNotFoundException {
    return repository.findOneByConfigId(configId)
        .orElseThrow(() -> new EntityNotFoundException(entityNotFoundErrorMessage));
  }

}
