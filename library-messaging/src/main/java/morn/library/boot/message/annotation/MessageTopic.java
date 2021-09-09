package morn.library.boot.message.annotation;

import static morn.library.util.AnnotationFeatureUtils.WILDCARD;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 消息主题
 *
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageTopic {

  /**
   * 消息通道名称
   *
   * <p>默认监听所有通道
   */
  String value() default WILDCARD;
}
