package morn.library.notify.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通知接收人
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotifyReceiver {

  /**
   * 接收人类型
   */
  String type();

  /**
   * 接收人标识
   */
  String[] value() default {};
}
