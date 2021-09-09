package morn.library.boot.message.rocket;

import morn.library.boot.message.BroadcastMessageHeaderConverter;
import org.apache.rocketmq.common.message.MessageClientExt;

/**
 * Rocket消息头转换器
 */
public interface RocketMessageHeaderConverter extends
        BroadcastMessageHeaderConverter<MessageClientExt> {

}
