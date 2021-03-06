package morn.library.bean.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 命名注解
 *
 * <p>名称是唯一的，否则请使用标签注解{@link Tag}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.TYPE, ElementType.METHOD})
public @interface Name {

  /**
   * 获取名称
   *
   * @return 名称
   */
  String value();
}
