package morn.library.bean;

import morn.library.bean.annotation.*;

/**
 * 注解特征
 *
 * <p>注解特征用于类或方法的唯一标识、标签、用途
 */
public interface AnnotationFeature {

  /**
   * 获取名称
   *
   * <p>同类中不允许名称重复
   *
   * @return 名称
   * @see Name
   * @see Function
   */
  String getName();

  /**
   * 获取标签
   *
   * <p>同类中允许标签重复
   *
   * @return 标签
   * @see Tag
   */
  String[] getTags();

  /**
   * 获取源
   *
   * @return 源
   * @see Source
   */
  Class<?> getSource();

  /**
   * 获取目标
   *
   * @return 目标
   * @see Target
   */
  Class<?> getTarget();
}
