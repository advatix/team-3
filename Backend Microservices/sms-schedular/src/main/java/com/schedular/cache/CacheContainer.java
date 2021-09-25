/**
 * 
 */
package com.schedular.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.springframework.util.StringUtils;

/**
 * The Class CacheContainer.
 */
public class CacheContainer {

  /** The instance. */
  static CacheContainer _instance = null;

  /** The cache name map. */
  private ConcurrentHashMap<Object, Map<Object, Object>> cacheNameMap =
      new ConcurrentHashMap<Object, Map<Object, Object>>();

  /**
   * Default Constructor.
   */
  private CacheContainer() {
    CacheNames[] names = CacheNames.values();

    for (CacheNames cacheName : names) {
      Map<Object, Object> tokenInfo = null;

      if (cacheName == CacheNames.tokenData)
        tokenInfo = ExpiringMap.builder().expiration(15, TimeUnit.MINUTES).build();
      // else if(cacheName == CacheNames.invalidLogin)
      // tokenInfo =
      // ExpiringMap.builder().expiration(PropertyReaderUtil.getInstance().getIntProperty(CommonConstant.TIME_TO_CHECK_FAILED_LOGIN_ATTEMPT),
      // TimeUnit.SECONDS).build();
      // else if(cacheName == CacheNames.blockedUsers)
      // tokenInfo =
      // ExpiringMap.builder().expiration(PropertyReaderUtil.getInstance().getIntProperty(CommonConstant.TIME_TO_BLOCK_USER),
      // TimeUnit.SECONDS).build();
      else
        tokenInfo = new ConcurrentHashMap<Object, Object>();


      cacheNameMap.put(cacheName.getName(), tokenInfo);
    }
  }

  /**
   * Creating instance of <strong>CacheContainer</strong> class using <strong>Singleton</strong>
   * pattern.
   * 
   * @return
   */
  public static CacheContainer getInstance() {
    if (_instance == null) {
      synchronized (CacheContainer.class) {
       // if (_instance == null)
          _instance = new CacheContainer();
     }
    }
    return _instance;
  }

