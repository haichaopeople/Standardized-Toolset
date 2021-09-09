package morn.library.rest.support;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import morn.library.rest.RestMessage;
import morn.library.rest.constant.RestMessageLevel;

/**
 * REST消息
 *
 */
@Getter
@Setter
@ToString
public class SimpleRestMessage implements RestMessage {

  /**
   * 成功标识
   */
  private int status;

  /**
   * 状态码
   */
  private String code;

  /**
   * 消息级别
   *
   * @see RestMessageLevel 级别枚举
   */
  private String level;

  /**
   * 消息内容
   */
  private String message;

  /**
   * 数据
   */
  private Object data;

  /**
   * 设置消息级别
   *
   * @param level 消息级别枚举
   */
  @Override
  public void setLevel(RestMessageLevel level) {
    this.level = level.getValue();
  }
}
