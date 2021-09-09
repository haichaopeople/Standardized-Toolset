package morn.library.boot.message;

import morn.library.core.BeanConverter;

/**
 * 消息转换器
 *
 * @param <T> 目标类型
 */
public interface BroadcastMessageConverter<T> extends BeanConverter<BroadcastMessage<?>, T> {

}
