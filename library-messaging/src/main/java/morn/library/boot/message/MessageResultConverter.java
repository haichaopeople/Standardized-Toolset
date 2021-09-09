package morn.library.boot.message;

import morn.library.core.BeanConverter;

/**
 * 消息结果转换器
 *
 * @param <S> 源结果类型
 */
public interface MessageResultConverter<S> extends BeanConverter<S, MessageResult> {

}
