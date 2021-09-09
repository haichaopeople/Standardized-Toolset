package morn.library.boot.message;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import morn.library.util.GenericUtils;

/**
 * 消息处理者
 *
 */
public interface BroadcastMessageHandler extends MessageHandler {

  /**
   * 处理消息
   *
   * @param message 消息
   */
  void handleMessage(BroadcastMessage<?> message);

  @Override
  default void handleMessage(Message<?> message) {
    if (message instanceof BroadcastMessage) {
      BroadcastMessage<?> broadcastMessage = GenericUtils.castFrom(message);
      handleMessage(broadcastMessage);
    }
  }
}
