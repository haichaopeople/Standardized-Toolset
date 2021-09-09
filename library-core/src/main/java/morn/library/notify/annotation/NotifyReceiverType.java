package morn.library.notify.annotation;


import morn.library.notify.NotifyMetaAdapter;
import morn.library.notify.NotifyProcessor;
import morn.library.util.AnnotationFeatureUtils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通知接受人类型注解
 * <p>用于标识通知的适配器{@link NotifyMetaAdapter}、处理者{@link
 * NotifyProcessor}等</p>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotifyReceiverType {

  /**
   * 通知接受人类型
   */
  String value() default AnnotationFeatureUtils.WILDCARD;
}
