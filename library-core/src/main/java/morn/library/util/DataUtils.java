package morn.library.util;

import lombok.experimental.UtilityClass;
import morn.library.data.Displayable;
import morn.library.data.Lockable;

/**
 * 数据工具
 */
@UtilityClass
public class DataUtils {


  /**
   * 判断是否为显示状态
   */
  public static boolean isDisplay(Displayable displayable) {
    return SpareBooleanUtils.isNotFalse(displayable.getDisplay());
  }

  /**
   * 判断是否为锁定状态
   */
  public static boolean isLocked(Lockable lockable) {
    return SpareBooleanUtils.isTrue(lockable.getLocked());
  }
}
