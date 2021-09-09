package morn.library.boot.message;

import morn.library.core.BeanConverter;

/**
 * 消息头转换器
 *
 * @param <T> 目标类型
 */
public interface BroadcastMessageHeaderConverter<T> extends
    BeanConverter<BroadcastMessageHeaders, T> {

  @Override
  default T convert(BroadcastMessageHeaders source) {
    return null;
  }
}
