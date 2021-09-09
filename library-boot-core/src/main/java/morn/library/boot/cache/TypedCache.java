package morn.library.boot.cache;

/**
 * 分类缓存
 */
public interface TypedCache {

  <T> T get(String type, String key);

  void put(String type, String key, Object value);
}
