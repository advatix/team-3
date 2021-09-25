package com.schedular.service;

import com.schedular.domain.SMTPConfig;
import com.schedular.exceptions.EntityNotFoundException;

public interface IEmailGatewayConfigService {

  SMTPConfig findApplicationId(Integer applicationId) throws EntityNotFoundException;

  SMTPConfig findById(Integer configId) throws EntityNotFoundException;

}
