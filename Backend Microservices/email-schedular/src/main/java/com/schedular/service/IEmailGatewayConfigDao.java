package com.schedular.service;

import com.schedular.domain.SMTPConfig;
import com.schedular.exceptions.EntityNotFoundException;

public interface IEmailGatewayConfigDao {

  SMTPConfig findTopByApplicationIdActiveConfig(Integer applicationId)
      throws EntityNotFoundException;

  SMTPConfig findById(Integer configId) throws EntityNotFoundException;

}
