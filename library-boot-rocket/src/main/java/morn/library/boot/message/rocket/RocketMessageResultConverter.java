package morn.library.boot.message.rocket;

import morn.library.boot.message.MessageResult;
import morn.library.boot.message.MessageResultConverter;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * Rocket消息结果转换器
 */
public interface RocketMessageResultConverter extends MessageResultConverter<SendResult> {

  /**
   * 转换消息结果
   *
   * @param source 源结果
   * @return 标准结果
   */
  @Override
  MessageResult convert(SendResult source);
}
