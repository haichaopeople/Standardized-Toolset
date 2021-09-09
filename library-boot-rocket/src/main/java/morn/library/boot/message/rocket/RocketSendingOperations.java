package morn.library.boot.message.rocket;

import static morn.library.boot.message.rocket.RocketConstants.ROCKET;

import morn.library.boot.message.BroadcastMessage;
import morn.library.boot.message.BroadcastMessageConverter;
import morn.library.boot.message.BroadcastMessageSendingOperations;
import morn.library.util.BeanFunctionUtils;

/**
 * Rocket消息发送操作
 */
public interface RocketSendingOperations extends BroadcastMessageSendingOperations {

  /**
   * 获取发送目标
   *
   * <p>topic和tag组合
   */
  @SuppressWarnings("unchecked")
  default String getDestination(BroadcastMessage<?> message) {
    return BeanFunctionUtils.convert(BroadcastMessageConverter.class, message, ROCKET);
  }
}
