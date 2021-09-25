/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class Utils.
 */
public class Utils {

  private Utils() {
    super();
  }

  /**
   * Convert date.
   *
   * @param timeZone the time zone
   * @return the date
   */
  public static Date convertDate(TimeZone timeZone) {
    return new DateTime().toMutableDateTime(DateTimeZone.forTimeZone(timeZone)).toDate();
  }

  /**
   * Token string.
   *
   * @param authUtils the auth utils
   * @param userName the user name
   * @param deviceType the device type
   * @param userAgent the user agent
   * @param globalLogin the global login
   * @return the string
   */
  public static String tokenString(AuthUtils authUtils, String userName, String deviceType,
      String userAgent, Boolean globalLogin) {
    return authUtils.createToken(userName + Constant.PIPE_SEPERATOR + deviceType
        + Constant.PIPE_SEPERATOR + userAgent + Constant.PIPE_SEPERATOR + (globalLogin ? 1 : 0));
  }

  /**
   * Mail token string.
   *
   * @param authUtils the auth utils
   * @param userName the user name
   * @param deviceType the device type
   * @param userAgent the user agent
   * @return the string
   */
  public static String mailTokenString(AuthUtils authUtils, String userName, String deviceType,
      String userAgent) {
    return authUtils.createMailToken(
        userName + Constant.PIPE_SEPERATOR + deviceType + Constant.PIPE_SEPERATOR + userAgent);
  }

  /**
   * Random password generator.
   *
   * @return the string
   */
  public static String randomPasswordGenerator() {
    int length = 10;
    boolean useLetters = true;
    boolean useNumbers = true;
    return RandomStringUtils.random(length, useLetters, useNumbers);
  }

  /**
   * ACCOUNTID : YYYY MM TTT AAAAA (14 Digit) YYYY : 4 digit year code MM : 2 digit month code TTT :
   * 3 digit alphabetical type code (From Account type master | CMR � Customer, PDR � Provider, CAR
   * � Carrier, SPL - Supplier) AAAAA : 5 digit auto increment code.
   *
   * @param accountType the account type
   * @param id the id
   * @return the string
   */
  private static String uniqueAccountId(String accountType, Long id) {
    DateTime date = new DateTime();
    String accountId = String.valueOf(id);
    if (accountId.length() < 5) {
      int addZeros = 5 - accountId.length();
      for (int i = 0; i < addZeros; i++) {
        accountId = "0" + accountId;
      }
    }
    return new StringBuilder().append(date.getYear()).append(date.toString("MM"))
        .append(accountType).append(accountId).toString();
  }

  /**
   * ACCOUNTID : 3 Alpha (A-Z) + 6 Numeric.
   *
   * @param alpha the alpha
   * @param id the id
   * @return the string
   */
  public static String accountId(String alpha, Long id) {
    if (id == 999999)
      alpha = alphaIncrement(alpha);
    String accountId = String.valueOf(id);
    if (accountId.length() < 6) {
      int addZeros = 6 - accountId.length();
      for (int i = 0; i < addZeros; i++) {
        accountId = "0" + accountId;
      }
    }
    return new StringBuilder(alpha).append(accountId).toString();
  }

  public static String workOrderGeneration(String parentOrder, String lastWorkOrderNumber) {
    Integer workOrderNumberInc = 0;
    if (Objects.nonNull(lastWorkOrderNumber))
      workOrderNumberInc =
          Integer.valueOf(lastWorkOrderNumber.substring(lastWorkOrderNumber.length() - 4));
    workOrderNumberInc += 1;

    if (workOrderNumberInc > 999)
      workOrderNumberInc = 1;

    if (Objects.nonNull(parentOrder)) {
      StringBuilder newWorkOrderNumber = new StringBuilder();
      int addZeros = 3 - String.valueOf(workOrderNumberInc).length();
      for (int i = 0; i < addZeros; i++) {
        newWorkOrderNumber = newWorkOrderNumber.append("0").append(newWorkOrderNumber);
      }
      return new StringBuilder(parentOrder).append(newWorkOrderNumber.toString()).toString();
    }
    return null;
  }

  /**
   * ACCOUNTID : 3 Alpha (A-Z) + 6 Numeric.
   *
   * @param alpha the alpha
   * @param id the id
   * @return the string
   */
  public static String priceId(String alpha, Long id) {
    if (id == 999999)
      alpha = alphaIncrement(alpha);
    String accountId = String.valueOf(id);
    if (accountId.length() < 6) {
      int addZeros = 6 - accountId.length();
      for (int i = 0; i < addZeros; i++) {
        accountId = "0" + accountId;
      }
    }
    return new StringBuilder(alpha).append(accountId).toString();
  }

