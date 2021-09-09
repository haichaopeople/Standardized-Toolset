package morn.library.rest;

import morn.library.core.BeanConverter;

/**
 * REST消息转换器
 * <p>用于任意类型之间相互转换</p>
 *
 */
public interface RestConverter<S, T> extends BeanConverter<S, T> {

}
