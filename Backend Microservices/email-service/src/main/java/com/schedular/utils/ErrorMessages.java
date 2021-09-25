/*
 * @author Advatix
 * @since 2019
 * @version 1.0
 */
package com.schedular.utils;

/**
 * The Interface ErrorMessages.
 */
public interface ErrorMessages {
  
  /** The invalid password. */
  String INVALID_PASSWORD = "Password must be minimum 8 and maximum 15 characters, at least one uppercase letter, one lowercase letter, one digit and one special character from allowed characters !,@,#,$";
  
  /** The invalid token message. */
  String INVALID_TOKEN_MESSAGE = "Token is invalid !!!";
  
  /** The token expired. */
  String TOKEN_EXPIRED = "Token expired";
  
  /** The user disabled message. */
  String USER_DISABLED_MESSAGE = "Entity not found or disabled";
  
  /** The Invalid login. */
  String InvalidLogin = "Invalid UserName / Password";
  
  /** The User already exists with user name. */
  String UserAlreadyExistsWithUserName = "User already exists with this username";
  
  /** The User name null. */
  String UserNameNull = "Username cannot be null";
  
  /** The First name null. */
  String FirstNameNull = "First Name cannot be null";
  
  /** The Password null. */
  String PasswordNull = "Password cannot null";
  
  /** The User status null. */
  String UserStatusNull = "User Status cannot be null";
  
  /** The Account id null. */
  String AccountIdNull = "Account Id cannot be null";
  
  /** The Role id null. */
  String RoleIdNull = "User role cannot be null";
  
  /** The invalid device type. */
  String INVALID_DEVICE_TYPE = "Invalid Device Type Provided !!!";
  
  /** The user not allow. */
  String USER_NOT_ALLOW = "Permission unavailable for this call";
  
  /** The exceed login attempts. */
  String EXCEED_LOGIN_ATTEMPTS = "You have exceed the number of login attempts. Please contact administrator";

}
