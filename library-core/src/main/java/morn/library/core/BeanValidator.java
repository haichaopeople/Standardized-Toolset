package morn.library.core;

/**
 * 实例验证器
 */
@FunctionalInterface
public interface BeanValidator<T> {

  /**
   * 校验
   *
   * @param source 源数据
   * @return 检验是否通过
   */
  boolean validate(T source);
}
