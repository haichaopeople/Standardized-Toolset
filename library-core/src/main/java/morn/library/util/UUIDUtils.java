package morn.library.util;

import java.util.UUID;
import lombok.experimental.UtilityClass;

/**
 * UUID工具类
 *
 */
@UtilityClass
public class UUIDUtils {

  /**
   * 32位小写
   *
   * @return UUID字符串
   */
  public static String lowercase() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
