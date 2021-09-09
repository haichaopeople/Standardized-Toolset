package morn.library.cache;

import static morn.library.constant.ApplicationConstants.Caches.CACHE_MANAGER_NAME_PRIMARY;

import morn.library.bean.support.BeanCaches;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * 缓存工具类
 */
public class CacheGroups {

  private CacheGroups() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  /**
   * 获取缓存池
   *
   * @param name 缓存池名称
   * @return 缓存池
   */
  public static Cache cache(String name) {
    CacheManager cacheManager = BeanCaches.getBean(CACHE_MANAGER_NAME_PRIMARY, CacheManager.class);
    return cacheManager.getCache(name);
  }

  /**
   * 获取缓存组
   *
   * @param cacheName 缓存名称
   * @return 缓存组
   */
  public static CacheGroup cacheGroup(String cacheName) {
    CacheManager cacheManager = BeanCaches.getBean(CACHE_MANAGER_NAME_PRIMARY, CacheManager.class);
    SimpleCacheGroup cacheGroup = new SimpleCacheGroup(cacheManager);
    cacheGroup.setCacheName(cacheName);
    return cacheGroup;
  }
}
