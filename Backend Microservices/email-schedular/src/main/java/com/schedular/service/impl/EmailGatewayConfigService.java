package com.schedular.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schedular.domain.SMTPConfig;
import com.schedular.exceptions.EntityNotFoundException;
import com.schedular.service.IEmailGatewayConfigDao;
import com.schedular.service.IEmailGatewayConfigService;

@Service
public class EmailGatewayConfigService implements IEmailGatewayConfigService {

  @Autowired
  private IEmailGatewayConfigDao gatewayConfigDao;

  @Override
  public SMTPConfig findApplicationId(Integer applicationId) throws EntityNotFoundException {
    return gatewayConfigDao.findTopByApplicationIdActiveConfig(applicationId);
  }

  @Override
  public SMTPConfig findById(Integer configId) throws EntityNotFoundException {
    return gatewayConfigDao.findById(configId);
  }

}
