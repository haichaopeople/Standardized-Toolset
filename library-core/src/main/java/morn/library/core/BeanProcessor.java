package morn.library.core;

/**
 * 实例处理器
 */
@FunctionalInterface
public interface BeanProcessor<T> {

  /**
   * 处理
   *
   * @param source 源
   */
  void handle(T source);
}
