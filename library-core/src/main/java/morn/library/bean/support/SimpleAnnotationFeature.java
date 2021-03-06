package morn.library.bean.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import morn.library.bean.AnnotationFeature;

/**
 * 注解标识实现
 *
 * <p>注解标识用于描述对象的唯一标识、标签、用途
 */
@Getter
@ToString
@AllArgsConstructor
public class SimpleAnnotationFeature implements AnnotationFeature {

  /**
   * 名称
   *
   * @see AnnotationFeature#getName()
   */
  protected String name;

  /**
   * 标签
   *
   * @see AnnotationFeature#getTags()
   */
  protected String[] tags;

  /**
   * 源类型
   *
   * @see AnnotationFeature#getSource()
   */
  protected Class<?> source;

  /**
   * 目标类型
   *
   * @see AnnotationFeature#getTarget()
   */
  protected Class<?> target;

  public SimpleAnnotationFeature(AnnotationFeature identify) {
    this(identify.getName(), identify.getTags(), identify.getSource(), identify.getTarget());
  }

  /**
   * 获取注解特征构建器
   *
   * @return 注解特征构建器
   * @deprecated 直接使用 {@link AnnotationFeatureBuilder}
   */
  @Deprecated
  public static AnnotationFeatureBuilder builder() {
    return AnnotationFeatureBuilder.empty();
  }

}
