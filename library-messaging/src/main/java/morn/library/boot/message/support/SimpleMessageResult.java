package morn.library.boot.message.support;

import lombok.Getter;
import lombok.Setter;
import morn.library.boot.message.MessageResult;

/**
 * 消息操作结果
 */
@Getter
@Setter
public class SimpleMessageResult implements MessageResult {

  /**
   * 状态
   */
  private String status;

  /**
   * 原结果
   */
  private Object originalResult;

  @Override
  public String getStatus() {
    return this.status;
  }

  @Override
  public Object getOriginalResult() {
    return this.originalResult;
  }
}