  /**
   * LOCATIONID : YYYY MM TTT AAAAA LLLL (18 Digit) YYYY -> 4 digit year code MM -> 2 digit month
   * code TTT -> 3 digit alphabetical type code(From Account type master) AAAAA-> 5 digit auto
   * increment code LLLL -> 4 digit auto increment location code.
   *
   * @param accountType the account type
   * @param id the id
   * @param locationCode the location code
   * @return the string
   */
  private static String uniqueLocationId(String accountType, Long id, String locationCode) {
    DateTime date = new DateTime();
    String locId = String.valueOf(id);
    if (locId.length() < 5) {
      int addZeros = 5 - locId.length();
      for (int i = 0; i < addZeros; i++) {
        locId = "0" + locId;
      }
    }
    if (locationCode.length() < 4) {
      int addZeros = 4 - locationCode.length();
      for (int i = 0; i < addZeros; i++) {
        locationCode = "0" + locationCode;
      }
    }
    return new StringBuilder().append(date.getYear()).append(date.toString("MM"))
        .append(accountType).append(locId).append(locationCode).toString();
  }

  /**
   * 3 Alpha + 7 Numeric Alpha is �LOC�.
   *
   * @param alpha the alpha
   * @param id the id
   * @return the string
   */
  public static String locationId(String alpha, Long id) {
    if (id == 9999999)
      alpha = alphaIncrement(alpha);
    String idStr = String.valueOf(id);
    if (idStr.length() < 7) {
      int addZeros = 7 - idStr.length();
      for (int i = 0; i < addZeros; i++) {
        idStr = "0" + idStr;
      }
    }
    return new StringBuilder(alpha).append(idStr).toString();
  }

  /**
   * ORDERID : YYYY MM TT OOOOOO (14 Digit) YYYY -> 4 digit year code MM -> 2 digit month code TT ->
   * 2 digit alphabetical order type code(From Order type master | PU � Pickup, DL - Delivery)
   * OOOOOO -> 6 digit auto increment code.
   *
   * @param orderType the order type
   * @param id the id
   * @return the string
   */
  private static String uniqueOrderId(String orderType, Long id) {
    DateTime date = new DateTime();
    String orderId = String.valueOf(id);
    if (orderId.length() < 6) {
      int addZeros = 6 - orderId.length();
      for (int i = 0; i < addZeros; i++) {
        orderId = "0" + orderId;
      }
    }
    if (orderType.length() < 2) {
      int addZeros = 2 - orderType.length();
      for (int i = 0; i < addZeros; i++) {
        orderType = "0" + orderType;
      }
    }
    return new StringBuilder().append(date.getYear()).append(date.toString("MM")).append(orderType)
        .append(orderId).toString();
  }

  /**
   * 1 Alpha + 6 Numeric.
   *
   * @param alpha the alpha
   * @param id the id
   * @return the string
   */
  public static String orderId(String alpha, Long id) {
    if (id == 999999)
      alpha = alphaIncrement(alpha);
    String idStr = String.valueOf(id);
    if (idStr.length() < 6) {
      int addZeros = 6 - idStr.length();
      for (int i = 0; i < addZeros; i++) {
        idStr = "0" + idStr;
      }
    }
    return new StringBuilder(alpha).append(idStr).toString();
  }

  // Phase2 Order number generator
  public static String newOrderId(String orderTypeCode, String orderId) {
    if (Utils.validateStringNotEmpty(orderTypeCode) && Utils.validateStringIsEmpty(orderId))
      return new StringBuilder(orderTypeCode).append(1).toString();

    if (Utils.validateStringNotEmpty(orderTypeCode))
      try {
        return new StringBuilder(orderTypeCode)
            .append(Long.valueOf(orderId.substring(orderTypeCode.length())) + 1).toString();
      } catch (Exception e) {
        return new StringBuilder(orderTypeCode).append(1).toString();
      }

    return Utils.orderId("A", 0L);
  }

  /**
   * 1 Alpha + 6 Numeric.
   *
   * @param alpha the alpha
   * @param id the id
   * @return the string
   */
  public static String invoiceId(String alpha, Long id) {
    if (id == 999999)
      alpha = alphaIncrement(alpha);
    String idStr = String.valueOf(id);
    if (idStr.length() < 6) {
      int addZeros = 6 - idStr.length();
      for (int i = 0; i < addZeros; i++) {
        idStr = "0" + idStr;
      }
    }
    return new StringBuilder(alpha).append(idStr).toString();
  }

  /**
   * PRODUCTID : ACCOUNTID OOO (14 Digit) OOO -> 3 digit auto increment code.
   *
   * @param accountId the account id
   * @param id the id
   * @return the string
   */
  public static String uniqueProductId(String accountId, Long id) {
    String productId = String.valueOf(id);
    if (productId.length() < 3) {
      int addZeros = 3 - productId.length();
      for (int i = 0; i < addZeros; i++) {
        productId = "0" + productId;
      }
    }
    return new StringBuilder().append(accountId).append(productId).toString();
  }

