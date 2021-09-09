package morn.library.boot.message;

import morn.library.boot.message.constant.MessageConstant.MessageResultStatus;

/**
 * 消息操作结果
 *
 */
public interface MessageResult {

  /**
   * 获取操作状态
   *
   * @return 操作状态
   * @see MessageResultStatus 操作状态
   */
  String getStatus();

  /**
   * 获取原始结果
   *
   * @return 原始结果
   */
  Object getOriginalResult();

  /**
   * 判断操作是否成功
   *
   * @return 操作是否成功
   */
  default boolean isSuccess() {
    return MessageResultStatus.isSuccess(getStatus());
  }
}
