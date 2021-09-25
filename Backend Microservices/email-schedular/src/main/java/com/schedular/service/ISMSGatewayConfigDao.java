package com.schedular.service;

import com.schedular.dto.SMSGatewayConfigHeadersMappedDetails;
import com.schedular.exceptions.EntityNotFoundException;

public interface ISMSGatewayConfigDao {

  SMSGatewayConfigHeadersMappedDetails findTopByApplicationIdActiveConfig(Integer applicationId)
      throws EntityNotFoundException;

  SMSGatewayConfigHeadersMappedDetails findById(Integer configId) throws EntityNotFoundException;

}
