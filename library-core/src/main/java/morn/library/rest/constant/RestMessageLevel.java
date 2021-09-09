package morn.library.rest.constant;

/**
 * 消息级别
 */
public enum RestMessageLevel {
  /**
   * 调试
   */
  DEBUG("debug"),
  /**
   * 信息
   */
  INFO("info"),
  /**
   * 警告
   */
  WARNING("warning"),
  /**
   * 错误
   */
  ERROR("error");

  /**
   * 值
   */
  private String value;

  RestMessageLevel(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
