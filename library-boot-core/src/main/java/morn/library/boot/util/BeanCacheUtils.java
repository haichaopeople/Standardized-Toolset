package morn.library.boot.util;

import java.util.List;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import morn.library.bean.FunctionHolder;

/**
 * 实例缓存工具类
 */
@Slf4j
@UtilityClass
public class BeanCacheUtils {

  /**
   * 判断函数名称是否重复
   *
   * @param functionHolders 方法持有者集合
   * @param functionName 函数名称
   * @return 函数名称重复
   */
  public static boolean contains(List<FunctionHolder> functionHolders, String functionName) {
    return functionHolders.stream()
        .anyMatch(functionHolder -> Objects.equals(functionHolder.getName(), functionName));
  }
}
