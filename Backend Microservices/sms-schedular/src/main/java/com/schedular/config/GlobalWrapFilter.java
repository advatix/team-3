/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;
import com.schedular.cache.CacheContainer;
import com.schedular.cache.CacheNames;
import com.schedular.commons.Logger;
import com.schedular.commons.MessageType;
import com.schedular.service.ISqlUserJourneyService;
import com.schedular.utils.Constant;
import com.schedular.utils.LogManager;
import net.minidev.json.JSONObject;

/**
 * The Class GlobalWrapFilter.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalWrapFilter implements Filter {

  /** The user journey service. */
  @Autowired
  private ISqlUserJourneyService userJourneyService;

  /**
   * Inits the.
   *
   * @param filterConfig the filter config
   * @throws ServletException the servlet exception
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  /**
   * Do filter.
   *
   * @param request the request
   * @param response the response
   * @param chain the chain
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws ServletException the servlet exception
   */
  @SuppressWarnings("unchecked")
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    Logger.getLogger(getClass()).setValue(Constant.REQUEST_ID, UUID.randomUUID().toString());
    userJourneyService.create(Logger.getLogger(getClass()).getValue(Constant.REQUEST_ID));

    MultiReadRequest wrapper = new MultiReadRequest((HttpServletRequest) request,
        Logger.getLogger(getClass()).getValue(Constant.REQUEST_ID));
    ContentCachingRequestWrapper requestCacheWrapperObject =
        new ContentCachingRequestWrapper((HttpServletRequest) request);
    requestCacheWrapperObject.getParameterMap();
    LogManager.info(getClass(), Arrays.toString(requestCacheWrapperObject.getContentAsByteArray()),
        MessageType.ApiRequest);

    Map<String, Object> pathVariables = (HashMap<String, Object>) ((HttpServletRequest) request)
        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    if (pathVariables != null)
      LogManager.info(getClass(), pathVariables.toString(), MessageType.ApiRequest);

    // LogManager.info(getClass(), HttpStatus.resolve(((HttpServletResponse) response).getStatus())
    // != null ? HttpStatus.resolve(((HttpServletResponse) response).getStatus()).name() : "",
    // MessageType.ApiResponse);
    // LogManager.info(getClass(), getResponsePayload((HttpServletResponse) response),
    // MessageType.ApiResponse);

    chain.doFilter(wrapper, response);
  }

  /**
   * Destroy.
   */
  @Override
  public void destroy() {}

  /**
   * The Class MultiReadRequest.
   */
  class MultiReadRequest extends HttpServletRequestWrapper {

    /** The request body. */
    private String requestBody;

    /**
     * Instantiates a new multi read request.
     *
     * @param request the request
     * @param requestId the request id
     */
    public MultiReadRequest(HttpServletRequest request, String requestId) {
      super(request);
      try {
        String token = request.getHeader(Constant.AUTH_TOKEN);
        if (!StringUtils.isEmpty(token) && CacheContainer.getInstance().isKeyAvailable(token,
            CacheNames.userActivity.getName())) {
          String requestKey = (String) CacheContainer.getInstance().getValueFromCache(token,
              CacheNames.userActivity.getName());
          Logger.getLogger(getClass()).setValue(Constant.REQUEST_KEY, requestKey);
        }
      } catch (Exception e) {
      }

      String requestUrl = getURL(request);
      LogManager.info(getClass(), requestUrl + " [" + request.getMethod() + "]",
          MessageType.ApiRequest);

      Boolean canWriteLogs = Boolean.TRUE;
      for (String path : Constant.FILE_UPLOAD_API_PATH) {
        String matchContent = path;
        // LogManager.info(getClass(), matchContent, MessageType.ApiRequest);
        if (requestUrl.contains(matchContent)) {
          canWriteLogs = Boolean.FALSE;
          break;
        }
      }
      if (canWriteLogs) {
        try {
          requestBody =
              request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
          LogManager.info(getClass(), requestBody, MessageType.ApiRequest);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      JSONObject headers = new JSONObject();
      for (Enumeration<String> e = request.getHeaderNames(); e != null && e.hasMoreElements();) {
        String nextHeaderName = e.nextElement();
        String headerValue = request.getHeader(nextHeaderName);
        headers.put(nextHeaderName, headerValue);
      }
      userJourneyService.save(Logger.getLogger(getClass()).getValue(Constant.REQUEST_KEY), requestId,
          request.getServletPath(), requestUrl, requestBody, getClientIpAddress(request),
          request.getHeader("user-agent"), headers.toString());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
      final ByteArrayInputStream byteArrayInputStream =
          new ByteArrayInputStream(requestBody.getBytes());
      return new ServletInputStream() {
        @Override
        public boolean isFinished() {
          return byteArrayInputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
          return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }

        @Override
        public int read() throws IOException {
          return byteArrayInputStream.read();
        }
      };
    }

    @Override
    public BufferedReader getReader() throws IOException {
      return new BufferedReader(
          new InputStreamReader(this.getInputStream(), Charset.forName("UTF-8")));
    }

    /**
     * Gets the url.
     *
     * @param req the req
     * @return the url
     */
    public String getURL(HttpServletRequest req) {

      String scheme = req.getScheme(); // http
      String serverName = req.getServerName(); // hostname.com
      int serverPort = req.getServerPort(); // 80
      String contextPath = req.getContextPath(); // /mywebapp
      String servletPath = req.getServletPath(); // /servlet/MyServlet
      String pathInfo = req.getPathInfo(); // /a/b;c=123
      String queryString = req.getQueryString(); // d=789

      // Reconstruct original requesting URL
      StringBuilder url = new StringBuilder();
      url.append(scheme).append("://").append(serverName);

      if (serverPort != 80 && serverPort != 443) {
        url.append(":").append(serverPort);
      }

      url.append(contextPath).append(servletPath);

      if (pathInfo != null) {
        url.append(pathInfo);
      }
      if (queryString != null) {
        url.append("?").append(queryString);
      }
      return url.toString();
    }
  }

  /**
   * Gets the response payload.
   *
   * @param response the response
   * @return the response payload
   */
  @SuppressWarnings("unused")
  private String getResponsePayload(HttpServletResponse response) {
    String payload = "[unknown]";
    ContentCachingResponseWrapper wrapper =
        WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
    if (wrapper != null) {

      byte[] buf = wrapper.getContentAsByteArray();
      if (buf != null && buf.length > 0) {
        try {
          payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
          wrapper.copyBodyToResponse();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    return payload;
  }

  /** The Constant HEADERS_TO_TRY. */
  private static final String[] HEADERS_TO_TRY =
      {"X-Forwarded-For", "X-Real-IP", "REMOTE_ADDR", "Proxy-Client-IP", "WL-Proxy-Client-IP",
          "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
          "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA"};

  /**
   * Gets the client ip address.
   *
   * @param request the request
   * @return the client ip address
   */
  private String getClientIpAddress(HttpServletRequest request) {
    for (String header : HEADERS_TO_TRY) {
      String ip = request.getHeader(header.toLowerCase());
      if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
        return ip;
      }
    }
    return request.getRemoteAddr();
  }
}
