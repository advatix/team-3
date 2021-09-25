/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.schedular.exceptions.BaseException;
import com.schedular.exceptions.NotAuthorizedException;

/**
 * The Class AuthUtils.
 */
@Service // ("authUtils")
public class AuthUtils {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LogManager.getLogger(AuthUtils.class);

  /** The Constant JWT_HEADER. */
  private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);

  /** The Constant TOKEN_SECRET. */
  private static final String TOKEN_SECRET = "TgY_Kx=Zhcvw/79k";

  /** The Constant ISSUER. */
  private static final String ISSUER = "com.ezr.oms";

  /** The login expiration minutes. */
  @Value("#{new Integer('${login.token.expiration.minutes}')}")
  private Integer loginExpirationMinutes;

  /** The email expiration hours. */
  @Value("#{new Integer('${email.token.expiration.hours}')}")
  private Integer emailExpirationHours;

  public AuthUtils() {
    super();
  }

  /**
   * Decode token.
   *
   * @param authHeader the auth header
   * @return the string
   * @throws NotAuthorizedException the not authorized exception
   */
  public String decodeToken(String authHeader) throws NotAuthorizedException {
    return xor(decode(authHeader).getSubject().getBytes());
  }

  /**
   * Decode.
   *
   * @param authHeader the auth header
   * @return the read only JWT claims set
   * @throws NotAuthorizedException the not authorized exception
   */
  public ReadOnlyJWTClaimsSet decode(String authHeader) throws NotAuthorizedException {

    ReadOnlyJWTClaimsSet jwtClaimsSet = null;
    try {
      jwtClaimsSet = SignedJWT.parse(getSerializedToken(authHeader)).getJWTClaimsSet();
    } catch (ParseException e) {
      throw new NotAuthorizedException(ErrorMessages.INVALID_TOKEN_MESSAGE);
    }
    Date expiryDate = jwtClaimsSet.getExpirationTime();
    Date now = DateTime.now().toDate();

    if (now.after(expiryDate)) {
      // throw new NotAuthorizedException(ErrorMessages.TOKEN_EXPIRED);
    }
    return jwtClaimsSet;
  }

  /**
   * Decode token string.
   *
   * @param token the token
   * @return the read only JWT claims set
   * @throws NotAuthorizedException the not authorized exception
   */
  public ReadOnlyJWTClaimsSet decodeTokenString(String token) throws NotAuthorizedException {

    ReadOnlyJWTClaimsSet jwtClaimsSet = null;
    try {
      jwtClaimsSet = SignedJWT.parse(getSerializedToken(token)).getJWTClaimsSet();
    } catch (ParseException e) {
      throw new NotAuthorizedException(ErrorMessages.INVALID_TOKEN_MESSAGE);
    }

    return jwtClaimsSet;
  }

  /**
   * Creates the token.
   *
   * @param subject the subject
   * @return the string
   */
  public String createToken(String subject) {

    JWTClaimsSet claim = new JWTClaimsSet();
    claim.setSubject(xor(subject.getBytes()));
    claim.setIssuer(ISSUER);
    claim.setIssueTime(DateTime.now().toDate());
    claim.setExpirationTime(DateTime.now().plusMinutes(loginExpirationMinutes).toDate());
    claim.setJWTID(UUID.randomUUID().toString());

    JWSSigner signer = new MACSigner(TOKEN_SECRET);
    SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
    try {
      jwt.sign(signer);
    } catch (JOSEException e) {
      LOGGER.error(e.getMessage(), e);
    }

    return jwt.serialize();
  }

  /**
   * Creates the mail token.
   *
   * @param subject the subject
   * @return the string
   */
  public String createMailToken(String subject) {

    JWTClaimsSet claim = new JWTClaimsSet();
    claim.setSubject(xor(subject.getBytes()));
    claim.setIssuer(ISSUER);
    claim.setIssueTime(DateTime.now().toDate());
    claim.setExpirationTime(DateTime.now().plusHours(emailExpirationHours).toDate());
    claim.setJWTID(UUID.randomUUID().toString());

    JWSSigner signer = new MACSigner(TOKEN_SECRET);
    SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
    try {
      jwt.sign(signer);
    } catch (JOSEException e) {
      LOGGER.error(e.getMessage(), e);
    }

    return jwt.serialize();
  }

  /**
   * Gets the serialized token.
   *
   * @param authHeader the auth header
   * @return the serialized token
   */
  private static String getSerializedToken(String authHeader) {
    return authHeader;
  }

  /**
   * Xor.
   *
   * @param input the input
   * @return the string
   */
  private static String xor(final byte[] input) {
    final byte[] output = new byte[input.length];
    final byte[] secret = TOKEN_SECRET.getBytes();
    int spos = 0;
    for (int pos = 0; pos < input.length; ++pos) {
      output[pos] = (byte) (input[pos] ^ secret[spos]);
      spos += 1;
      if (spos >= secret.length) {
        spos = 0;
      }
    }
    return new String(output);
  }

  /**
   * Creates the random token.
   *
   * @param min the min
   * @param max the max
   * @return the int
   */
  public static int createRandomToken(int min, int max) {
    return (int) Math.floor(Math.random() * (max - min + 1)) + min;
  }

  /**
   * Validate page and size.
   *
   * @param pageNumber the page number
   * @param pageSize the page size
   * @throws BaseException the base exception
   */
  public static void validatePageAndSize(Integer pageNumber, Integer pageSize)
      throws BaseException {
    if ((pageNumber == null) || (pageSize == null) || (pageNumber < 0) || (pageSize < 0)) {
      throw new BaseException(HttpStatus.BAD_REQUEST, "PageNumber or PageSize not valid");
    }
  }

  /**
   * Encode base 64.
   *
   * @param string the string
   * @return the string
   */
  public static String encodeBase64(String string) {
    byte[] bytesEncoded = Base64.encodeBase64(string.getBytes());
    System.out.println("Encoded value is " + new String(bytesEncoded));
    return new String(bytesEncoded);
  }

  /**
   * Decode base 64.
   *
   * @param string the string
   * @return the string
   */
  public static String decodeBase64(String string) {
    byte[] valueDecoded = Base64.decodeBase64(string);
    System.out.println("Decoded value is " + new String(valueDecoded));
    return new String(valueDecoded);
  }

}
