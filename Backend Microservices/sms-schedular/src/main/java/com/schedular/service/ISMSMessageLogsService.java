package com.schedular.service;

import com.schedular.domain.SMSMessageLogs;
import com.schedular.dto.TwillioSMSCallBackDto;
import com.schedular.dto.TwillioSMSResponseDto;

public interface ISMSMessageLogsService {

  SMSMessageLogs save(String batchId, Integer applicationId, Integer configId,
      TwillioSMSResponseDto domain);

  SMSMessageLogs save(TwillioSMSCallBackDto domain);

}
