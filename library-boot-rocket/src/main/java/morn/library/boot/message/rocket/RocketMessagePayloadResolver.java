package morn.library.boot.message.rocket;

import morn.library.boot.message.support.AnnotationBroadcastMessagePayloadResolver;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * Rocket消息体解析器
 *
 * @param <P> 消息体类型
 */
public interface RocketMessagePayloadResolver<P> extends
        AnnotationBroadcastMessagePayloadResolver<MessageExt, P> {

  @Override
  P convert(MessageExt source);
}
