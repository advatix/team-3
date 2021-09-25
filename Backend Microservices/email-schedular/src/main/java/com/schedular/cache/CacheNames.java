/*
 * @author Advatix
 * @since 2019
 * @version 1.0
 */
package com.schedular.cache;

	/**
	 * The Enum CacheNames.
	 *
	 * @author GP0095387
	 */
public enum CacheNames {

		/**
		 * The token data.
		 *
		 * @uml.property  name="tokenData"
		 * @uml.associationEnd 
		 */
		tokenData("tokenData"),
		
		/**
		 * The user token.
		 *
		 * @uml.property  name="userToken"
		 * @uml.associationEnd 
		 */
		userToken("userTokenData"),
		
		/**
		 * The special token.
		 *
		 * @uml.property  name="specialToken"
		 * @uml.associationEnd 
		 */
        specialToken("specialToken"),
		
		/**
		 * The user data.
		 *
		 * @uml.property  name="userData"
		 * @uml.associationEnd 
		 */
		userData("userData"),
		
		/**
		 * The invalid login.
		 *
		 * @uml.property  name="invalidLogin"
		 * @uml.associationEnd 
		 */
		invalidLogin("invalidLogin"),
		
		/**
		 * The blocked users.
		 *
		 * @uml.property  name="blockedUser"
		 * @uml.associationEnd 
		 */
		blockedUsers("blockedUser"),
		
		/**
		 * The user activity.
		 *
		 * @uml.property  name="userActivity"
		 * @uml.associationEnd 
		 */
		
		userActivity("userActivity");
		
		/**
		 * The cache name.
		 *
		 * @uml.property  name="cacheName"
		 */
		private String cacheName;
		
		/**
		 * Instantiates a new cache names.
		 *
		 * @param names the names
		 */
		CacheNames(String names)
		{
			this.cacheName = names;
		}
		
		public String getName()
		{
			return this.cacheName;
		}
	}