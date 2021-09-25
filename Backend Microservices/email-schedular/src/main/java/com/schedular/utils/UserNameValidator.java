/*
 * @author Advatix
 * @since 2019
 * @version 1.0
 */
package com.schedular.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 * The Class UserNameValidator.
 */
public class UserNameValidator {

  /** The Constant USERNAME_PATTERN. */
  public static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{5,30}$";
  
  /** The Constant ERROR_MESSAGE. */
  public static final String ERROR_MESSAGE = "UserName should be alphanumeric with atleast 5 characters and max 30 characters.";

  /**
   * Validate.
   *
   * @param username the username
   * @return true, if successful
   */
  public static boolean validate(String username) {

    Pattern pattern = Pattern.compile(USERNAME_PATTERN);
    if (StringUtils.isEmpty(username)) {
      return false;
    }
    Matcher matcher = pattern.matcher(username);
    return matcher.matches();
  }

}
