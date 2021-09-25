/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.service;

/**
 * The Interface ISqlUserJourneyService.
 */
public interface ISqlUserJourneyService {

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
  void save(String requestKey, String requestId, String module, String url, String requestJson,
      String ipAddress, String userAgent, String headers);

  /**
   * Update response.
   *
   * @param requestId the request id
   * @param responseCode the response code
   */
  void updateResponse(String requestId, String responseCode);

  /**
   * Update.
   *
   * @param requestId the request id
   * @param userId the user id
   * @param userName the user name
   */
  void update(String requestId, Long userId, String userName);

  /**
   * Creates the.
   *
   * @param requestId the request id
   */
  void create(String requestId);

}
