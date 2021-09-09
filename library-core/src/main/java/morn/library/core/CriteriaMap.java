package morn.library.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 标准字典类
 */
public class CriteriaMap extends ConcurrentHashMap<String, Object> implements CriteriaAttributes {

  public CriteriaMap() {
    super();
  }

  public CriteriaMap(Map<String, Object> map) {
    super(map);
  }

  /**
   * 获取子集
   *
   * @param key 键
   * @return 值
   */
  public CriteriaMap getCriteriaMap(String key) {
    return getExpect(key);
  }
}
