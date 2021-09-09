package morn.library.boot.message.rocket.support;

import static morn.library.boot.message.rocket.RocketConstants.ROCKET;

import morn.library.bean.annotation.Tag;
import morn.library.boot.message.BroadcastMessage;
import morn.library.boot.message.BroadcastMessageHeaders;
import morn.library.boot.message.rocket.RocketDestinationConverter;

/**
 * 默认Rocket消息目标转换器
 */
@Tag(ROCKET)
public class SimpleRocketDestinationConverter implements RocketDestinationConverter {

  @Override
  public String convert(BroadcastMessage<?> source) {
    BroadcastMessageHeaders headers = source.getHeaders();
    return headers.getTopic();
  }
}
