package morn.library.boot.netty.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import morn.library.boot.netty.constant.HandlerTypeConstants;

/**
 * Netty处理者注解
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NettyHandler {

  /**
   * 处理类型
   *
   * @see HandlerTypeConstants
   */
  String value();
}
