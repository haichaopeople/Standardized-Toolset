package morn.library.boot.message.rocket.support;

import static org.springframework.messaging.MessageHeaders.CONTENT_TYPE;
import static org.springframework.messaging.MessageHeaders.ERROR_CHANNEL;
import static org.springframework.messaging.MessageHeaders.REPLY_CHANNEL;

import morn.library.boot.message.BroadcastMessageHeaders;
import morn.library.boot.message.rocket.RocketMessageHeaderResolver;
import morn.library.boot.message.support.BroadcastMessageHeaderAccessor;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.util.MimeType;

/**
 * 默认Rocket消息头解析器
 */
public class SimpleRocketMessageHeaderResolver implements RocketMessageHeaderResolver {

  @Override
  public BroadcastMessageHeaders convert(MessageExt source) {
    BroadcastMessageHeaderAccessor headerAccessor = new BroadcastMessageHeaderAccessor();
    headerAccessor.setTopic(source.getTopic()); // 主题
    headerAccessor.setTags(source.getTags()); // 标签
    headerAccessor.setType(source.getUserProperty(BroadcastMessageHeaders.TYPE)); // 类型
    String contentType = source.getUserProperty(CONTENT_TYPE); // 格式
    headerAccessor.setContentType(MimeType.valueOf(contentType));
    headerAccessor.setErrorChannelName(source.getUserProperty(ERROR_CHANNEL)); // 异常通道
    headerAccessor.setReplyChannelName(source.getUserProperty(REPLY_CHANNEL)); // 回复通道
    return headerAccessor.toMessageHeaders();
  }
}
