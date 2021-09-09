package morn.library.boot.message.rocket;

import morn.library.boot.message.BroadcastMessageHeaderResolver;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * Rocket消息头解析器
 */
public interface RocketMessageHeaderResolver extends BroadcastMessageHeaderResolver<MessageExt> {

}
