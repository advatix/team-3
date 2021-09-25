/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.utils;

/**
 * The Class MobileNumberValidator.
 */
public class MobileNumberValidator {

  /** The Constant PATTERN. */
  private static final String PATTERN = "[0-9]+";

  private MobileNumberValidator() {
    super();
  }

  /**
   * Validate.
   *
   * @param text the text
   * @return true, if successful
   */
  public static boolean validate(String text) {

    if (text == null) {
      return Boolean.FALSE;
    }

    if (text.matches(PATTERN) && text.length() == 10) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
}
