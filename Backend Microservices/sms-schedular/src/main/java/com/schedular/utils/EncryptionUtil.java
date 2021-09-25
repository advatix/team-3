/*
 * @author Advatix
 * @since 2019
 * @version 1.0
 */
package com.schedular.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class EncryptionUtil.
 */
public class EncryptionUtil {

  /** The Constant DEFAULT_ENCRYPTION_ALGORITHM. */
  private static final String DEFAULT_ENCRYPTION_ALGORITHM = "MD5";

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LogManager.getLogger(EncryptionUtil.class);

  /*
   * public static void main(String... strings) { System.out.println(encrypt("Ezr@#Adv8x!M78r~")); }
   */

  /**
   * Encrypt.
   *
   * @param input the input
   * @return the string
   */
  public static String encrypt(String input) {
    return encrypt(input, DEFAULT_ENCRYPTION_ALGORITHM);
  }

  /**
   * Encrypt.
   *
   * @param input the input
   * @param algorithmName the algorithm name
   * @return the string
   */
  public static String encrypt(String input, String algorithmName) {
    try {
      MessageDigest md = MessageDigest.getInstance(algorithmName);
      md.update(input.getBytes());
      byte[] mdbytes = md.digest();
      StringBuffer output = new StringBuffer();
      for (int i = 0; i < mdbytes.length; i++) {
        output.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      return output.toString();
    } catch (NoSuchAlgorithmException ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
    return "";
  }

}
