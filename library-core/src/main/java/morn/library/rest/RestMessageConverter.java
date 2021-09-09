package morn.library.rest;

/**
 * RestMessage转换器
 * <p>用于{@link RestMessage}与任意类型相互转换</p>
 *
 */
public interface RestMessageConverter<T> extends RestConverter<RestMessage, T> {

}
