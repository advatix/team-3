package com.schedular.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.schedular.commons.Logger;
import com.schedular.commons.MessageType;
import com.schedular.service.ISqlUserJourneyService;
import com.schedular.utils.Constant;
import com.schedular.utils.LogManager;

@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private ISqlUserJourneyService userJourneyService;

  // @Override
  // public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
  // Object handler) {
  // Map<String, String> headers = Collections.list(request.getHeaderNames()).stream()
  // .collect(Collectors.toMap(h -> h, request::getHeader));
  // logger.info(headers);
  // return true;
  // }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView mv) {
    LogManager.info(getClass(), String.valueOf(response.getStatus()),
        MessageType.ApiRequestResponse);
    userJourneyService.updateResponse(Logger.getLogger(getClass()).getValue(Constant.REQUEST_ID),
        String.valueOf(response.getStatus()));
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception exception) throws Exception {
    LogManager.info(getClass(), String.valueOf(response.getStatus()),
        MessageType.ApiRequestResponse);
    userJourneyService.updateResponse(Logger.getLogger(getClass()).getValue(Constant.REQUEST_ID),
        String.valueOf(response.getStatus()));
    Logger.getLogger(getClass()).clearContext();
  }

}
