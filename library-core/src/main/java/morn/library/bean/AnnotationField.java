package morn.library.bean;

import java.lang.annotation.Annotation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * 注解属性
 */
@RequiredArgsConstructor
@Getter
@Setter
public class AnnotationField {

  /**
   * 注解类
   */
  private final Class<? extends Annotation> annotationClass;

  /**
   * 注解名称
   */
  private String annotationName;

  /**
   * 注解类型
   */
  private AnnotationFieldType annotationType = AnnotationFieldType.TAG;

  /**
   * 值名称
   */
  private String valueName = AnnotationUtils.VALUE;
}
