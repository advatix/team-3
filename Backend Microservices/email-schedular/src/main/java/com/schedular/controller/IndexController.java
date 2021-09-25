package com.schedular.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.schedular.cache.CacheContainer;
import com.schedular.commons.MessageType;
import com.schedular.utils.LogManager;
import com.schedular.utils.RestResponse;
import com.schedular.utils.RestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class IndexController.
 */
@Controller
@Api(value = "/", tags = {"Index Controller"}, hidden = false)
public class IndexController {

  /** The repo. */
  @Autowired
  private EntityManagerFactory repo;

  /**
   * Index.
   *
   * @return the string
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  @ApiOperation(value = "Index", hidden = true)
  @GetMapping(path = {"", "/"})
  public String index() throws UnsupportedEncodingException {
    return "index";
  }

  /**
   * Health check.
   *
   * @param timezone the timezone
   * @param headers the headers
   * @return the response entity
   * @throws JsonProcessingException the json processing exception
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  @ApiOperation(value = "Health Check", notes = "Server Health Check Api")
  @GetMapping(path = "/healthCheck")
  @ResponseBody
  public ResponseEntity<?> healthCheck(TimeZone timezone,
      @RequestHeader Map<String, String> headers)
      throws JsonProcessingException, UnsupportedEncodingException {
    LogManager.info(
        getClass(), timezone, "WebService is up. Timezone: " + timezone.getID() + " "
            + timezone.getDSTSavings() + " " + timezone.getDisplayName(Locale.US),
        MessageType.ApiRequestResponse);
    SessionFactory session = repo.unwrap(SessionFactory.class);

    @SuppressWarnings("unchecked")
    List<Object> list = session.openSession().createSQLQuery("SHOW TABLES").list();

    LogManager.info(getClass(), headers.toString(), MessageType.ApiRequest);
    return RestUtils.successResponse("WebService is up. Timezone: " + timezone.getID() + " "
        + timezone.getDSTSavings() + " " + timezone.getDisplayName(Locale.US) + " " + list);
  }

  /**
   * Reset cache instance.
   *
   * @return the response entity
   */
  @ApiOperation(value = "Reset Cache Instance", notes = "Reset Cache Instance")
  @GetMapping(path = "/resetCacheInstance")
  @ResponseBody
  public ResponseEntity<RestResponse<Boolean>> resetCacheInstance() {
    return RestUtils.successResponse(CacheContainer.getInstance().refreshCacheContainer());
  }

}
