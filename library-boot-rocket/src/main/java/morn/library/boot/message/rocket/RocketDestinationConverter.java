package morn.library.boot.message.rocket;

import morn.library.boot.message.BroadcastMessage;
import morn.library.boot.message.BroadcastMessageConverter;

/**
 * Rocket消息目标转换器
 */
public interface RocketDestinationConverter extends BroadcastMessageConverter<String> {

  /**
   * 转换发送目标
   *
   * @param source 源消息
   * @return 发送目标
   */
  @Override
  String convert(BroadcastMessage<?> source);
}
