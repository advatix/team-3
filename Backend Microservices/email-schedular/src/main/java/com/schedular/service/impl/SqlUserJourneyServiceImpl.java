/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.schedular.domain.UserJourney;
import com.schedular.repository.UserJourneyRepository;
import com.schedular.service.ISqlUserJourneyService;

/**
 * The Class SqlUserJourneyServiceImpl.
 */
@Service
public class SqlUserJourneyServiceImpl implements ISqlUserJourneyService {

  /** The user journey repository. */
  @Autowired
  private UserJourneyRepository userJourneyRepository;

  private static final Map<String, UserJourney> JourneyMap = new HashMap<>();

  /**
   * Save.
   *
   * @param requestKey the request key
   * @param requestId the request id
   * @param module the module
   * @param url the url
   * @param requestJson the request json
   * @param ipAddress the ip address
   * @param userAgent the user agent
   */
  @Override
  @Async(value = "processExecutor")
  public void save(String requestKey, String requestId, String module, String url,
      String requestJson, String ipAddress, String userAgent, String headers) {

    UserJourney journey = new UserJourney();
    if (JourneyMap.containsKey(requestId)) {
      journey = JourneyMap.get(requestId);
    }
    journey.setIpAddress(ipAddress);
    journey.setModule(module);
    journey.setRequest(requestJson);
    journey.setUrl(url);
    journey.setUserAgent(userAgent);
    journey.setHeaders(headers);

    journey = userJourneyRepository.saveAndFlush(journey);
    JourneyMap.put(requestId, journey);
  }

  /**
   * Creates the.
   *
   * @param requestId the request id
   */
  @Override
  public void create(String requestId) {
    UserJourney journey = new UserJourney();
    journey.setApiId(requestId);
    journey = userJourneyRepository.saveAndFlush(journey);
    JourneyMap.put(requestId, journey);
  }

  /**
   * Update.
   *
   * @param requestId the request id
   * @param userId the user id
   * @param userName the user name
   */
  @Override
  @Async(value = "processExecutor")
  public void update(String requestId, Long userId, String userName) {
    if (JourneyMap.containsKey(requestId)) {
      UserJourney journey = JourneyMap.get(requestId);
      journey.setUserId(userId);
      journey.setUserName(userName);
      JourneyMap.put(requestId, journey);
    }
  }

  /**
   * Update response.
   *
   * @param requestId the request id
   * @param responseCode the response code
   */
  @Override
  @Async(value = "processExecutor")
  public void updateResponse(String requestId, String responseCode) {
    if (JourneyMap.containsKey(requestId)) {
      UserJourney journey = JourneyMap.get(requestId);
      journey.setResponseCode(responseCode);
      userJourneyRepository.saveAndFlush(journey);
      JourneyMap.remove(requestId);
    }
  }

}
