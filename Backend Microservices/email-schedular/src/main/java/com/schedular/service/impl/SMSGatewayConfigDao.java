package com.schedular.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.schedular.domain.enums.Status;
import com.schedular.dto.SMSGatewayConfigHeadersMappedDetails;
import com.schedular.exceptions.EntityNotFoundException;
import com.schedular.repository.SMSGatewayConfigHeadersMappedRepository;
import com.schedular.service.ISMSGatewayConfigDao;

@Service
public class SMSGatewayConfigDao implements ISMSGatewayConfigDao {

  @Value("${sms.config.not.found.by.id}")
  private String entityNotFoundErrorMessage;

  @Value("${sms.config.not.found.by.appid}")
  private String entityNotFoundByApplicationIdErrorMessage;

  @Autowired
  private SMSGatewayConfigHeadersMappedRepository mappedHeadersRepo;

  @Override
  public SMSGatewayConfigHeadersMappedDetails findTopByApplicationIdActiveConfig(
      Integer applicationId) throws EntityNotFoundException {
    return mappedHeadersRepo
        .findTopByBasicInfoApplicationIdAndBasicInfoStatus(applicationId, Status.ACTIVE.getId())
        .orElseThrow(() -> new EntityNotFoundException(entityNotFoundByApplicationIdErrorMessage));
  }

  @Override
  public SMSGatewayConfigHeadersMappedDetails findById(Integer configId)
      throws EntityNotFoundException {
    return mappedHeadersRepo.findOneByIdConfigId(configId)
        .orElseThrow(() -> new EntityNotFoundException(entityNotFoundErrorMessage));
  }

}
