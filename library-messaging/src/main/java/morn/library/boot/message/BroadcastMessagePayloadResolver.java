package morn.library.boot.message;

import morn.library.core.BeanConverter;

/**
 * 消息体解析器
 *
 * @param <S> 源消息类型
 * @param <P> 消息体类型
 */
public interface BroadcastMessagePayloadResolver<S, P> extends BeanConverter<S, P> {

  @Override
  P convert(S source);
}
