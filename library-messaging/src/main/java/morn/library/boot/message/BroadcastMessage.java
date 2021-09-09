package morn.library.boot.message;

import org.springframework.messaging.Message;

/**
 * 消息
 *
 * @param <P> 消息体类型
 */
public interface BroadcastMessage<P> extends Message<P> {

  /**
   * 获取消息头
   *
   * @return 消息头
   */
  @Override
  BroadcastMessageHeaders getHeaders();
}
