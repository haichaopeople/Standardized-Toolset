package morn.library.bean.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 标签注解
 *
 * <p>标签是可重复的，否则请使用名称{@link Name}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.TYPE, ElementType.METHOD})
public @interface Tag {

  /**
   * 获取名称标签
   *
   * @return 名称标签
   */
  String[] value() default {};
}
