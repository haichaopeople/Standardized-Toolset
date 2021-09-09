package morn.library.core;

/**
 * 实例适配器
 *
 */
@FunctionalInterface
public interface BeanAdapter<S> {

  /**
   * 适配
   *
   * @param source 源
   * @return 目标
   */
  S adaption(S source);
}
