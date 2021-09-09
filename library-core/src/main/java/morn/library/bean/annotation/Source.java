package morn.library.bean.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 源类注解
 *
 * <p>源类是唯一的，通常代表实例的输入类型
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.TYPE, ElementType.METHOD})
public @interface Source {

  /**
   * 获取源类
   *
   * @return 源类
   */
  Class<?> value();
}
