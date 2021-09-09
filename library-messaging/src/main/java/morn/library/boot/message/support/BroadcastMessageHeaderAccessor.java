package morn.library.boot.message.support;

import morn.library.boot.message.BroadcastMessageHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 * 消息头访问者
 *
 * <p>消息头一旦生成无法修改，可使用{@link BroadcastMessageHeaderAccessor}重新生成
 *
 */
public class BroadcastMessageHeaderAccessor extends MessageHeaderAccessor {

  public BroadcastMessageHeaderAccessor() {
    super();
  }

  public BroadcastMessageHeaderAccessor(Message<?> message) {
    super(message);
  }

  @Override
  protected BroadcastMessageHeaderAccessor createAccessor(Message<?> message) {
    return new BroadcastMessageHeaderAccessor(message);
  }

  /**
   * 设置主题
   *
   * @param topic 主题名称
   */
  public void setTopic(String topic) {
    setHeader(BroadcastMessageHeaders.TOPIC, topic);
  }

  /**
   * 设置标签
   *
   * @param tags 标签数组
   */
  public void setTags(String... tags) {
    setHeader(BroadcastMessageHeaders.TAG, tags);
  }

  /**
   * 设置类型
   *
   * @param type 类型名称
   */
  public void setType(String type) {
    setHeader(BroadcastMessageHeaders.TYPE, type);
  }

  @Override
  public BroadcastMessageHeaders toMessageHeaders() {
    return new BroadcastMessageHeaders(getMessageHeaders());
  }
}
