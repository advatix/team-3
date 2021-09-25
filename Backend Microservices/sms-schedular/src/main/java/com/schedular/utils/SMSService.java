package com.schedular.utils;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.schedular.commons.Logger;
import com.schedular.domain.SMSGatewayConfigHeaders;
import com.schedular.domain.SMSQueue;
import com.schedular.domain.enums.Gateway;
import com.schedular.dto.SMSGatewayConfigHeadersMappedDetails;
import com.schedular.dto.TwillioSMSResponseDto;
import com.schedular.exceptions.EntityNotFoundException;
import com.schedular.service.ISMSGatewayConfigDao;
import com.schedular.service.ISMSMessageLogsService;

@Service
public class SMSService {

  private static final Logger log = Logger.getLogger(SMSService.class);

  @Autowired
  private RestTemplateHelper restTemplate;

  @Value("${twillio.callback.post-url}")
  private String twillioCallBackUrl;

  @Autowired
  private ISMSGatewayConfigDao smsGatewayConfigService;

  @Autowired
  private ISMSMessageLogsService smsLoggingService;

  @Async("processExecutor")
  public void sendSmsPool(SMSQueue entities) {
    try {
      log.info("In Pool - {}", entities);
      SMSGatewayConfigHeadersMappedDetails configDetails =
          smsGatewayConfigService.findById(entities.getConfigId().getConfigId());
      if (Gateway.TWILLIO.equals(configDetails.getBasicInfo().getGateway())) {
        List<SMSGatewayConfigHeaders> headers = configDetails.getConfigList();
        MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<>();
        String password = "";
        if (Boolean.TRUE.equals(Utils.checkCollectionIsNotEmpty(headers))) {
          for (SMSGatewayConfigHeaders header : headers) {
            if (header.getHeaderKey().equals(configDetails.getBasicInfo().getSid())) {
              password = header.getHeaderValue();
            }
            headersMap.add(header.getHeaderKey(), header.getHeaderValue());
          }
        }
        smsLoggingService.save(entities.getBatchId(), entities.getApplicationid(),
            entities.getConfigId().getConfigId(),
            twillioPostRequest(configDetails.getBasicInfo().getUrl(),
                configDetails.getBasicInfo().getSid(), password,
                configDetails.getBasicInfo().getFromNumber(), entities.getContactNumber(),
                entities.getMessage(), headersMap));
      }
    } catch (EntityNotFoundException e) {
      log.error(e.getLocalizedMessage(), e);
    }
  }

  private TwillioSMSResponseDto twillioPostRequest(String url, String sid, String password,
      String fromNumber, String contact, String message, MultiValueMap<String, String> headers,
      String... mediaUrl) {
    MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
    values.add("From", fromNumber);
    values.add("To", contact);
    values.add("Body", message);
    if (Objects.nonNull(mediaUrl))
      values.add("mediaUrl", String.join(Constant.COMMA_SEPERATOR, mediaUrl));
    values.add("StatusCallback", twillioCallBackUrl); // POST REQUEST
    TwillioSMSResponseDto responseLog = restTemplate.postForUrlEncodedEntityBasicAuth(
        TwillioSMSResponseDto.class, url, sid, password, headers, values);
    log.info(responseLog.toString());
    return responseLog;
  }

}
