package morn.library.boot.message.rocket;

import morn.library.boot.message.support.AnnotationBroadcastMessageHandler;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * Rocket消息处理者
 */
public interface RocketMessageHandler extends AnnotationBroadcastMessageHandler<MessageExt> {

}
