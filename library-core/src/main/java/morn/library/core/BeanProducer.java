package morn.library.core;

import java.util.concurrent.Callable;

/**
 * 实例生产者
 */
@FunctionalInterface
public interface BeanProducer<T> extends Callable<T> {

  /**
   * 生产
   *
   * @return 实例
   */
  T product();

  @Override
  default T call() {
    return product();
  }
}
