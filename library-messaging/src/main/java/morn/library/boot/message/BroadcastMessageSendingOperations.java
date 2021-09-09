package morn.library.boot.message;

import java.util.concurrent.CompletableFuture;
import morn.library.task.TaskExecutors;

/**
 * 消息发送操作
 *
 */
public interface BroadcastMessageSendingOperations {

  /**
   * 异步发送消息
   *
   * @param message 消息
   * @return 发送结果
   */
  default CompletableFuture<MessageResult> asyncSend(BroadcastMessage<?> message) {
    return TaskExecutors.submit(() -> syncSend(message));
  }

  /**
   * 同步发送消息
   *
   * @param message 消息
   * @return 发送结果
   */
  MessageResult syncSend(BroadcastMessage<?> message);
}
