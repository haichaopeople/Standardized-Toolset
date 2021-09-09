package morn.library.boot.message;

import morn.library.core.BeanConverter;

/**
 * 消息头解析器
 *
 * @param <S> 源消息
 */
public interface BroadcastMessageHeaderResolver<S> extends
    BeanConverter<S, BroadcastMessageHeaders> {

}