  /**
   * Alpha increment.
   *
   * @param alpha the alpha
   * @return the string
   */
  public static String alphaIncrement(String alpha) {
    if (alpha.length() == 1) {
      if (alpha.equals("Z"))
        return "AA";
      else
        return (char) (alpha.charAt(0) + 1) + "";
    }
    if (alpha.charAt(alpha.length() - 1) != 'Z') {
      return alpha.substring(0, alpha.length() - 1) + (char) (alpha.charAt(alpha.length() - 1) + 1);
    }
    return alphaIncrement(alpha.substring(0, alpha.length() - 1)) + "A";
  }

  /**
   * Unique license plate id.
   *
   * @param prefix the prefix
   * @param id the id
   * @return the string
   */
  public static String uniqueLicensePlateId(String prefix, Long id) {
    DateTime date = new DateTime();
    String uniqueIdSeq = String.valueOf(id);
    if (uniqueIdSeq.length() < 7) {
      int addZeros = 7 - uniqueIdSeq.length();
      for (int i = 0; i < addZeros; i++) {
        uniqueIdSeq = "0" + uniqueIdSeq;
      }
    }
    return new StringBuilder().append(prefix).append("-").append(date.toString("MM"))
        .append(date.toString("dd")).append(date.toString("YY")).append("-").append(uniqueIdSeq)
        .toString();
  }

  /**
   * Display error for web.
   *
   * @param t the t
   * @return the string
   */
  public static String displayErrorForWeb(StackTraceElement[] t) {
    if (t == null)
      return "null";

    StringBuilder sb = new StringBuilder();
    for (StackTraceElement element : t) {
      sb.append(element.toString());
      sb.append("<br/>");
    }
    return sb.toString();
  }

  public static String truncate(String value, int length) {
    return value != null && value.length() > length ? value.substring(0, length) : value;
  }

  public static String urlEncoder(String url) {
    try {
      return UriUtils.encodeQuery(url, "UTF-8");
    } catch (Exception e) {
      return "";
    }
  }

  public static Boolean validateStringNotEmpty(String string) {
    return StringUtils.isNotBlank(string);
  }

  public static Boolean validateStringIsEmpty(String string) {
    return StringUtils.isBlank(string);
  }

  public static void copyProperties(Object source, Object target) {
    BeanUtils.copyProperties(source, target);
  }

  public static Boolean checkCollectionIsNotEmpty(Collection<?> collection) {
    return !CollectionUtils.isEmpty(collection);
  }

  public static Boolean checkCollectionIsNotEmpty(Map<?, ?> collection) {
    return !CollectionUtils.isEmpty(collection);
  }

  public static Boolean checkCollectionIsEmpty(Collection<?> collection) {
    return CollectionUtils.isEmpty(collection);
  }

  public static Boolean checkCollectionIsEmpty(Map<?, ?> collection) {
    return CollectionUtils.isEmpty(collection);
  }

  public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  public static List<Date> getMinMaxDate(Date date) {
    return Arrays.asList(
        new DateTime(date).hourOfDay().withMinimumValue().minuteOfHour().withMinimumValue()
            .secondOfMinute().withMinimumValue().millisOfSecond().withMinimumValue().toDate(),
        new DateTime(date).hourOfDay().withMaximumValue().minuteOfHour().withMaximumValue()
            .secondOfMinute().withMaximumValue().millisOfSecond().withMaximumValue().toDate());
  }

  public static DateTime getMinDate(Date date) {
    return new DateTime(date).hourOfDay().withMinimumValue().minuteOfHour().withMinimumValue()
        .secondOfMinute().withMinimumValue().millisOfSecond().withMinimumValue();
  }


  private static boolean isWorkingDay(Calendar cal) {
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY)
      return false;
    // tests for other holidays here
    // ...
    return true;
  }

  public static Date getWorkingDays(Integer daysToAdd) {
    Calendar cal = new GregorianCalendar();
    for (int i = 0; i < daysToAdd; i++)
      do {
        cal.add(Calendar.DAY_OF_MONTH, 1);
      } while (!isWorkingDay(cal));

    return cal.getTime();
  }

  public static void printJsonString(Object... objects) {
    String jsonString = toJsonString(objects);
    LogManager.getLogger(Utils.class).info(jsonString);
  }

  public static String toJsonString(Object... objects) {
    ObjectMapper mapperObj = new ObjectMapper();
    try {
      return mapperObj.writeValueAsString(objects);
    } catch (JsonProcessingException e) {
      LogManager.getLogger(Utils.class).error(e);
    }
    return "";
  }

}
