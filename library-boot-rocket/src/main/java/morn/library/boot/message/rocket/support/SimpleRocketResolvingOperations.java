package morn.library.boot.message.rocket.support;

import java.util.List;

import morn.library.boot.message.BroadcastMessageHeaderResolver;
import morn.library.boot.message.rocket.RocketResolvingOperations;
import morn.library.boot.message.support.AbstractBroadcastMessageResolvingOperations;
import morn.library.boot.message.support.AnnotationBroadcastMessageHandler;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 默认Rocket消息解析操作类
 */
public class SimpleRocketResolvingOperations extends
        AbstractBroadcastMessageResolvingOperations<MessageExt> implements RocketResolvingOperations {

  public SimpleRocketResolvingOperations(
      AnnotationBroadcastMessageHandler<MessageExt> messageHandler,
      List<BroadcastMessageHeaderResolver<?>> headerResolvers) {
    super(messageHandler, headerResolvers);
  }

  @Override
  public boolean syncResolve(MessageExt message) {
    getMessageHandler().handleMessage(message);
    return true;
  }
}
