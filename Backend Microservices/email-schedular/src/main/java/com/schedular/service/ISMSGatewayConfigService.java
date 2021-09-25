package com.schedular.service;

import com.schedular.dto.SMSGatewayConfigHeadersMappedDetails;
import com.schedular.exceptions.EntityNotFoundException;

public interface ISMSGatewayConfigService {

  SMSGatewayConfigHeadersMappedDetails findApplicationId(Integer applicationId)
      throws EntityNotFoundException;

  SMSGatewayConfigHeadersMappedDetails findById(Integer configId) throws EntityNotFoundException;

}
