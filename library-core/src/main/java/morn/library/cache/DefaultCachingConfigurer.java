package morn.library.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;

/**
 * 默认缓存配置
 */
public class DefaultCachingConfigurer extends CachingConfigurerSupport {

  private final CacheManager cacheManager;

  public DefaultCachingConfigurer(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @Override
  public CacheManager cacheManager() {
    return cacheManager;
  }
}
