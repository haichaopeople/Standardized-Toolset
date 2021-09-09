package morn.library.bean;

import morn.library.bean.annotation.Name;
import morn.library.bean.annotation.Tag;
import morn.library.bean.annotation.Target;

/**
 * 注解属性类型
 */
public enum AnnotationFieldType {
  /**
   * 命名注解
   *
   * @see Name
   */
  NAME,
  /**
   * 源注解
   */
  SOURCE,
  /**
   * 标签注解
   *
   * @see Tag
   */
  TAG,
  /**
   * 目标注解
   *
   * @see Target
   */
  TARGET
}
