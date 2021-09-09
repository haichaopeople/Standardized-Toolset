package morn.library.data;

/**
 * 可锁定的
 *
 * <p>默认为未锁定状态
 */
public interface Lockable {

  /**
   * 获取锁定状态
   */
  Boolean getLocked();

  /**
   * 设置锁定状态
   */
  void setLocked(Boolean isDisplay);
}
