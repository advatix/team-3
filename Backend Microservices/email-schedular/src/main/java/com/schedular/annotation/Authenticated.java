package com.schedular.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Auth 2.0
// https://www.baeldung.com/spring-security-oauth-jwt
/**
 * The Interface Authenticated.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authenticated {

  /**
   * Required.
   *
   * @return true, if successful
   */
  boolean required() default true;

  /**
   * Menu id.
   *
   * @return the int
   */
  int menuId() default 0;

  /**
   * Sub menu id.
   *
   * @return the int
   */
  int subMenuId() default 0;

}
