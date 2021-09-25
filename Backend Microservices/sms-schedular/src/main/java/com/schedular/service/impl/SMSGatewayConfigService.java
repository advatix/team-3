package com.schedular.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schedular.dto.SMSGatewayConfigHeadersMappedDetails;
import com.schedular.exceptions.EntityNotFoundException;
import com.schedular.service.ISMSGatewayConfigDao;
import com.schedular.service.ISMSGatewayConfigService;

@Service
public class SMSGatewayConfigService implements ISMSGatewayConfigService {

  @Autowired
  private ISMSGatewayConfigDao gatewayConfigDao;

  @Override
  public SMSGatewayConfigHeadersMappedDetails findApplicationId(Integer applicationId)
      throws EntityNotFoundException {
    return gatewayConfigDao.findTopByApplicationIdActiveConfig(applicationId);
  }

  @Override
  public SMSGatewayConfigHeadersMappedDetails findById(Integer configId)
      throws EntityNotFoundException {
    return gatewayConfigDao.findById(configId);
  }

}
