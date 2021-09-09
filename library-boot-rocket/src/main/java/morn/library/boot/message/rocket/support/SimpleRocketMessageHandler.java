package morn.library.boot.message.rocket.support;

import morn.library.boot.message.rocket.RocketMessageHandler;
import morn.library.boot.message.support.AbstractAnnotationBroadcastMessageHandler;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 默认Rocket消息处理者
 */
public class SimpleRocketMessageHandler extends
        AbstractAnnotationBroadcastMessageHandler<MessageExt> implements RocketMessageHandler {

}