  /**
   * Adding element to container, then container will share the object to other listening node via
   * Multicast.
   *
   * @param keyObject - Key of Element
   * @param objectValue - Value of Element
   * @param cacheName - Name of Cache
   * @return <strong>True</strong> if added to container successfully, else return
   *         <strong>False</strong>
   */
  public boolean addToContainer(Object keyObject, Object objectValue, String cacheName) {
    try {
      if (keyObject == null || objectValue == null || cacheName == null)
        return false;

      checkCacheNameAvailability(cacheName);

      cacheNameMap.get(cacheName).put(keyObject, objectValue);

      // MulticastSocketListner.getDefaultInstance().addToQueue(new CacheElement(keyObject,
      // objectValue, cacheName, actionMethod));
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Removing element from specific cache.
   *
   * @param keyObject the key object
   * @param cacheName the cache name
   * @return true, if successful
   */
  public boolean deleteFromContainer(Object keyObject, String cacheName) {
    try {
      if (keyObject == null || cacheName == null)
        return false;

      cacheNameMap.get(cacheName).remove(keyObject);

      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;

  }


  /**
   * Delete all from container.
   *
   * @param cacheName the cache name
   * @return true, if successful
   */
  public boolean deleteAllFromContainer(String cacheName) {
    try {
      if (cacheName == null)
        return false;

      // checkCacheNameAvailability(cacheName);

      if (cacheNameMap.containsKey(cacheName)) // checks that mentoned cache is available
        cacheNameMap.get(cacheName).clear();

      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Gets the cache size.
   *
   * @param cacheName the cache name
   * @return the cache size
   */
  public long getCacheSize(String cacheName) {
    try {

      if (cacheName == null)
        return 0l;

      return cacheNameMap.get(cacheName).size();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0l;
  }


  /**
   * Fetching value from specific Cache using Key.
   * 
   * @param keyObject - Key of Element
   * @param cacheName - Name of Cache
   * @return <strong>Object</strong> if requested key available in requested cache, else return
   *         <strong>null</strong>
   */
  public Object getValueFromCache(Object keyObject, String cacheName) {
    try {
      if (keyObject == null || keyObject.equals(""))
        return null;

      if (!cacheNameMap.containsKey(cacheName))
        return null;

      return cacheNameMap.get(cacheName).get(keyObject);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Checks that requested key is already available in requested Cache.
   * 
   * @param keyObject - Key of Element
   * @param cacheName - Name of Cache
   * @return <strong>True</strong> if requested key available in requested cache, else return
   *         <strong>False</strong>
   */
  public boolean isKeyAvailable(Object keyObject, String cacheName) {
    try {
      if (keyObject == null || cacheName == null)
        return false;

      if (!cacheNameMap.containsKey(cacheName))
        return false;

      return cacheNameMap.get(cacheName).containsKey(keyObject);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  /** The Constant cacheFilePath. */
  static final String cacheFilePath = "/tmp/EZR.cache";

  /**
   * Cache Persistence related.
   *
   * @return true, if successful
   */
  public boolean persistCacheToDisk() {

    File persistFile =null;
    FileOutputStream f =null;
  //  ObjectOutputStream s = null;

    if (cacheFilePath != null && !cacheFilePath.equals("")) {
      try {
        persistFile = new File(cacheFilePath);
         f = new FileOutputStream(persistFile);
        try(ObjectOutputStream  s = new ObjectOutputStream(f);){
         s.writeObject(cacheNameMap);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }finally {
        try {
          if(f!=null) {
            f.close();
          }
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
       
      }
    }
    return false;
  }

  /**
   * Cache Persistence related.
   *
   * @return true, if successful
   */
  public boolean loadToContainer() {
    FileInputStream f =null;
    if (cacheFilePath != null && !cacheFilePath.equals("")) {
      try {
        File persistFile = new File(cacheFilePath);

        if (persistFile.exists()) {
           f = new FileInputStream(persistFile);
          try(ObjectInputStream stream = new ObjectInputStream(f);){
            cacheNameMap = (ConcurrentHashMap<Object, Map<Object, Object>>) stream.readObject(); 
          }
        //  stream.close();
      //    f.close();

          persistFile.delete();

          return true;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }finally {
        if(f!=null) {
          try {
            f.close();
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    }
    return false;
  }


  /**
   * Refresh cache container.
   *
   * @return true, if successful
   */
  public boolean refreshCacheContainer() {
    InnerInitializationManager.refreshCacheContainer();
    return true;
  }

  /**
   * Check cache name availability.
   *
   * @param cacheName the cache name
   */
  private void checkCacheNameAvailability(String cacheName) {

    if (cacheNameMap != null && !cacheNameMap.containsKey(cacheName)) {
      // Map<Object, Object> tokenInfo = ExpiringMap.builder().expiration(24,
      // TimeUnit.HOURS).build();
      Map<Object, Object> tokenInfo = new HashMap<Object, Object>();;
      cacheNameMap.put(cacheName, tokenInfo);
    }
  }

  /**
   * The Class InnerInitializationManager.
   */
  private static class InnerInitializationManager {
    
    /** The reader instance. */
    private static CacheContainer _readerInstance = createInstance();

    /**
     * Creates the instance.
     *
     * @return the cache container
     */
    private static CacheContainer createInstance() {
      return new CacheContainer();
    }

    /**
     * Refresh cache container.
     */
    private static void refreshCacheContainer() {
      _readerInstance = new CacheContainer();
    }
  }

  /**
   * Sets the request key.
   *
   * @param token the token
   * @return the string
   */
  public String setRequestKey(String token) {
    if (!StringUtils.isEmpty(token)
        && !CacheContainer.getInstance().isKeyAvailable(token, CacheNames.userActivity.getName())) {
      String id = UUID.randomUUID().toString();
      CacheContainer.getInstance().addToContainer(token, id, CacheNames.userActivity.getName());
      return id;
    }
    return null;
  }
}

