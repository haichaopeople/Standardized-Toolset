package morn.library.boot.message.rocket.support;

import morn.library.boot.message.BroadcastMessage;
import morn.library.boot.message.MessageResult;
import morn.library.boot.message.MessageResultConverter;
import morn.library.boot.message.rocket.RocketConstants;
import morn.library.boot.message.rocket.RocketSendingOperations;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import morn.library.util.BeanFunctionUtils;

/**
 * 默认Rocket消息发送操作类
 */
public class SimpleRocketSendingOperations implements RocketSendingOperations {

  /**
   * Rocket原生操作类
   */
  private final RocketMQTemplate template;

  public SimpleRocketSendingOperations(RocketMQTemplate template) {
    this.template = template;
  }

  @Override
  @SuppressWarnings("unchecked")
  public MessageResult syncSend(BroadcastMessage<?> message) {
    String destination = getDestination(message);
    SendResult sendResult = template.syncSend(destination, message);
    return BeanFunctionUtils.convert(MessageResultConverter.class, sendResult, RocketConstants.ROCKET);
  }
}
