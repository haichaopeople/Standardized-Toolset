package morn.library.boot.message.rocket;

import morn.library.boot.message.BroadcastMessageResolvingOperations;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * Rocket消息解析操作
 */
public interface RocketResolvingOperations extends BroadcastMessageResolvingOperations<MessageExt> {

}
