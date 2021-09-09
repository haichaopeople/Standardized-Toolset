package morn.library.boot.message.support;

import java.lang.reflect.Type;

import morn.library.boot.message.BroadcastMessagePayloadResolver;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * 注解消息体解析器
 *
 * @param <S> 源类型
 * @param <P> 消息体类型
 */
public interface AnnotationBroadcastMessagePayloadResolver<S, P> extends
        BroadcastMessagePayloadResolver<S, P> {

  /**
   * 设置消息体类型
   *
   * <p>由于{@link Payload}泛型声明在消息处理方法上，
   * 因此基于注解的消息解析器要获取消息处理函数后才能解析
   *
   * @param type {@link Payload}类型
   */
  void setPayloadType(Type type);
}
